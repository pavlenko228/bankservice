package com.bank.bankservice.exception;

public class NoSuchAccount extends RuntimeException {
    public NoSuchAccount(String message) {
        super(message);
    }
}
