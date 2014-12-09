package com.rising.springmvc.action;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import com.rising.springmvc.interfaces.MessageService;
import com.rising.springmvc.services.MessagePrinter;

@Configuration
@ComponentScan
public class Application {
	
	@Bean
    MessageService mockMessageService() {
        return new MessageService() {
            public String getMessage() {
              return "Hello World!";
            }
        };
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ApplicationContext context = 
		          new AnnotationConfigApplicationContext(Application.class);
		      MessagePrinter printer = context.getBean(com.rising.springmvc.services.MessagePrinter.class);
		      printer.printMessage();
	}

}
