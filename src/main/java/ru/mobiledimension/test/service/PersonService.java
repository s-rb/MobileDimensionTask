package ru.mobiledimension.test.service;

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

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Integer createPerson(PersonDTO personDTO) {
        //todo реализовать создание person'а, сделать проверку документа
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
        //todo реализовать удаление
    }

    public PersonDTO getPerson(Integer id) {
       //todo реализовать получение информации о person'е
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
       //todo реализовать одностороннее добавление в друзья. т.е. у person с id == id должен появиться друг с id == friendId, а у person с id == friendId - нет
    }
}
