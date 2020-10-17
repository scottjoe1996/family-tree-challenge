package com.familytree.challenge.models;

public class AddChildRequest {
    private final Person father;
    private final Person mother;
    private final Gender gender;

    public AddChildRequest(Person father, Person mother, Gender gender) {
        this.father = father;
        this.mother = mother;
        this.gender = gender;
    }

    public Person getFather() {
        return father;
    }

    public Person getMother() {
        return mother;
    }

    public Gender getGender() {
        return gender;
    }
}
