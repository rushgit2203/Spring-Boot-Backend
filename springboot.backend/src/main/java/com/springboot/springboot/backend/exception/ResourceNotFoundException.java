package com.springboot.springboot.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//import static org.springframework.jdbc.datasource.init.ScriptStatementFailedException.buildErrorMessage;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID=1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    private String customMessage;


    public ResourceNotFoundException(String resourceName,String fieldName,Object fieldValue,String customMessage) {
        super(buildErrorMessage(resourceName,fieldName,fieldValue,customMessage));
//        super(String.format("Could not find %s with %s: '%s' in system",resourceName,fieldName,fieldValue,customMessage));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue=fieldValue;
        this.customMessage=customMessage;
    }

    private static String buildErrorMessage(String resourceName,String fieldName,Object fieldValue,String customMessage){
        return String.format("%s not found with %s: '%s'. %s", resourceName, fieldName, fieldValue, customMessage);
    }
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}
