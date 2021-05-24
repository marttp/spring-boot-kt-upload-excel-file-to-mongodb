package dev.tpcoder.springbootpoi.service.impl

import dev.tpcoder.springbootpoi.repository.FoodSaleRepository
import dev.tpcoder.springbootpoi.service.ReportService
import dev.tpcoder.springbootpoi.util.ExcelHelper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ReportServiceImpl(private val foodSaleRepository: FoodSaleRepository) : ReportService {

    private val logger = LoggerFactory.getLogger(ReportServiceImpl::class.java)

    override fun uploadReport(file: MultipartFile) {
        logger.info("Upload report service call")
        if (ExcelHelper.hasExcelFormat(file)) {
            logger.info("Start excel to FoodSales...")
            val foodSaleList = ExcelHelper.excelToFoodSale(file)
            foodSaleRepository.saveAll(foodSaleList)
        } else {
            throw RuntimeException("Not excel format")
        }
    }

}