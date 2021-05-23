package dev.tpcoder.springbootpoi.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate

@Document(collection = "food_sale")
data class FoodSale(
        private val id: ObjectId,
        private val orderDate: LocalDate,
        private val region: String,
        private val city: String,
        private val category: String,
        private val product: String,
        private val quantity: Int,
        private val unitPrice: BigDecimal,
        private val totalPrice: BigDecimal)