package dev.tpcoder.springbootpoi.service.impl

import dev.tpcoder.springbootpoi.model.FoodSale
import dev.tpcoder.springbootpoi.repository.FoodSaleRepository
import dev.tpcoder.springbootpoi.service.FoodSaleService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FoodSaleServiceImpl(private val foodSaleRepository: FoodSaleRepository) : FoodSaleService {
    override fun getAllFoodSalesPagination(pageable: Pageable): Page<FoodSale> =
            foodSaleRepository.findAll(pageable)

    override fun getFoodSalesPagination(region: String?, pageable: Pageable): Page<FoodSale> =
            foodSaleRepository.findAllByRegionContainingIgnoreCase(region, pageable)
}