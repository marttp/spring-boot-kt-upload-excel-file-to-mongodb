package dev.tpcoder.springbootpoi.controller

import dev.tpcoder.springbootpoi.controller.ReportController.Companion.BASE_REPORT_URL
import dev.tpcoder.springbootpoi.service.ReportService
import dev.tpcoder.springbootpoi.util.constant.CommonConstant
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(value = [BASE_REPORT_URL])
class ReportController(private val reportService: ReportService) {

    private val logger = LoggerFactory.getLogger(ReportController::class.java)

    companion object {
        const val BASE_REPORT_URL: String = "/api/v1/reports"
    }

    // Upload excel file to MongoDB
    @PostMapping("/upload")
    fun uploadExcel(@RequestParam("file") file: MultipartFile): ResponseEntity<Void> {
        logger.info("Upload excel file name: {}", file.originalFilename)
        reportService.uploadReport(file = file)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

}