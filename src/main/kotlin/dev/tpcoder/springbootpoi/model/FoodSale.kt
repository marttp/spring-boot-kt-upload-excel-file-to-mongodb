package dev.tpcoder.springbootpoi.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate

@Document(collection = "food_sale")
data class FoodSale(
        val id: ObjectId? = null,
        var orderDate: LocalDate? = null,
        var region: String? = null,
        var city: String? = null,
        var category: String? = null,
        var product: String? = null,
        var quantity: Int? = null,
        var unitPrice: BigDecimal? = null,
        var totalPrice: BigDecimal? = null)