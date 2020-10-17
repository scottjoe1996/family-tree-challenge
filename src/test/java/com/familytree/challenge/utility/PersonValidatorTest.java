package com.familytree.challenge.utility;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.familytree.challenge.exceptions.GenderException;
import com.familytree.challenge.exceptions.ValidationException;
import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonValidatorTest {
    private final Person validFather = new Person(UUID.randomUUID(), UUID.randomUUID(),
        UUID.randomUUID(), Gender.MALE);
    private final Person validMother = new Person(UUID.randomUUID(), UUID.randomUUID(),
        UUID.randomUUID(), Gender.FEMALE);

    @Test
    public void validateCoupleShouldThrowValidationExceptionWhenFatherIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            PersonValidator.validateCouple(null, validMother);
        });

        assertThat(exception.getMessage()).isEqualTo("Person cannot be null");
    }

    @Test
    public void validateCoupleShouldThrowValidationExceptionWhenMotherIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            PersonValidator.validateCouple(validFather, null);
        });

        assertThat(exception.getMessage()).isEqualTo("Person cannot be null");
    }

    @Test
    public void validateCoupleShouldNotThrowExceptionWhenCoupleIsValid() {
        assertDoesNotThrow(() -> PersonValidator.validateCouple(validFather, validMother));
    }

    @Test
    public void validateCoupleShouldThrowGenderExceptionWhenFatherIsFemale() {
        Exception exception = assertThrows(GenderException.class, () -> {
            PersonValidator.validateCouple(validMother, validMother);
        });

        assertThat(exception.getMessage()).isEqualTo("Father must be a male");
    }

    @Test
    public void validateCoupleShouldThrowGenderExceptionWhenMotherIsMale() {
        Exception exception = assertThrows(GenderException.class, () -> {
            PersonValidator.validateCouple(validFather, validFather);
        });

        assertThat(exception.getMessage()).isEqualTo("Mother must be a female");
    }

    @Test
    public void validateCoupleShouldThrowValidationExceptionWhenPersonIdIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            PersonValidator
                .validateCouple(new Person(null, UUID.randomUUID(), UUID.randomUUID(), Gender.MALE),
                    validMother);
        });

        assertThat(exception.getMessage()).isEqualTo("Person id cannot be null");
    }

    @Test
    public void validateCoupleShouldThrowValidationExceptionWhenPersonFatherIdIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            PersonValidator
                .validateCouple(new Person(UUID.randomUUID(), null, UUID.randomUUID(), Gender.MALE),
                    validMother);
        });

        assertThat(exception.getMessage()).isEqualTo("Person father id cannot be null");
    }

    @Test
    public void validateCoupleShouldThrowValidationExceptionWhenPersonMotherIdIsNull() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            PersonValidator
                .validateCouple(new Person(UUID.randomUUID(), UUID.randomUUID(), null, Gender.MALE),
                    validMother);
        });

        assertThat(exception.getMessage()).isEqualTo("Person mother id cannot be null");
    }

    @Test
    public void validateCoupleShouldThrowGenderExceptionWhenPersonGenderIsNull() {
        Exception exception = assertThrows(GenderException.class, () -> {
            PersonValidator.validateCouple(
                new Person(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), null),
                validMother);
        });

        assertThat(exception.getMessage()).isEqualTo("Person gender cannot be null");
    }
}