package com.mycompany.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.mycompany.exception.CamelCustomException;

public class ThrowCamelCustomExceptionProcessor implements Processor {
	
	
	@Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Exception Thrown");
        throw new CamelCustomException();
    }

}
