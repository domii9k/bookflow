package edu.api.bookflow.Exceptions;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiHttpResponse {

    public static ResponseEntity<Object> responseStatus(HttpStatus httpStatus, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", httpStatus.value());
        body.put("response", message);

        return new ResponseEntity<>(body, httpStatus);
    }
}
