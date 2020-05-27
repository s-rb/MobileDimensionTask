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

    @PostMapping(path = "person")
    public ResponseEntity<Integer> createPerson(@RequestBody PersonDTO person) {
        System.out.println("===> " + person.getDocumentNumber() + " " + person.getLastName());
        URI uri = URI.create("person/" + personService.createPerson(person));
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "person/{id}")
    @ResponseStatus(OK)
    public void updatePerson(@PathVariable Integer id, @RequestBody PersonDTO person) {
        personService.updatePerson(id, person);
    }

    @DeleteMapping(path = "person/{id}")
    @ResponseStatus(OK)
    public void deletePerson(@PathVariable("id") Integer id) {
        personService.delete(id);
    }

    @GetMapping(path = "person")
    public ResponseEntity<List<PersonSmallDto>> getPersons() {
        return ResponseEntity.ok(personService.getPersons());
        //todo реализовать получение списка person'ов - V
    }

    @GetMapping(name = "person/{id}", path = "person/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @GetMapping(name = "person/{id}/friend", path = "person/{id}/friend")
    public ResponseEntity<List<PersonSmallDto>> getFriends(@PathVariable(value = "id", required = true) Integer id) {
        return ResponseEntity.ok(personService.getFriends(id));
    }

    @DeleteMapping(path = "person/{id}/friend/{friendId}")
    @ResponseStatus(OK)
    public void deleteFriend(@PathVariable("id") Integer id, @PathVariable("friendId") Integer friendId) {
        personService.deleteFriend(id, friendId);
    }

    @PostMapping(path = "person/{id}/friend/{friendId}")
    @ResponseStatus(OK)
    public void addFriend(@PathVariable("id") Integer id, @PathVariable("friendId") Integer friendId) {
        personService.addFriend(id, friendId);

    }
}
