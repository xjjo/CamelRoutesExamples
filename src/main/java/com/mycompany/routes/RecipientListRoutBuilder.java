package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class RecipientListRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		String ENDPOINT_REST ="jetty:http://localhost:8088/rest/inventory?bridgeEndpoint=true&amp;throwExceptionOnFailure=false";
		String ENDPOINT_SOAP ="jetty:http://localhost:8088/soap/inventory?bridgeEndpoint=true&amp;throwExceptionOnFailure=false";
		
		from("jetty:http://0.0.0.0:8080/inventory?matchOnUriPrefix=true")
		.streamCaching()
		.log(">>> ${body}")
		.log(">>> type: ${headers.type}")
		//.recipientList("direct:${headers.type}");
		.recipientList(simple("direct:${headers.type}"))
		.parallelProcessing().timeout(200);
		

		
		from("direct:rest")
		.log(">>> rest")
		.to(ENDPOINT_REST);

		from("direct:soap")
		.log(">>> soap")
		.to(ENDPOINT_SOAP);
		
	}

}
