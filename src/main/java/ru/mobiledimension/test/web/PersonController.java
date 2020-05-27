package ru.mobiledimension.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mobiledimension.test.dto.PersonDTO;
import ru.mobiledimension.test.dto.PersonSmallDto;
import ru.mobiledimension.test.service.PersonService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

//todo для всех точек принимаемый и возвращаемый тип должен быть json в кодировке UTF-8 - V

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> createPerson(@RequestBody PersonDTO person) {
        URI uri = URI.create("person/" + personService.createPerson(person));
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "person/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public void updatePerson(@PathVariable Integer id, @RequestBody PersonDTO person) {
        personService.updatePerson(id, person);
    }

    @DeleteMapping(name = "person/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public void deletePerson(@PathVariable Integer id) {
        personService.delete(id);
    }

    @GetMapping(name = "person", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PersonSmallDto>> getPersons() {
        return ResponseEntity.ok(personService.getPersons());
        //todo реализовать получение списка person'ов - V
    }

    @GetMapping(name = "person/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Integer id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @GetMapping(name = "person/{id}/friend", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PersonSmallDto>> getFriends(@PathVariable Integer id) {
        return ResponseEntity.ok(personService.getFriends(id));
    }

    @DeleteMapping(name = "person/{id}/friend/{friendId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public void deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        personService.deleteFriend(id, friendId);
    }

    @PostMapping(name = "person/{id}/friend/{friendId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(OK)
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        personService.addFriend(id, friendId);

    }
}
