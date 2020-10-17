package com.familytree.challenge.exceptions;

public class WrongFamilyTreeException extends RuntimeException {

    public WrongFamilyTreeException() {
        super("Wrong family tree");
    }
}
