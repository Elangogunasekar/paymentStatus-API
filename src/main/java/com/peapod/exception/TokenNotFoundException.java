package com.peapod.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.HashMap;
import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;
    private List<HashMap<String, String>> errorList;

    public TokenNotFoundException(List<HashMap<String, String>> errorList) {
        super(errorList.toString());
        this.errorList = errorList;
    }
    public TokenNotFoundException(String exception) {
        super(exception);
    }
}
