package com.familytree.challenge.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonRepositoryTests {

    @Test
    public void findByIdShouldReturnExpectedPerson() {
        PersonRepository personRepository = new PersonRepository();
        Person personToSave = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.MALE);

        personRepository.save(personToSave);

        assertThat(personRepository.findById(personToSave.getId())).isEqualTo(personToSave);
    }

    @Test
    public void findByIdShouldReturnNullIfPersonDoesNotExist() {
        PersonRepository personRepository = new PersonRepository();
        assertThat(personRepository.findById(UUID.randomUUID())).isEqualTo(null);
    }

    @Test
    public void saveShouldReturnPersonOnSuccess() {
        PersonRepository personRepository = new PersonRepository();

        Person personToSave = new Person(UUID.randomUUID(), UUID.randomUUID(),
            UUID.randomUUID(), Gender.FEMALE);

        assertThat(personRepository.save(personToSave)).isEqualTo(personToSave);
    }
}