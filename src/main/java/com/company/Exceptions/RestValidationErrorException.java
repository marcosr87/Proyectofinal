package com.company.Exceptions;

/**
 * Created by alumno on 06/06/2018.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestValidationErrorException extends RuntimeException {

    @JsonProperty
    List<String> errors;

    public RestValidationErrorException(Set<ConstraintViolation<?>> errors) {
        super((errors != null) ? (errors.iterator() != null) ? (errors.iterator().hasNext() ? errors.iterator().next().getMessage() : null) : "" : "");
    }
}

