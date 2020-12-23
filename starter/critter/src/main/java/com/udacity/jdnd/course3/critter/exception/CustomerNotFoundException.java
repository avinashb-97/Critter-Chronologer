package com.udacity.jdnd.course3.critter.exception;

import org.aspectj.bridge.Message;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message)
    {
        super(message);
    }


}
