package com.familytree.challenge.repository;

import com.familytree.challenge.models.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository implements PersonRepo {

    private List<Person> personList;

    public PersonRepository() {
        this.personList = new ArrayList<>();
    }

    @Override
    public Person save(Person person) {
        personList.add(person);
        return person;
    }

    @Override
    public Person findById(UUID id) {
        return personList.stream().filter(person -> id.equals(person.getId())).findFirst().orElse(null);
    }
}
