package com.mycompany.routes.jetty;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class JettyHelloWorldRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		//http://localhost:8080/helloWorld
		from("jetty:http://0.0.0.0:8080/helloWorld").process(new Processor(){
			@Override
			public void process(Exchange exchange) throws Exception {
				Message out = exchange.getOut();
				out.setBody("Hello, World!");
			}	
		}); 				
	}
}
/*
0.0.0.0 -> es usado para independizarce del hostname

# dependencias requeridas -
---
1. camel-core
2. camel-jetty 

<dependency> 
<groupId>org.apache.camel</groupId> 
	<artifactId>camel-jetty</artifactId> 
	<version>x.x.x</version> <!-- use the same version as your Camel core version --> 
</dependency>
*/