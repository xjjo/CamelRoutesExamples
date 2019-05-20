package com.mycompany.routes.jetty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class JettyHttpServletRequestRouteBuilder extends RouteBuilder{
/*
 * getting httpServletRequest Object to obtain an http header.
 */
	@Override
	public void configure() throws Exception {
		//http://localhost:8080/user/agent
				from("jetty:http://0.0.0.0:8080/user/agent").process(new Processor(){
					@Override
					public void process(Exchange exchange) throws Exception {
						HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);
								
						Message out = exchange.getOut();
						out.setBody(request.getHeader("User-Agent"));
					}	
				}); 
	}

}
