package dev.tpcoder.springbootpoi.service

import dev.tpcoder.springbootpoi.model.FoodSale
import dev.tpcoder.springbootpoi.repository.FoodSaleRepository
import dev.tpcoder.springbootpoi.service.impl.FoodSaleServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
internal class FoodSaleServiceTest {

    @InjectMocks
    private lateinit var foodSaleService: FoodSaleServiceImpl

    @Mock
    private lateinit var foodSaleRepository: FoodSaleRepository

    @Test
    fun getAllFoodSalesPagination_success_returnPage() {
        // Given
        val mockFoodSaleList = listOf(FoodSale(region = "East"), FoodSale(region = "West"), FoodSale(region = "East"))
        val foodSalePage = PageImpl(mockFoodSaleList)
        val paging: Pageable = PageRequest.of(0, 5)
        // When
        Mockito.`when`(foodSaleRepository.findAll(paging)).thenReturn(foodSalePage)
        // Then
        val actual = foodSaleService.getAllFoodSalesPagination(paging)
        val items: List<FoodSale> = actual.content
        val pageable: Pageable = actual.pageable
        assertEquals(3, items.size)
        assertNotNull(pageable)
    }

    @Test
    fun getFoodSalesWithRegionPagination_success_returnPage() {
        // Given
        val mockFoodSaleList = listOf(FoodSale(region = "East"), FoodSale(region = "East"))
        val foodSalePage = PageImpl(mockFoodSaleList)
        val paging: Pageable = PageRequest.of(0, 5)
        // When
        Mockito.`when`(foodSaleRepository.findAllByRegionContainingIgnoreCase("east", paging))
                .thenReturn(foodSalePage)
        // Then
        val actual = foodSaleService.getFoodSalesWithRegionPagination("east", paging)
        val items: List<FoodSale> = actual.content
        val pageable: Pageable = actual.pageable
        assertEquals(2, items.size)
        assertNotNull(pageable)
    }
}