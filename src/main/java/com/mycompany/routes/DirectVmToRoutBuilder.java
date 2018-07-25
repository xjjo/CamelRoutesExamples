package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

public class DirectVmToRoutBuilder extends RouteBuilder {
//https://cleverbuilder.com/articles/camel-direct-vm-seda/
	@Override
	public void configure() throws Exception {
		from("timer:foo?period=10000")
	    .to("direct-vm:process-file"); // invoke the direct-vm endpoint

	}

}
