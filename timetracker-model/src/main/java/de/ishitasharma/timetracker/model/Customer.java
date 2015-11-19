package de.ishitasharma.timetracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {

	

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@NotNull
	@Size(min=3, message="Customer name should be longer than 2 characters")
	private String customername;
}
