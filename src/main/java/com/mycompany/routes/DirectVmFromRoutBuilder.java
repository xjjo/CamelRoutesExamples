package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class DirectVmFromRoutBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct-vm:process-file")    // receive from direct-vm endpoint
	    .to("log:samplelog");         // log the message

	}

}
