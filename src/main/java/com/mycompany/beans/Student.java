package com.mycompany.beans;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
/*
 * valid json - pasa las validaciones
 * ---------------------------------------
 * {"firstName":"hernan","lastName":"oliva","grade":"mayor","graduated":true}
 * 
 *  invalid json - pasa las validaciones
 * ---------------------------------------
 * {"firstName":"hernan","lastName":"oliva","grade":"mayor","graduated":false}
 * */

public class Student {
	// No validation rules on these fields
	  private String firstName;
	  private String lastName;

	  // This field can't be null
	  @NotNull
	  private String grade;

	  // This field must be true
	  @AssertTrue
	  private boolean graduated;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public boolean isGraduated() {
		return graduated;
	}

	public void setGraduated(boolean graduated) {
		this.graduated = graduated;
	}
	  
	 
}
