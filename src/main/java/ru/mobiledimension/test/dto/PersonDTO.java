package ru.mobiledimension.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String documentNumber;
    private String firstName;
    private String lastName;
    //todo день рождения должен быть в формате дд-мм-гггг
    private LocalDate birthday;
}
