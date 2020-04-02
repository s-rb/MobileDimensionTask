package ru.mobiledimension.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mobiledimension.test.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT COUNT (p) > 0 FROM Person p WHERE p.documentNumber = :documentNumber")
    boolean isDocumentRegistered(@Param("documentNumber") String documentNumber);

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.friends WHERE p.id = :id")
    Person findWithFriends(@Param("id") Integer id);
}
