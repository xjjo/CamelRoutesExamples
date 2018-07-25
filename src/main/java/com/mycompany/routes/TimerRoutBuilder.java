package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;

import com.mycompany.beans.CountBean;
import com.mycompany.beans.HelloWorldBean;

public class TimerRoutBuilder extends RouteBuilder {

	
		
		public void configure() {
	     
			
			/*
			 * Desde un Bean 'HelloWorldBean' -singleton- inserta lo que retorna el metodo sayBay en el body 
			 */
			from("timer:foo?period=2000")
	            .bean(HelloWorldBean.class,"sayBye")
	            .log(">>> ${body}");
	       
			/*
			 * Desde un Bean 'CountBean' -singleton- inserta lo que retorna el metodo count en el header 
			 */
			from("timer:foo?period=1000")
		    .setHeader("JellyBeans", method(new CountBean(),"count"))
		    .log(">>> Your wealth is: ${headers.JellyBeans} beans");
		
	}

}
