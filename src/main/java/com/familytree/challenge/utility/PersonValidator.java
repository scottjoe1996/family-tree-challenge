package com.familytree.challenge.utility;

import com.familytree.challenge.exceptions.GenderException;
import com.familytree.challenge.exceptions.ValidationException;
import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;

public class PersonValidator {

    public static void validateCouple(Person father, Person mother) {
        validateFather(father);
        validateMother(mother);
    }

    private static void validateFather(Person father) {
        validatePerson(father);

        if (father.getGender() != Gender.MALE) {
            throw new GenderException("Father must be a male");
        }
    }

    private static void validateMother(Person mother) {
        validatePerson(mother);

        if (mother.getGender() != Gender.FEMALE) {
            throw new GenderException("Mother must be a female");
        }
    }

    private static void validatePerson(Person person) {
        if (person == null) {
            throw new ValidationException("Person cannot be null");
        }
        if(person.getId() == null) {
            throw new ValidationException("Person id cannot be null");
        }

        if(person.getFatherId() == null) {
            throw new ValidationException("Person father id cannot be null");
        }

        if(person.getMotherId() == null) {
            throw new ValidationException("Person mother id cannot be null");
        }

        validateGender(person.getGender());
    }

    public static void validateGender(Gender gender) {
        if(gender == null) {
            throw new GenderException("Person gender cannot be null");
        }
    }
}
