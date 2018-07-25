package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class HttpRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("timer:foo?period=10000")       // receive a file
	    .to("direct:start") // send to direct endpoint
	    .log(">>> ${body}");   // will print 'Hola mundo!'
		
		
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
	}

}
// features:install camel-http
/*
<dependency>
     <groupId>org.apache.camel</groupId>
     <artifactId>camel-http</artifactId>
     <version>x.x.x</version>
     <!-- use the same version as your Camel core version -->
 </dependency> 
 */