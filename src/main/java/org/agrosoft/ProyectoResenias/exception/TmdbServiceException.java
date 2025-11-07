package org.agrosoft.ProyectoResenias.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class TmdbServiceException extends Exception {
    private final HttpStatusCode statusCode;
    private final String errorMessage;

    public TmdbServiceException(HttpStatusCode code, String errorMessage) {
        super(errorMessage);
        this.statusCode = code;
        this.errorMessage = errorMessage;
    }
}
