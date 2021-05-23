package dev.tpcoder.springbootpoi.exception

data class ErrorResponse(
        val error: String,
        val errorStatus: Int,
        val errorDescription: String)
