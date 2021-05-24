package dev.tpcoder.springbootpoi.controller

import dev.tpcoder.springbootpoi.model.FoodSale
import dev.tpcoder.springbootpoi.service.FoodSaleService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
internal class FoodSaleControllerTest {

    @InjectMocks
    private lateinit var foodSaleController: FoodSaleController

    @Mock
    private lateinit var foodSaleService: FoodSaleService

    @Test
    fun getFoodSalesPage_success_returnItemsPagination() {
        // Given
        val mockFoodSaleList = listOf(FoodSale(region = "East"), FoodSale(region = "East"))
        val foodSalePage = PageImpl(mockFoodSaleList)
        val paging: Pageable = PageRequest.of(0, 5)
        // When
        Mockito.`when`(foodSaleService.getFoodSalesWithRegionPagination("east", paging))
                .thenReturn(foodSalePage)
        // Then
        val actual = foodSaleController.getFoodSalesPage("east", 0, 5)

        assertTrue(actual.statusCode == HttpStatus.OK)
        val items: List<FoodSale> = actual.body?.get("items") as List<FoodSale>
        val pageable: Pageable = actual.body?.get("pageable") as Pageable
        assertEquals(2, items.size)
        assertNotNull(pageable)
    }

    @Test
    fun getFoodSalesPageWithNonRegion_success_returnItemsPagination() {
        // Given
        val mockFoodSaleList = listOf(FoodSale(region = "East"), FoodSale(region = "West"), FoodSale(region = "East"))
        val foodSalePage = PageImpl(mockFoodSaleList)
        val paging: Pageable = PageRequest.of(0, 5)
        // When
        Mockito.`when`(foodSaleService.getAllFoodSalesPagination(paging))
                .thenReturn(foodSalePage)
        // Then
        val actual = foodSaleController.getFoodSalesPage(null, 0, 5)

        assertTrue(actual.statusCode == HttpStatus.OK)
        val items: List<FoodSale> = actual.body?.get("items") as List<FoodSale>
        val pageable: Pageable = actual.body?.get("pageable") as Pageable
        assertEquals(3, items.size)
        assertNotNull(pageable)
    }
}