package com.css.model.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationErrorReport {
	
	private String 	title;
    private String 	httpStatus;
    private String 	userMessage;
    private String 	path;
    private String 	developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<String, List<ValidationError>>();	

    public ValidationErrorReport() {

    }

    
    public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(String status) {
		this.httpStatus = status;
	}


	public String getUserMessage() {
		return userMessage;
	}


	public void setUserMessage(String detail) {
		this.userMessage = detail;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getDeveloperMessage() {
		return developerMessage;
	}


	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}


	public Map<String, List<ValidationError>> getErrors() {
		return errors;
	}


	public void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}

}
