package ru.mobiledimension.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mobiledimension.test.domain.Person;
import ru.mobiledimension.test.dto.PersonDTO;
import ru.mobiledimension.test.dto.PersonSmallDto;
import ru.mobiledimension.test.exception.DocumentAlreadyRegisteredException;
import ru.mobiledimension.test.exception.PersonNotFoundException;
import ru.mobiledimension.test.mapper.PersonMapper;
import ru.mobiledimension.test.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Integer createPerson(PersonDTO personDTO) {
        //todo реализовать создание person'а, сделать проверку документа
        String documentNumber = personDTO.getDocumentNumber();
        if (!isDocumentValid(documentNumber)) throw new DocumentAlreadyRegisteredException(documentNumber);
        Person person = personMapper.toEntity(personDTO);
        return personRepository.save(person).getId();
    }

    private boolean isDocumentValid(String documentNumber) {
        return !personRepository.isDocumentRegistered(documentNumber);
    }

    @Transactional
    public void updatePerson(Integer id, PersonDTO dto) {
        Person person = personRepository.getOne(id);
        if (person == null) {
            throw new PersonNotFoundException(id);
        }

        personMapper.updateEntity(person, dto);
        personRepository.save(person);
    }

    public List<PersonSmallDto> getPersons() {
        List<Person> all = personRepository.findAll();
        return personMapper.toSmallDto(all);
    }

    public void delete(Integer id) {
        if (id == null) return;
        personRepository.deleteById(id);
        //todo реализовать удаление
    }

    public PersonDTO getPerson(Integer id) {
       //todo реализовать получение информации о person'е
        if (id == null) return null;
        Person person = personRepository.findWithFriends(id);
        return person != null ? personMapper.toFullDto(person) : null;
    }

    public List<PersonSmallDto> getFriends(Integer id) {
        Person personWithFriends = personRepository.findWithFriends(id);
        return personMapper.toSmallDto(personWithFriends.getFriends());
    }

    @Transactional
    public void deleteFriend(Integer id, Integer friendId) {
        Person person = personRepository.findWithFriends(id);
        person.getFriends().removeIf(p -> p.getId().equals(friendId));
        personRepository.save(person);
    }

    @Transactional
    public void addFriend(Integer id, Integer friendId) {
        if (id == null || friendId == null) return;
        Person person = personRepository.findWithFriends(id);
        Person friend = personRepository.getOne(friendId);
        person.getFriends().add(friend);
        personRepository.save(person);
       //todo реализовать одностороннее добавление в друзья. т.е. у person с id == id должен появиться друг с id == friendId, а у person с id == friendId - нет
    }
}
