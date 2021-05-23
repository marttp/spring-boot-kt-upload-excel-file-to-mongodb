package dev.tpcoder.springbootpoi.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import org.springframework.web.multipart.MaxUploadSizeExceededException
import java.lang.RuntimeException


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(MaxUploadSizeExceededException::class)])
    fun handleMaxSizeException(exc: MaxUploadSizeExceededException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Max upload file size error", HttpStatus.EXPECTATION_FAILED.value(), "File too large!")
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errorResponse)
    }


    @ExceptionHandler(value = [(RuntimeException::class), (Exception::class)])
    fun handleMaxSizeException(exc: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.message
                ?: "")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}