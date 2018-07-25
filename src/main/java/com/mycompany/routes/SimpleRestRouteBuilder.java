package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class SimpleRestRouteBuilder extends RouteBuilder {

    public void configure() {

        restConfiguration()
        .component("restlet")
        .host("localhost").port("8080");
        
        rest("/api/customers")
        .get().to("direct:processTheFile");
       
        
        from("direct:processTheFile")
        .to("log:samplelog"); 
        
    }

}
