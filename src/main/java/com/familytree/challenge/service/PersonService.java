package com.familytree.challenge.service;

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

//    public Person addChild(Person father, Person mother, Gender gender) {
//        PersonValidator.validateCouple(father, mother);
//        validateFamilyTreeContext(father, mother);
//
//        personRepo.save(new Person(UUID.randomUUID(), father.getId(), mother.getId(), gender));
//    }
//
//    private void validateFamilyTreeContext(Person father, Person mother) {
//        if (!(isFamilyMember(father) && isFamilyMember(mother))) {
//            throw new FamilyTreeException("One person needs to exist in the family tree");
//        }
//    }
//
//    private boolean isFamilyMember(Person person) {
//        return personRepo.findById(person.getId()) != null;
//    }
}
