package dev.tpcoder.springbootpoi.exception

data class ErrorResponse(
        private val error: String,
        private val errorStatus: Int,
        private val errorDescription: String)
