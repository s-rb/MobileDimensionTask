package ru.mobiledimension.test.exception;

import lombok.Getter;

@Getter
public class DocumentAlreadyRegisteredException extends RuntimeException {
    private String documentNumber;

    public DocumentAlreadyRegisteredException(String documentNumber) {
        super(String.format("Person with document number [%s] is already exists", documentNumber));
        this.documentNumber = documentNumber;
    }
}
