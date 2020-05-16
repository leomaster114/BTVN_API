package com.hoanv.SpringBootApi;

//@JsonAutoDetect
public class ErrorMessages {
	private int code;
	private String messages;

	public ErrorMessages(int code, String messages) {
		this.code = code;
		this.messages = messages;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}
