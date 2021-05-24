package dev.tpcoder.springbootpoi.service

import dev.tpcoder.springbootpoi.repository.FoodSaleRepository
import dev.tpcoder.springbootpoi.service.impl.ReportServiceImpl
import dev.tpcoder.springbootpoi.util.constant.CommonConstant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.RuntimeException

@SpringBootTest
@ExtendWith(SpringExtension::class)
@DirtiesContext
internal class ReportServiceTest {

    @Autowired
    private lateinit var reportService: ReportServiceImpl

    @Autowired
    private lateinit var foodSaleRepository: FoodSaleRepository

    @Test
    fun uploadReport_success_returnEmpty() {
        // Given
        val resource = ClassPathResource("file/sampledatafoodsales.xlsx")
        val multipartFile = MockMultipartFile(
                resource.filename!!,
                resource.filename!!,
                CommonConstant.EXCEL_TYPE,
                resource.file.inputStream()
        )
        // When
        reportService.uploadReport(multipartFile)
        // Then
        val foodSaleList = foodSaleRepository.findAll()
        assertEquals(20, foodSaleList.size)
    }

    @Test
    fun uploadReport_fail_returnRuntimeException() {
        // Given
        val resource = ClassPathResource("file/sampledatafoodsales.xlsx")
        val multipartFile = MockMultipartFile(
                resource.filename!!,
                resource.file.inputStream()
        )
        // When
        assertThrows(RuntimeException::class.java) {
            reportService.uploadReport(multipartFile)
        }

    }
}