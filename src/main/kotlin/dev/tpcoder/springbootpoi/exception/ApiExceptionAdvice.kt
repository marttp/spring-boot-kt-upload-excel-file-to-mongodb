package dev.tpcoder.springbootpoi.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

import org.springframework.web.multipart.MaxUploadSizeExceededException
import java.lang.RuntimeException


@ControllerAdvice
class ApiExceptionAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxSizeException(exc: MaxUploadSizeExceededException?): ResponseEntity<ErrorResponse> =
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ErrorResponse("Max upload file size error", HttpStatus.EXPECTATION_FAILED.value(), "File too large!"))

    @ExceptionHandler(RuntimeException::class)
    fun handleMaxSizeException(exc: RuntimeException?): ResponseEntity<ErrorResponse> =
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"))
}