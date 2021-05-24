package dev.tpcoder.springbootpoi.exception

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MaxUploadSizeExceededException
import java.lang.RuntimeException

internal class GlobalExceptionHandlerTest {

    private lateinit var globalExceptionHandler: GlobalExceptionHandler

    @BeforeEach
    fun setUpTest() {
        globalExceptionHandler = GlobalExceptionHandler()
    }

    @Test
    fun handleMaxSizeException() {
        val expectedText = "File too large!"
        val actual = globalExceptionHandler.handleMaxSizeException(MaxUploadSizeExceededException(1L))
        assertEquals(HttpStatus.EXPECTATION_FAILED, actual.statusCode)
        assertEquals(expectedText, actual.body?.errorDescription)
    }

    @Test
    fun testHandleMaxSizeException() {
        val errorText = "Test Runtime exception"
        val actual = globalExceptionHandler.handleException(RuntimeException(errorText))
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.statusCode)
        assertEquals(errorText, actual.body?.errorDescription)
    }
}