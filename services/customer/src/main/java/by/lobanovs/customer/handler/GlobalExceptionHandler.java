package by.lobanovs.customer.handler;


import by.lobanovs.customer.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
// Глобальный обработчик исключений для контроллеров
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Обработчик исключения CustomerNotFoundException
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    // Обработчик исключения MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(MethodArgumentNotValidException e) {

        // Создаем карту для хранения ошибок валидации
        var errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors() // Получаем список всех ошибок
                .forEach(error -> {

            // Преобразуем каждую ошибку в пару "имя поля - сообщение ошибки"
            var fieldName = ((FieldError)error).getField(); // Имя поля, вызвавшего ошибку
            var errorMessage = error.getDefaultMessage(); // Сообщение об ошибке
            errors.put(fieldName, errorMessage);
        });
        // Возвращаем HTTP 400 Bad Request с подробностями ошибок в теле ответа
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errors)); // Передаем объект ErrorResponse в тело ответа
    }
}
