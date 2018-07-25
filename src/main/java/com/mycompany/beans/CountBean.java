package com.mycompany.beans;

public class CountBean {
	   private int counter = 0;

	    public int count() {
	        return counter++;
	    }
	    public int countx2() {
	    	counter = counter + 2;
	        return counter; 
	    }
}
