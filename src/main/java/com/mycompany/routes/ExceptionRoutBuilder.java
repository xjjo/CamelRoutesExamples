package com.mycompany.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.mycompany.exception.CamelCustomException;
import com.mycompany.processor.ThrowCamelCustomExceptionProcessor;

public class ExceptionRoutBuilder extends RouteBuilder {
		private static final String FROM = "file:files/input/json?noop=true";
		private static final String FROM_INBOX = "file:files/inbox?noop=true";
		private static final String TO = "file:files/output";
	
		public void configure1() throws Exception {
	        
	        // *  There is no exception handling code written. So the message will keep retrying as the exception is not handled.
	        // *  --> mirar la diferencia en ThrowCamelCustomExceptionProcessor al comentar o no la exepcion, mientras que se lance 
	        // *  la excepcion, el mensaje nunca llega a su destino 'to', por lo que se sigue reintentando, mientras que si se descomenta
	        // *  el msg llega a su destino por lo que la ruta termina. 
	         
			from(FROM).process(new ThrowCamelCustomExceptionProcessor()).to(TO);
	    }
		
		
	    public void configure2() throws Exception {
			//Using Do Try block
			//This approach is similar to the Java try catch block. So the thrown exception will be immediately caught 
			// and the message wont keep on retrying.
			// si ThrowCamelCustomExceptionProcessor lanza la exception, el msg no termina en la carpeta ouput
			// si en ThrowCamelCustomExceptionProcessor se obvia lanzar la  exception, el msg  termina en la carpeta ouput
			from(FROM).doTry().process(new ThrowCamelCustomExceptionProcessor()).to("file:files/output")
            .doCatch(CamelCustomException.class).process(new Processor() {

                public void process(Exchange exchange) throws Exception {
                    System.out.println("handling ex");
                }
            }).log("Received body ");
		}
	    
	    
	    
	    public void configure3() throws Exception {
	    	/*
	    	 * The limitation of this approach is that it is applicable only for the single route.
	    	 * Suppose we have one more seperate route. Then if an exception occurs in that route 
	    	 * then it will not be handled by the current doTry block.
	    	 * */
	    	
	        from(FROM).doTry().process(new ThrowCamelCustomExceptionProcessor()).to(TO)
	            .doCatch(CamelCustomException.class).process(new Processor() {

	                public void process(Exchange exchange) throws Exception {
	                    System.out.println("handling ex");
	                }
	            }).log("Received body ");

	        from(FROM_INBOX).process(new ThrowCamelCustomExceptionProcessor()).to(TO);
	        /*dos rutas dif no pueden tener el mismo consumer 'from'
	         * pero si el mismo endpoint
	         * */
	    }
	    
	    
		
	    public void configure() throws Exception {
			/*
			 * Using OnException block
			 * The OnException block is written as a separate block from the routes.
             * This applies to all the routes. Below for both route starting with 
             * from("file:C:/inputFolder?noop=true") and from("file:C:/inbox?noop=true") 
             * the exception handling will be applied.
			 * */
	    	onException(CamelCustomException.class).process(new Processor() {

	            public void process(Exchange exchange) throws Exception {
	                System.out.println("handling ex");
	            }
	        }).log("Received body ").handled(true);

	        from(FROM).process(new ThrowCamelCustomExceptionProcessor()).to(TO);

	        from(FROM_INBOX).process(new ThrowCamelCustomExceptionProcessor()).to(TO);
		}

}
