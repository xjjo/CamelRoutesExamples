package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class DirectRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
	
	from("timer:foo?period=10000")       // receive a file
	    .to("direct:processTheFile") // send to direct endpoint
	    .log(">>> ${body}");   // will print 'Hola mundo!'

	// meanwhile...    
	from("direct:processTheFile")     // receive from direct endpoint
	    .setBody(simple("Hola mundo!")) // set a simple body
	    .log("Received body");           // modify the message body

	}

}
