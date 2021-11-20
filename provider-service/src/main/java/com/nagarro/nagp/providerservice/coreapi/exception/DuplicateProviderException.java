package com.nagarro.nagp.providerservice.coreapi.exception;

public class DuplicateProviderException extends IllegalStateException {

    public DuplicateProviderException(String name) {
        super("Cannot duplicate provider  [" + name + "]");
    }
}
