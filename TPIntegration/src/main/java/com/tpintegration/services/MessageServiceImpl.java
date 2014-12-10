package com.tpintegration.services;

import com.tpintegration.interfaces.MessageService;

public class MessageServiceImpl implements MessageService{

	private String message;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message=message;
	}

}
