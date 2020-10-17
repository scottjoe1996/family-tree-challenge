package com.familytree.challenge.service;

import com.familytree.challenge.exceptions.FamilyTreeException;
import com.familytree.challenge.exceptions.WrongFamilyTreeException;
import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;
import com.familytree.challenge.repository.PersonRepo;
import com.familytree.challenge.utility.PersonValidator;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Person addChild(Person father, Person mother, Gender gender) {
        PersonValidator.validateCouple(father, mother);
        PersonValidator.validateGender(gender);
        try {
            validateFamilyTreeContext(father, mother);
        } catch (FamilyTreeException exception) {
            personRepo.save(exception.getPerson());
        }

        return personRepo.save(new Person(UUID.randomUUID(), father.getId(), mother.getId(),
            gender));
    }

    private void validateFamilyTreeContext(Person father, Person mother) {
        Person foundFather = personRepo.findById(father.getId());
        Person foundMother = personRepo.findById(mother.getId());

        if (isAFamilyMember(foundFather) | isAFamilyMember(foundMother)) {
            if (foundFather == null) {
                throw new FamilyTreeException("Father is not in family tree", father);
            }
            throw new FamilyTreeException("Mother is not in family tree", mother);
        } else {
            throw new WrongFamilyTreeException();
        }
    }

    private boolean isAFamilyMember(Person person) {
        return person != null;
    }
}
