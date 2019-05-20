package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class RestToRestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jetty:http://0.0.0.0:8080/api/v2/tokens?matchOnUriPrefix=true")
		.to("https://developers.decidir.com/api/v2/tokens?bridgeEndpoint=true&amp;throwExceptionOnFailure=false").delay(10000);
		
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