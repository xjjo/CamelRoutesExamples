package com.mycompany.routes.jetty;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.stream.CachedByteArrayOutputStream;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.apache.camel.converter.stream.InputStreamCache;

public class JettyTestRouteBuilder extends RouteBuilder {
	@Override
	public void configure() throws Exception  {
		//http://localhost:8080/test
				from("jetty:http://0.0.0.0:8080/test").streamCaching().process(new Processor(){
					@Override
					public void process(Exchange exchange) throws Exception {
							
						InputStreamCache isc =  (InputStreamCache) exchange.getIn().getBody();
						
						BufferedReader reader = new BufferedReader( new InputStreamReader(isc));
						
						StringBuilder buffer = new StringBuilder();					    
					    String line;
					    while ((line = reader.readLine()) != null) {
					        buffer.append(line);
					    }
					    String data = buffer.toString();
						
						System.out.println(data);
						
					}	
				}).to("http://localhost:8089/test1?bridgeEndpoint=false&throwExceptionOnFailure=false").streamCaching().process(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						
						
						exchange.getOut().setBody( exchange.getIn().getBody());
						//HttpServletResponse request = exchange.getOut().getBody(HttpServletResponse.class); 
						
						
						
						/*InputStream isc = (InputStream) exchange.getIn().getBody();
						
						BufferedReader reader = new BufferedReader( new InputStreamReader(isc));
						
						StringBuilder buffer = new StringBuilder();					    
					    String line;
					    while ((line = reader.readLine()) != null) {
					        buffer.append(line);
					    }
					    String data = buffer.toString();
					    
						System.out.println(data);*/
					}
				});
	}
}
