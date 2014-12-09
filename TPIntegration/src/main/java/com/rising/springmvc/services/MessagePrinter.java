package com.rising.springmvc.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rising.springmvc.interfaces.MessageService;

@Component
public class MessagePrinter {
	
	final private MessageService service;
    @Autowired
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }
}
