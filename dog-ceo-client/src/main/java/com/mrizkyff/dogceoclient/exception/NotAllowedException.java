/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrizkyff.dogceoclient.exception;

/**
 *
 * @author yudi
 */
public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String exception) {
        super(exception);
    }
}
