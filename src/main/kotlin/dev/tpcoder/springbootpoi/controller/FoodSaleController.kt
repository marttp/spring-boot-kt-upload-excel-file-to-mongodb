package dev.tpcoder.springbootpoi.controller

import dev.tpcoder.springbootpoi.controller.FoodSaleController.Companion.BASE_FOOD_SALE_URL
import dev.tpcoder.springbootpoi.service.FoodSaleService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [BASE_FOOD_SALE_URL])
class FoodSaleController(private val foodSaleService: FoodSaleService) {

    private val logger = LoggerFactory.getLogger(FoodSaleController::class.java)

    companion object {
        const val BASE_FOOD_SALE_URL: String = "/api/food-sales"
    }

    @GetMapping
    fun getFoodSalesPage(
            @RequestParam(required = false) region: String? = "",
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "3") size: Int): ResponseEntity<Map<String, Any>> {
        logger.info("Get food sales data as pagination")
        val paging: Pageable = PageRequest.of(page, size)
        val pageFoodSales = if (region.isNullOrEmpty()) {
            foodSaleService.getAllFoodSalesPagination(paging)
        } else {
            foodSaleService.getFoodSalesWithRegionPagination(region, paging)
        }
        return ResponseEntity.ok(mapOf(
                "items" to pageFoodSales.content,
                "pageable" to pageFoodSales.pageable
        ))
    }

}