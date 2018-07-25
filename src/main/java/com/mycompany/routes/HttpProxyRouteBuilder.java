package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class HttpProxyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("jetty:http://0.0.0.0:8080/inventory?matchOnUriPrefix=true")
		 .streamCaching()
		.log(">>> ${body}")
		.to("jetty:http://localhost:8088/inventory?bridgeEndpoint=true&amp;throwExceptionOnFailure=false");//deprecated?

	}

}
/*
 http://camel.apache.org/how-to-use-camel-as-a-http-proxy-between-a-client-and-server.html
 
  -	The option matchOnUriPrefix is set to true, to match any wildcards in the "myapp" context path. 
  - The bridgeEndpoint option is set to true, to tell Camel that its a bridging from an incoming 
  Jetty service (ie. to act as a HTTP adapter/proxy). 
  - The option throwExceptionOnFailure is set to false, to rely back any errors communicating with the 
  real HTTP server directly to the client, without using any Camel Error Handling (ie. no exception is raised from Camel).
 
 ----------------------------
  
  The producer is deprecated - do not use. We only recommend using jetty as consumer (eg from jetty)
 
 
 */