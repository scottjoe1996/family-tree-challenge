package com.familytree.challenge.controller;

import com.familytree.challenge.models.AddChildRequest;
import com.familytree.challenge.models.Gender;
import com.familytree.challenge.models.Person;
import com.familytree.challenge.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addChild(@RequestBody AddChildRequest addChildRequest) {
        Person father = addChildRequest.getFather();
        Person mother = addChildRequest.getMother();
        Gender gender = addChildRequest.getGender();

        Person childAdded = personService.addChild(father, mother, gender);

        return new ResponseEntity<>(childAdded, HttpStatus.CREATED);
    }
}
