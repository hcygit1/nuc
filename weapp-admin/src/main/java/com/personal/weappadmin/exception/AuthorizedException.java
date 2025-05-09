package com.personal.weappadmin.exception;


public class AuthorizedException extends RuntimeException {

    public AuthorizedException() {
        super();
    }
    public AuthorizedException(String message) {
        super(message);
    }
}
