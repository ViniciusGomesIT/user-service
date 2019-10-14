package br.com.api.enums;

public enum MessagesEnum {
	
	USER_EMAIL_UPDATED_MESSAGE("email was changed successfully");
	
	private String message;

	public String getMessage() {
		return message.toUpperCase();
	}

	private MessagesEnum(String message) {
		this.message = message;
	}
}
