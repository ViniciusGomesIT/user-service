package br.com.api.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "messages")
@Component
public class MessagesModel {

	private String parseUtilsError;
	private String emailSucessChanged;
	private String emailAlreadyRegistered;
	private String loginAlreadyRegistered;

	public String getParseUtilsError() {
		return parseUtilsError;
	}

	public void setParseUtilsError(String parseUtilsError) {
		this.parseUtilsError = parseUtilsError;
	}

	public String getEmailSucessChanged() {
		return emailSucessChanged;
	}

	public void setEmailSucessChanged(String emailSucessChanged) {
		this.emailSucessChanged = emailSucessChanged;
	}

	public String getEmailAlreadyRegistered() {
		return emailAlreadyRegistered;
	}

	public void setEmailAlreadyRegistered(String emailAlreadyRegistered) {
		this.emailAlreadyRegistered = emailAlreadyRegistered;
	}

	public String getLoginAlreadyRegistered() {
		return loginAlreadyRegistered;
	}

	public void setLoginAlreadyRegistered(String loginAlreadyRegistered) {
		this.loginAlreadyRegistered = loginAlreadyRegistered;
	}

}
