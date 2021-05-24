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

    // Upload excel file to MongoDB
    @PostMapping("/upload")
    fun uploadController(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        logger.info("Upload excel file name: {}", file.originalFilename)
        reportService.uploadReport(file = file)
        return ResponseEntity.ok("Upload excel file completed")
    }

}