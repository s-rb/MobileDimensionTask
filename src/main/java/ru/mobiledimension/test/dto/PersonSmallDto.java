package ru.mobiledimension.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonSmallDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
