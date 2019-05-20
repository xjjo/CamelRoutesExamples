package com.mycompany.routes.jetty;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class JettyRequestParametersRouteBuilder extends RouteBuilder{
	/*
		getting a request uri param...
		test this example in a browser hitting diferents url's.
	*/
	
	@Override
	public void configure() throws Exception {
		//http://localhost:8080/request/param?name=hernan
				from("jetty:http://0.0.0.0:8080/request/param").process(new Processor(){
					@Override
					public void process(Exchange exchange) throws Exception {
						String paramName = (String) exchange.getIn().getHeader("name");
								
						Message out = exchange.getOut();
						out.setBody("name: " + paramName);
					}	
				}); 
	}
}
