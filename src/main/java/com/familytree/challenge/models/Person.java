package com.familytree.challenge.models;

import java.util.UUID;

public class Person {
    private final UUID id;
    private final UUID fatherId;
    private final UUID motherId;
    private final Gender gender;

    public Person(UUID id, UUID fatherId, UUID motherId, Gender gender) {
        this.id = id;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public UUID getFatherId() {
        return fatherId;
    }

    public UUID getMotherId() {
        return motherId;
    }

    public Gender getGender() {
        return gender;
    }
}