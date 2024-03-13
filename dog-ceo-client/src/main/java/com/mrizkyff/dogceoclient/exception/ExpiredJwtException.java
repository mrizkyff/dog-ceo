package com.mrizkyff.dogceoclient.exception;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String exception) {
        super(exception);
    }
}
