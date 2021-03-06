package com.homestars.ChannelService.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@RestControllerAdvice(basePackages = {"com.homestars.UserService"} )
@ControllerAdvice(basePackages = {"com.homestars.UserService"} )
public class ValidatorControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @ResponseBody
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> authExceptionHandler(APIException ex, WebRequest request) {
        logger.error(ex.toString());
        return ResponseEntity.ok(ex.getLocalizedMessage());
    }
} 