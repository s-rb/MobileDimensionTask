package ru.mobiledimension.test.exception;

import lombok.Getter;

public class PersonNotFoundException extends RuntimeException {

    @Getter
    private Integer id;

    public PersonNotFoundException(Integer id) {
        super(String.format("Person with id [%d] was not found", id));
    }
}
