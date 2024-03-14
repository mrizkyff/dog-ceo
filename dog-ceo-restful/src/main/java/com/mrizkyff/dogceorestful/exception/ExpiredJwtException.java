package com.mrizkyff.dogceorestful.exception;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String exception) {
        super(exception);
    }
}
