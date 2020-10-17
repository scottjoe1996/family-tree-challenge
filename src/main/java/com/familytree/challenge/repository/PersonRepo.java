package com.familytree.challenge.repository;

import com.familytree.challenge.models.Person;
import java.util.UUID;

public interface PersonRepo {

    Person save(Person person);

    Person findById(UUID id);
}
