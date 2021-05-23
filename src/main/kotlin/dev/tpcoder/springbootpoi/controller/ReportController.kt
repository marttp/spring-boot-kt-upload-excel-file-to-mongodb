package dev.tpcoder.springbootpoi.controller

import dev.tpcoder.springbootpoi.service.ReportService
import dev.tpcoder.springbootpoi.util.constant.CommonConstant
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/reports")
class ReportController(private val reportService: ReportService) {

    private val logger = LoggerFactory.getLogger(ReportController::class.java)

    // Do by own
    @PostMapping("/upload")
    fun uploadController(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        logger.info("Upload excel file name: {}", file.originalFilename)
        reportService.uploadReport(file = file)
        return ResponseEntity.ok("Test")
    }

    // Sample of download excel file
    @PostMapping("/xlsx")
    fun generateXlsxReport(): ResponseEntity<ByteArray> {
        logger.info("Generate sample excel report")
        val report = reportService.generateXlsxReport()
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${CommonConstant.REPORT_FILE_NAME}\"")
                .body(report)
    }
}