package com.familytree.challenge.repository;

import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository implements PersonRepo {

    private List<Person> personList;

    public PersonRepository() {
        this.personList = new ArrayList<>();
        this.personList.add(
            new Person(UUID.fromString("15519758-639c-4d6b-bd6d-f7cceec20c73"), UUID.randomUUID(),
                UUID.randomUUID(), Gender.FEMALE));
        this.personList.add(
            new Person(UUID.fromString("8be4f2bc-4bf9-4c88-8655-3b5bdfdf623c"), UUID.randomUUID(),
                UUID.randomUUID(), Gender.MALE));
    }

    @Override
    public Person save(Person person) {
        personList.add(person);
        return person;
    }

    @Override
    public Person findById(UUID id) {
        return personList.stream().filter(person -> id.equals(person.getId())).findFirst()
                         .orElse(null);
    }
}
