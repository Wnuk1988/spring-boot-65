package com.tms.execption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionResolver {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<HttpStatus> userNotFound() {
        log.info("UserNotFound exception!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<HttpStatus> illegalArgumentException() {
        log.info("IllegalArgumentException exception!");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OptimisticLockingFailureException.class)
    public ResponseEntity<HttpStatus> optimisticLockingFailureException() {
        log.info("OptimisticLockingFailureException exception!");
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
