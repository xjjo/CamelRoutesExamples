package com.mycompany.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.mycompany.beans.Student;

public class BeanValidationRoutBuilder extends RouteBuilder {

	
	
     
		public void configure() throws Exception {
		    
			  // Set up a JSON format so we can unmarshal to a POJO
			  JsonDataFormat json = new JsonDataFormat(JsonLibrary.Jackson);
			
			  // Set the target class that I want to convert the JSON to
			  json.setUnmarshalType(Student.class);
			 
				  // Read a JSON file
				  from("file:files/input/json?noop=true")
				  
				      // convert to a Java object
				      .unmarshal(json) 
				      
				      // Validate the Java object
				      .to("bean-validator:myvalidator")
				      .log(">>> ${body}")
				      .log(">>> ${body.lastName}")
				      .to("mock:output");
				
				  	//La pregunta es como capturamos y manejamos las excepciones
		}

}