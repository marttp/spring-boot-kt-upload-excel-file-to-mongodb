package dev.tpcoder.springbootpoi.service

import dev.tpcoder.springbootpoi.model.FoodSale
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FoodSaleService {
    fun getAllFoodSalesPagination(pageable: Pageable): Page<FoodSale>
    fun getFoodSalesPagination(region: String?, pageable: Pageable): Page<FoodSale>
}