package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.TryDefinition;

import com.mycompany.beans.CountBean;
import com.mycompany.beans.TimeOutBean;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.Processor;

public class XTestRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String ENDPOINT_REST ="jetty:http://localhost:8088/rest/inventory?bridgeEndpoint=true&amp;throwExceptionOnFailure=false";
		String ENDPOINT_SOAP ="jetty:http://localhost:8088/soap/inventory?bridgeEndpoint=true&throwExceptionOnFailure=false&{0}";
		
		from("jetty:http://0.0.0.0:8080/inventory?matchOnUriPrefix=true")
		.streamCaching()
		.log(">>> ${body}")
		.log(">>> type: ${headers.type}")
		.recipientList(simple("direct:${headers.type}"));

		from("direct:rest")
		.log(">>> rest")
		.to(ENDPOINT_REST);

		from("direct:soap").routeId("SOAP Proxy Route")	
		
		.doTry()
			.log(">>> soap")
			.process(new Processor() {				
				@Override
				public void process(Exchange exchange) throws Exception {
					TimeOutBean timeout = new TimeOutBean();
					timeout.resolveTimeOutValue(exchange);
					String concat ="httpClient.idleTimeout="+exchange.getIn().getHeader("timeout",String.class);
					System.out.println(concat);
					exchange.getIn().setHeader("customRecipient",(ENDPOINT_SOAP.replace("{0}", concat )));
				}
			})
			.log("timeout : ${header.timeout}")
			.recipientList(header("customRecipient")).end()
		.doCatch(ExchangeTimedOutException.class)
			.log("Excepcion capturada exitosamente...")
			.to("direct:soap");
		
	}

}



