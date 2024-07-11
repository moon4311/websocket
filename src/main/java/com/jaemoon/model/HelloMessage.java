package com.jaemoon.model;

import java.util.Date;

import com.jaemoon.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloMessage {

	private String type;
	// type : userCnt, enter, exit, message
	
	private String name, content;
	
	private Date time = new Date();

	public HelloMessage() {
	}

	public HelloMessage(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return  String.format("{name : %s , time : %s , content: %s}", name, DateUtil.getTime(time) , content);
	}

}
