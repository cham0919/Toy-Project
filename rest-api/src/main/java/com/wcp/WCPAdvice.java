package com.wcp;

import com.wcp.error.ErrorResponse;
import org.apache.tika.mime.MimeTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

@RestControllerAdvice
public class WCPAdvice {

    private final Logger log = LoggerFactory.getLogger(WCPAdvice.class);

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> numberFormatException(NumberFormatException e) {
        log.error("key should not be empty or String. Please Check key", e);

        final ErrorResponse response
                = ErrorResponse
                .create()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorResponse> loginException(LoginException e) {
        log.error("Please check id and pw", e);

        final ErrorResponse response
                = ErrorResponse
                .create()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> throwable(Throwable t) {
        log.error(t.getMessage(), t);

        final ErrorResponse response
                = ErrorResponse
                .create()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
