package com.bitc.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Covid19InfoStateDto {
	//클래스 멤버변수
	private Covid19InfoStateHeaderDto header;
	private Covid19InfoStateBodyDto body;
	
	@XmlElement(name="header")
	public Covid19InfoStateHeaderDto getHeader() {
		return header;
	}
	public void setHeader(Covid19InfoStateHeaderDto header) {
		this.header = header;
	}
	
	@XmlElement(name="body")
	public Covid19InfoStateBodyDto getBody() {
		return body;
	}
	public void setBody(Covid19InfoStateBodyDto body) {
		this.body = body;
	}
	
	
	
}
