package com.TuntunAuction.auth_service.exceptions;

public class UnauthorizedExceptions extends RuntimeException {
    public UnauthorizedExceptions(String message) {
        super(message);
    }
}
