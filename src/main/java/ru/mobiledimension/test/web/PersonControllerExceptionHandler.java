package ru.mobiledimension.test.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.mobiledimension.test.exception.DocumentAlreadyRegisteredException;
import ru.mobiledimension.test.exception.PersonNotFoundException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Log4j2
public class PersonControllerExceptionHandler {

    @ExceptionHandler(value = DocumentAlreadyRegisteredException.class)
    public ResponseEntity<?> handleDocumentAlreadyRegisteredException(DocumentAlreadyRegisteredException exception) {
        log.warn(exception);
        return errorResponse("Документ уже зарегистрирован у другого пользователя", CONFLICT);
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<?> handlePersonNotFoundException(PersonNotFoundException exception) {
        log.warn(exception);
        return errorResponse("Пользователь не найден", NOT_FOUND);
    }

    private ResponseEntity<?> errorResponse(String message, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Error-Msg", message);

        return new ResponseEntity<>(headers, status);
    }
}
