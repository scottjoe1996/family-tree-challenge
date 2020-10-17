package com.familytree.challenge.exceptions;

import com.familytree.challenge.models.Person;

public class FamilyTreeException extends ValidationException {
    private Person person;

    public FamilyTreeException(String errorMessage) {
        super(errorMessage);
    }

    public FamilyTreeException(String errorMessage, Person person) {
        super(errorMessage);
        this.person = person;
    }

    public Person getPerson() {
        return this.person;
    }
}
