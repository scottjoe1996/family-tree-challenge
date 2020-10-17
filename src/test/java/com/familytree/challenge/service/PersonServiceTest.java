package com.familytree.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.familytree.challenge.exceptions.ValidationException;
import com.familytree.challenge.exceptions.WrongFamilyTreeException;
import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;
import com.familytree.challenge.repository.PersonRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;
    private final Person familyFather = new Person(UUID.randomUUID(), UUID.randomUUID(),
        UUID.randomUUID(), Gender.MALE);
    private final Person familyMother = new Person(UUID.randomUUID(), UUID.randomUUID(),
        UUID.randomUUID(), Gender.FEMALE);

    @BeforeEach
    public void setUp() {
        personRepository.save(familyFather);
        personRepository.save(familyMother);
    }

    @Test
    public void addChildShouldReturnExpectedFemaleChildWithValidParents() {
        Person child = personService.addChild(familyFather, familyMother, Gender.FEMALE);

        assertThat(child.getFatherId()).isEqualTo(familyFather.getId());
        assertThat(child.getMotherId()).isEqualTo(familyMother.getId());
        assertThat(child.getGender()).isEqualTo(Gender.FEMALE);
    }

    @Test
    public void addChildShouldReturnExpectedMaleChildWithValidParents() {
        Person child = personService.addChild(familyFather, familyMother, Gender.MALE);

        assertThat(child.getFatherId()).isEqualTo(familyFather.getId());
        assertThat(child.getMotherId()).isEqualTo(familyMother.getId());
        assertThat(child.getGender()).isEqualTo(Gender.MALE);
    }

    @Test
    public void addChildShouldThrowWrongFamilyTreeExceptionWithParentsNotFromFamilyTree() {
        Person nonFamilyFather = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.MALE);
        Person nonFamilyMother = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.FEMALE);

        Exception exception = assertThrows(WrongFamilyTreeException.class, () -> {
            personService.addChild(nonFamilyFather, nonFamilyMother, Gender.MALE);
        });

        assertThat(exception.getMessage()).isEqualTo("Wrong family tree");
    }

    @Test
    public void addChildShouldReturnExpectedChildWithFatherNotFromFamilyTree() {
        Person nonFamilyFather = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.MALE);

        Person child = personService.addChild(nonFamilyFather, familyMother, Gender.MALE);

        assertThat(child.getFatherId()).isEqualTo(nonFamilyFather.getId());
        assertThat(child.getMotherId()).isEqualTo(familyMother.getId());
        assertThat(child.getGender()).isEqualTo(Gender.MALE);
    }

    @Test
    public void addChildShouldAddFatherNotFromFamilyTreeToPersonDatabase() {
        Person nonFamilyFather = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.MALE);

        personService.addChild(nonFamilyFather, familyMother, Gender.MALE);

        Person addedFather = personRepository.findById(nonFamilyFather.getId());

        assertThat(addedFather).isEqualTo(nonFamilyFather);
    }

    @Test
    public void addChildShouldReturnExpectedChildWithMotherNotFromFamilyTree() {
        Person nonFamilyMother = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.FEMALE);

        Person child = personService.addChild(familyFather, nonFamilyMother, Gender.MALE);

        assertThat(child.getFatherId()).isEqualTo(familyFather.getId());
        assertThat(child.getMotherId()).isEqualTo(nonFamilyMother.getId());
        assertThat(child.getGender()).isEqualTo(Gender.MALE);
    }

    @Test
    public void addChildShouldAddMotherNotFromFamilyTreeToPersonDatabase() {
        Person nonFamilyMother = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            Gender.FEMALE);

        personService.addChild(familyFather, nonFamilyMother, Gender.MALE);

        Person addedFather = personRepository.findById(nonFamilyMother.getId());

        assertThat(addedFather).isEqualTo(nonFamilyMother);
    }

    @Test
    public void addChildShouldThrowValidationExceptionWithNullFather() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(null, familyMother, Gender.MALE);
        });

        assertThat(exception.getMessage()).isEqualTo("Person cannot be null");
    }

    @Test
    public void addChildShouldThrowValidationExceptionWithNullMother() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(familyFather, null, Gender.MALE);
        });

        assertThat(exception.getMessage()).isEqualTo("Person cannot be null");
    }

    @Test
    public void addChildShouldThrowValidationExceptionWithNullGender() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(familyFather, familyMother, null);
        });

        assertThat(exception.getMessage()).isEqualTo("Person gender cannot be null");
    }

    @Test
    public void addChildShouldThrowValidationExceptionWhenPersonIdIsNull() {
        Person invalidFather = new Person(null, UUID.randomUUID(), UUID.randomUUID(), Gender.MALE);

        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(invalidFather, familyMother, null);
        });

        assertThat(exception.getMessage()).isEqualTo("Person id cannot be null");
    }

    @Test
    public void addChildShouldThrowValidationExceptionWhenPersonFatherIdIsNull() {
        Person invalidFather = new Person(UUID.randomUUID(), null, UUID.randomUUID(), Gender.MALE);

        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(invalidFather, familyMother, null);
        });

        assertThat(exception.getMessage()).isEqualTo("Person father id cannot be null");
    }

    @Test
    public void addChildShouldThrowValidationExceptionWhenPersonMotherIdIsNull() {
        Person invalidFather = new Person(UUID.randomUUID(), UUID.randomUUID(), null, Gender.MALE);

        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(invalidFather, familyMother, null);
        });

        assertThat(exception.getMessage()).isEqualTo("Person mother id cannot be null");
    }

    @Test
    public void addChildShouldThrowValidationExceptionWhenPersonGenderIsNull() {
        Person invalidFather = new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
            null);

        Exception exception = assertThrows(ValidationException.class, () -> {
            personService.addChild(invalidFather, familyMother, null);
        });

        assertThat(exception.getMessage()).isEqualTo("Person gender cannot be null");
    }
}