package com.cy.entity;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public int responseCode;
	public String responseMsg;
	public List<User> data;
	
	public Response(){}

}
