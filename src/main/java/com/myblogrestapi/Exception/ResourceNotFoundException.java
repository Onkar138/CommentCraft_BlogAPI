package com.myblogrestapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resource;
    private String feildName;
    private long feildValue;

    public ResourceNotFoundException(String resource, String feildName, long feildValue){
        super(String.format("%s is Not Found %s: '%s'",resource,feildName,feildValue));
        this.resource=resource;
        this.feildName=feildName;
        this.feildValue=feildValue;
    }

    public String getResource() {
        return resource;
    }

    public String getFeildName() {
        return feildName;
    }

    public long getFeildValue() {
        return feildValue;
    }
}
