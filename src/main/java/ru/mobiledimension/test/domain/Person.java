package ru.mobiledimension.test.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

//todo сделать этот класс сущностью, которая маппится на таблицу person - V
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document_number")
    @NonNull private String documentNumber;

    @Column(name = "first_name")
    @NonNull private String firstName;

    @Column(name = "last_name")
    @NonNull private String lastName;

    private LocalDate birthday;

    //todo реализовать связь многие-ко-многим через промежуточную таблицу person_friend
    @ManyToMany
    @JoinTable(name = "person_friend",
    joinColumns = @JoinColumn(name = "person_id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Person> friends;

    //todo: если необходимо, реализовать equals и hashcode - V
    // Возможно не требуется, так как нигде не сравниваются объекты Person

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        // Если равны Фамилия, имя и документ, то принимаем что пользователи равны
        return Objects.equals(documentNumber, person.documentNumber) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, firstName, lastName);
    }
}
