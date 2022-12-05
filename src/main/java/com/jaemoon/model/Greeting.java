package com.jaemoon.model;

import org.springframework.web.util.HtmlUtils;

import com.jaemoon.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Greeting {

	private String name, dt, content;
	
	public Greeting() {
	}
	
	public Greeting(String content) {
		
	}
	public Greeting(HelloMessage message) {
		this.name = message.getName();
		this.dt = DateUtil.getTime(message.getTime());
		this.content = HtmlUtils.htmlEscape(message.getContent());
	}

}
