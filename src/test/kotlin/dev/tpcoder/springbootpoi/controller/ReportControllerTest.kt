package dev.tpcoder.springbootpoi.controller

import dev.tpcoder.springbootpoi.service.ReportService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile

@ExtendWith(MockitoExtension::class)
internal class ReportControllerTest {

    @InjectMocks
    private lateinit var reportController: ReportController

    @Mock
    private lateinit var reportService: ReportService

    @Test
    fun uploadExcel_success_returnAcceptedResponse() {
        // Given
        val resource = ClassPathResource("file/sampledatafoodsales.xlsx")
        val multipartFile = MockMultipartFile(resource.filename!!, resource.file.inputStream())
        // When
        Mockito.doNothing().`when`(reportService).uploadReport(multipartFile)
        // Then
        val actual = reportController.uploadExcel(multipartFile)
        assertTrue(actual.statusCode == HttpStatus.ACCEPTED)
    }

}