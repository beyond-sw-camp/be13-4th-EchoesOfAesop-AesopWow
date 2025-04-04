package com.aesopwow.echoesofaesop.common;

import com.aesopwow.echoesofaesop.auth.exception.UnauthorizeException;
import com.aesopwow.echoesofaesop.common.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleException(Exception e) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        false,
                        null
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ResponseDto<Void>> handleValidationException(HandlerMethodValidationException e) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        false,
                        null
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage(),
                        false,
                        null
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<ResponseDto<Void>> handleAuthorizeException(UnauthorizeException e) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage(),
                        false,
                        null
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
