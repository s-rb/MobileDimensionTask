#Тестовое задание на позицию Java Junior Developer
##Описание
Задание представляет собой CRUD-сервис, обеспечивающий работу с сущностями Person.
Используемые технологии:
- Spring Boot
- Spring Core
- Spring Web-MVC
- Spring Data-JPA
- Hibernate
- Liquibase
- H2 RDBMS
- Lombok
- Mapstruct
- Maven

Для удобства тестирования, можно воспользоваться примерами запросов из файла Requests.http

## Задача
- Сделать класс `Person` сущностью, которая маппится на таблицу person, реализовать в нем связь многие-ко-многим, и, если необходимо, написать имплементацию equals/hashCode
- Изменить класс `PersonDTO` таким образом, чтобы в поле birthday в результирующем JSON'е имело формат дд-мм-гггг
- В классе `PersonService` реализовать методы `createPerson(PersonDTO personDTO)`, `delete(Integer id)`, `getPerson(Integer id)`, `addFriend(Integer id, Integer friendId)`
- В классе `PersonController` реализовать для всех методов принимаемый и возвращаемый формат JSON в кодировке UTF-8; реализовать метод `getPersons()`