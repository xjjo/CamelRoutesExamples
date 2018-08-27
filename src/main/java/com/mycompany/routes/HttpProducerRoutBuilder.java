package com.mycompany.routes;

import java.net.SocketTimeoutException;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.mycompany.beans.TimeOutBean;

public class HttpProducerRoutBuilder extends RouteBuilder {

	@SuppressWarnings("unchecked")
	@Override
	public void configure() throws Exception {
		
		/**
		 * jetty as a producer shuoldn't be used, it's currently deprecated.
		 * */
		
		String ENDPOINT_REST ="jetty:http://localhost:8088/rest/inventory?bridgeEndpoint=true&amp;throwExceptionOnFailure=false";
		
		String ENDPOINT_SOAP ="http://localhost:8088/soap/inventory?bridgeEndpoint=true&throwExceptionOnFailure=false&{0}";
		String TIMEOUT_PARAM_FOR_ENDPOINT= "httpClient.soTimeout=";
		
		from("jetty:http://0.0.0.0:8080/inventory?matchOnUriPrefix=true")
		.streamCaching()
		.log(">>> ${body}")
		.log(">>> type: ${headers.type}")
		//.removeHeaders("CamelHttp*")
		.recipientList(simple("direct:${headers.type}"));

		from("direct:rest").routeId("REST Proxy Route")	
		.log(">>> rest")
		.to(ENDPOINT_REST);

		from("direct:soap").routeId("SOAP Proxy Route")	
		.streamCaching()
		.doTry()
			.log(">>> soap")
			.process(new Processor() {				
				@Override
				public void process(Exchange exchange) throws Exception {
					TimeOutBean timeout = new TimeOutBean();
					timeout.resolveTimeOutValue(exchange);
					String concat = TIMEOUT_PARAM_FOR_ENDPOINT + exchange.getIn().getHeader("timeout",String.class);
					
					exchange.getOut().setHeader("customRecipient",(ENDPOINT_SOAP.replace("{0}", concat )));
					exchange.getOut().setBody(exchange.getIn().getBody());
				}
			})
			.log("customRecipient : ${header.customRecipient}")
			.recipientList(header("customRecipient"))
		.end()
		.doCatch(ExchangeTimedOutException.class, SocketTimeoutException.class )
			.log("Excepcion capturada exitosamente...")
			.to("direct:soap");
		
	}

}
