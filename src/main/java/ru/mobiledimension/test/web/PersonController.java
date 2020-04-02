package ru.mobiledimension.test.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mobiledimension.test.dto.PersonDTO;
import ru.mobiledimension.test.dto.PersonSmallDto;
import ru.mobiledimension.test.service.PersonService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

//todo для всех точек принимаемый и возвращаемый тип должен быть json в кодировке UTF-8

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("person")
    public ResponseEntity<Integer> createPerson (@RequestBody PersonDTO person) {
        URI uri = URI.create("person/" + personService.createPerson(person));
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("person/{id}")
    @ResponseStatus(OK)
    public void updatePerson(@PathVariable Integer id, @RequestBody PersonDTO person) {
        personService.updatePerson(id, person);
    }

    @DeleteMapping("person/{id}")
    @ResponseStatus(OK)
    public void deletePerson(@PathVariable Integer id) {
        personService.delete(id);
    }

    @GetMapping("person")
    public ResponseEntity<List<PersonSmallDto>> getPersons() {
        //todo реализовать получение списка person'ов
    }

    @GetMapping("person/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Integer id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @GetMapping("person/{id}/friend")
    public ResponseEntity<List<PersonSmallDto>> getFriends(@PathVariable Integer id) {
        return ResponseEntity.ok(personService.getFriends(id));
    }

    @DeleteMapping("person/{id}/friend/{friendId}")
    @ResponseStatus(OK)
    public void deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        personService.deleteFriend(id, friendId);
    }

    @PostMapping("person/{id}/friend/{friendId}")
    @ResponseStatus(OK)
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        personService.addFriend(id, friendId);

    }
}
