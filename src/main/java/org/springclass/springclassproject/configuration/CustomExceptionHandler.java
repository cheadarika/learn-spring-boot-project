package org.springclass.springclassproject.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springclass.springclassproject.exception.PlatformApiDataValidationException;
import org.springclass.springclassproject.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpStatusCodeException.class})
    protected ResponseEntity<Object> handleHttpException(final HttpStatusCodeException e, final WebRequest request) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final Map<String, String> content = new LinkedHashMap<>();
        content.put("timestamp", String.valueOf(System.currentTimeMillis()));
        content.put("status", String.valueOf(e.getStatusCode().value()));
        content.put("error", e.getStatusCode().toString());
        content.put("message", e.getStatusText());
        content.put("path", request.getContextPath());

        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(content), headers, e.getStatusCode());
        } catch (Exception ex) {
            log.error("Error serializing the response body", ex);
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler({PlatformApiDataValidationException.class})
    protected ResponseEntity<Object> handlePlatformError(final PlatformApiDataValidationException e) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final Map<String, String> content = new LinkedHashMap<>();
        content.put("timestamp", String.valueOf(System.currentTimeMillis()));
        content.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        content.put("error", e.getError());
        content.put("message", e.getMessage());

        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(content), headers, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error("Error serializing the response body", ex);
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(final ResourceNotFoundException e) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final Map<String, String> content = new LinkedHashMap<>();
        content.put("timestamp", String.valueOf(System.currentTimeMillis()));
        content.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        content.put("error", e.getError());
        content.put("message", e.getMessage());

        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(content), headers, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Error serializing the response body", ex);
            return new ResponseEntity<>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
