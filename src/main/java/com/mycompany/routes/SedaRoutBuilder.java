package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class SedaRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		/*
		 Problem 
		 ---------
		 1- after loging the body I lose its content. why?
		 
		 Stream types (like StreamSource, InputStream and Reader) are commonly used in messaging for performance reasons, 
		 they also have an important drawback: they can only be read once. 
		 In order to be able to work with message content multiple times, the stream needs to be cached.
		 
		 2- how to solve the problem?
		 using .streamCaching()
		 */
		
		
		from("timer:foo?period=10000")       // receive a file
	    .to("direct:start") // send to direct endpoint
	    .streamCaching()
	    .log(">>> ${body}")
	    .to("seda:processInventory")
	    .log(">>> ${body}"); // if I log the body I lose its content. why?
		
		
		String msg ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:getInventory>        \n" + 
				"         <arg0>p9034</arg0>\n" + 
				"      </ser:getInventory>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		from("direct:start").setBody(constant(msg)) 
				.setHeader("SOAPAction", constant("MySOAPAction"))
				.setHeader("CamelHttpMethod", constant("POST"))
				.setHeader("Content-Type", constant("text/xml;charset=UTF-8"))
				.to("http://localhost:8080/inventory")
				.log("SOAP service called"); // Here you can process service response	
		
		from("seda:processInventory")
			.log("Processing invetory")
			.to("file:files/output/inventory");

	}

}
