package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

import com.mycompany.beans.HelloWorldBean;

public class SayHelloRoutBuilder extends RouteBuilder {

	
	
	public void configure() {
     
		/*Dessde un bean 'HelloWorld' se llama al metodo sayHello y se inserta el valor en el body*/
		from("timer:foo?period=2000")
            .bean(HelloWorldBean.class,"sayHello")
            .log(">>> ${body}"); 
	
}

}