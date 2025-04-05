package com.group1.vipbilliardspayment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.group1.vipbilliardspayment.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorCode errorCode = ErrorCode.valueOf(exception.getFieldError().getDefaultMessage());
        ApiResponse apiResponse = ApiResponse.builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
    
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ApiResponse apiResponse = ApiResponse.builder()
            .code(exception.getErrorCode().getCode())
            .message(exception.getErrorCode().getMessage())
            .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
