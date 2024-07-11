package com.jaemoon;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.jaemoon.model.Greeting;
import com.jaemoon.model.HelloMessage;

@Controller
public class GreetingController {


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
	
	
	@MessageMapping("/enter/{tid}")
	@SendTo("/topic/greetings/{tid}")
	public Greeting join(HelloMessage message) throws Exception {
		Thread.sleep(100); // simulated delay
		
		message.setContent(message.getName()+ "¥‘¿Ã ¿‘¿Â«œºÃΩ¿¥œ¥Ÿ.");
		return new Greeting(message);
	}
	
	@MessageMapping("/hello/{tid}")
	@SendTo("/topic/greetings/{tid}")
	public Greeting topic(HelloMessage message) throws Exception {
		Thread.sleep(100); // simulated delay
//		return new Greeting("Bye, " + HtmlUtils.htmlEscape(message.getName()) + "!");
		
		return new Greeting(message);
	}
	
	

}
