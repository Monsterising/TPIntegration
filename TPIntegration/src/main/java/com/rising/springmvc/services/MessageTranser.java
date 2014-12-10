package com.rising.springmvc.services;

import com.rising.springmvc.interfaces.MessageService;

public class MessageTranser implements MessageService{
	
	private String message;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message=message;
	}

}
