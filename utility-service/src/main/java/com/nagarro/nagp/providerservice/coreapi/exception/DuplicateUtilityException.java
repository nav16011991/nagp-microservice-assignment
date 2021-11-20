package com.nagarro.nagp.providerservice.coreapi.exception;

public class DuplicateUtilityException extends IllegalStateException {

    public DuplicateUtilityException(String name) {
        super("Cannot duplicate utility  [" + name + "]");
    }
}
