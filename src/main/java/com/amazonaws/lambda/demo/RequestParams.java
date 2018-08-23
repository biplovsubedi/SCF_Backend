package com.amazonaws.lambda.demo;

public class RequestParams {

	private String cin;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
	
	public RequestParams() {
		this.cin = "0";
	}
	
	public RequestParams(String cin) {
		this.cin = cin;
	}
	
}
