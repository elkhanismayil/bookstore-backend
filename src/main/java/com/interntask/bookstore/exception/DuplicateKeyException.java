package com.interntask.bookstore.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateKeyException extends DataIntegrityViolationException {
    public DuplicateKeyException(String msg) {
        super(msg);
    }
}
