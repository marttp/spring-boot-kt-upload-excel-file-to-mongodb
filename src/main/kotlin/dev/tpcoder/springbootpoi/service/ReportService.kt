package dev.tpcoder.springbootpoi.service

import org.springframework.web.multipart.MultipartFile

interface ReportService {
    fun generateXlsxReport(): ByteArray
    fun uploadReport(file: MultipartFile)
}