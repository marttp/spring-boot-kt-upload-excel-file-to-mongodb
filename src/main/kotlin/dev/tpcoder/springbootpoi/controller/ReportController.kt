package dev.tpcoder.springbootpoi.controller

import dev.tpcoder.springbootpoi.service.impl.ReportServiceImpl
import dev.tpcoder.springbootpoi.util.constant.CommonConstant
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
class ReportController(private val reportService: ReportServiceImpl) {

    // Do by own
    @PostMapping("/upload")
    fun uploadController(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        return ResponseEntity.ok("Test")
    }


    // Sample of download excel file
    @PostMapping("/xlsx")
    fun generateXlsxReport(): ResponseEntity<ByteArray> {
        val report = reportService.generateXlsxReport()
        return createResponseEntity(report, CommonConstant.REPORT_FILE_NAME)
    }

    private fun createResponseEntity(
            report: ByteArray,
            fileName: String
    ): ResponseEntity<ByteArray> =
            ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"$fileName\"")
                    .body(report)
}