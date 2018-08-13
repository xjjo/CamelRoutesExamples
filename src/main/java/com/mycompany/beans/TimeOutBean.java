package com.mycompany.beans;

import org.apache.camel.Exchange;

public class TimeOutBean {
	
		 String resolveTry(int n){
				 return String.valueOf(n+1);
	 }
	  public Exchange resolveTimeOutValue(Exchange e){
		 int value = 100;
		 String n = (e.getIn().getHeader("try", String.class)==null?"1":e.getIn().getHeader("try", String.class));
		 String time = (e.getIn().getHeader("timeout", String.class)==null?"100":e.getIn().getHeader("timeout", String.class));
		 
		 time = String.valueOf(Integer.parseInt(n) * Integer.parseInt(time));
		 e.getIn().setHeader("try", Integer.parseInt(n)+1 );
		 e.getIn().setHeader("timeout",Integer.parseInt(time) );
		 e.getOut().setHeader("try",  Integer.parseInt(n)+1);
		 e.getOut().setHeader("timeout", Integer.parseInt(time));
		 return e;
		 
	 }
	 /* 
	 public void resolveTimeOutValue(){
	 	System.out.println("resolveTimeOutValue()");
	 }
	 */
}
