package com.mycompany.routes.jetty;

import javax.servlet.http.HttpSession;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMessage;

public class JettyHttpSessionRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		//http://localhost:8080/session
		from("jetty:http://0.0.0.0:8080/session/?sessionSupport=true").process(new Processor(){
			@Override
			public void process(Exchange exchange) throws Exception {
				HttpSession session = exchange.getIn(HttpMessage.class)
						.getRequest().getSession();	
				
				Message out = exchange.getOut();
				out.setBody(session.getId());
			}	
		}); 				
	}

}
/*	
	Se agrega como param del consumer sessionSupport=true, para soportar sessiones.
	Probar el resultado en diferentes browsers.

 */