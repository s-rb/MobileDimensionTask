package ru.mobiledimension.test.mapper;

import org.mapstruct.*;
import ru.mobiledimension.test.domain.Person;
import ru.mobiledimension.test.dto.PersonDTO;
import ru.mobiledimension.test.dto.PersonSmallDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toFullDto(Person entity);
    PersonSmallDto toSmallDto(Person entity);

    @IterableMapping(elementTargetType = PersonSmallDto.class, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<PersonSmallDto> toSmallDto(List<Person> dtoList);


    @Mapping(target = "documentNumber", ignore = true)
    void updateEntity(@MappingTarget Person person, PersonDTO dto);
    Person toEntity(PersonDTO dto);
}
