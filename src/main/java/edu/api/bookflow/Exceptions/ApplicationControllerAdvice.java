package edu.api.bookflow.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ControllerAdvice
public class ApplicationControllerAdvice {

    // Captura de exceção NotFoundObject
    @ExceptionHandler(NotFoundObject.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundObject ex) {
        return ex.getMessage();
    }

    // Captura de exceção MethodArgumentNotValidException para validação dos campos inseridos
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CustomErrorHandler> MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); // cria uma lista dos campos inválidos
        List<CustomErrorHandler> errorHandlerList = new ArrayList<>();
        fieldErrors.forEach(error -> errorHandlerList.add(new CustomErrorHandler(error.getField(), error.getDefaultMessage())));
        return errorHandlerList;
    }

    // Captura de exceção IllegalStateException para verificações específicas
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "BAD_REQUEST");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    //excecao para caso o usuário não seja encontrado
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> internalAuthenticationServiceException(InternalAuthenticationServiceException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "UNAUTHORIZED");
        body.put("message", "Usuário não encontrado");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    //excecao para caso senha invalida
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> badCredentialsException(BadCredentialsException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "UNAUTHORIZED");
        body.put("message", "Usuário ou senha inválidos");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    //excecao para caso usuario desabilitado
    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> disabledException(DisabledException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "FORBIDDEN");
        body.put("message", ex.getMessage()+". Contate o Administrador");
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}
