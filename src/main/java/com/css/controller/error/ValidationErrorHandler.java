package com.css.controller.error;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.css.model.error.*;
import com.css.model.error.ValidationErrorReport;


@ControllerAdvice
public class ValidationErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationErrorHandler.class);

    private MessageSource messageSource;

    @Autowired
    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorReport processValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        LOGGER.debug("Handling form validation error");

        BindingResult result = ex.getBindingResult();
        
        ValidationErrorReport errorReport = new ValidationErrorReport();
        
        // Populate errorReport instance
        errorReport.setHttpStatus(HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if(requestPath == null) {
                requestPath = request.getRequestURI();
        }
        errorReport.setTitle("Validation Failed");
        errorReport.setDeveloperMessage("[" + DateFormat.getDateInstance().getDateTimeInstance().format(new Date()).toString() + "]: " + ex.getClass().getName());
        errorReport.setUserMessage("Please correct the errors in your inputs and resubmit."); 
        
        // Add detailed information on all errors
        List<FieldError> fieldErrors = result.getFieldErrors();
        
        for(FieldError fe : fieldErrors) {
        	List<ValidationError> validationErrorList = errorReport.getErrors().get(fe.getField());
        	if(validationErrorList == null) {
        		validationErrorList = new ArrayList<ValidationError>();
        		errorReport.getErrors().put(fe.getField(), validationErrorList);
            }
	        ValidationError validationError = new ValidationError(fe.getField (), messageSource.getMessage(fe, null));
	        validationErrorList.add(validationError);
		}

        return errorReport;
    }
    
}
