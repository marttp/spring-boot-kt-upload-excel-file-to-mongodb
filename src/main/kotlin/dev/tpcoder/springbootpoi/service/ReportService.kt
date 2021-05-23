package dev.tpcoder.springbootpoi.service

interface ReportService {
    fun generateXlsxReport(): ByteArray
}