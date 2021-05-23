package dev.tpcoder.springbootpoi.util

import dev.tpcoder.springbootpoi.model.FoodSale
import dev.tpcoder.springbootpoi.util.constant.CommonConstant
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.springframework.web.multipart.MultipartFile
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import org.apache.poi.ss.usermodel.Workbook
import java.io.IOException
import java.lang.Exception
import java.lang.RuntimeException
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.ZoneId
import java.util.logging.Logger

object ExcelHelper {

    private val logger = Logger.getLogger(ExcelHelper::class.java.name)

    fun hasExcelFormat(file: MultipartFile): Boolean = CommonConstant.EXCEL_TYPE == file.contentType

    fun excelToFoodSale(file: MultipartFile): List<FoodSale> {
        try {
            val workbook: Workbook = XSSFWorkbook(file.inputStream)
            val sheet: Sheet = workbook.getSheet(CommonConstant.EXCEL_FOOD_SALES_SHEET)
            val rows: Iterator<Row> = sheet.iterator()
            val foodSaleList: MutableList<FoodSale> = mutableListOf()

            var rowNumber = 0
            while (rows.hasNext()) {
                val currentRow = rows.next()
                // skip header
                if (rowNumber == 0) {
                    rowNumber++
                    continue
                }
                var orderDate: LocalDate? = null
                var region: String? = null
                var city: String? = null
                var category: String? = null
                var product: String? = null
                var quantity: Int? = null
                var unitPrice: BigDecimal? = null
                var totalPrice: BigDecimal? = null

                currentRow.forEach {
                    when (it.columnIndex) {
                        0 -> orderDate = it.dateCellValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        1 -> region = it.stringCellValue
                        2 -> city = it.stringCellValue
                        3 -> category = it.stringCellValue
                        4 -> product = it.stringCellValue
                        5 -> quantity = it.numericCellValue.toInt()
                        6 -> unitPrice = BigDecimal(it.numericCellValue).setScale(2, RoundingMode.CEILING)
                        7 -> totalPrice = BigDecimal(it.numericCellValue).setScale(2, RoundingMode.CEILING)
                    }
                }

                val foodSale = FoodSale(
                        orderDate = orderDate,
                        region = region,
                        city = city,
                        category = category,
                        product = product,
                        quantity = quantity,
                        unitPrice = unitPrice,
                        totalPrice = totalPrice
                )
                foodSaleList.add(foodSale)
            }
            workbook.close()
            return foodSaleList
        } catch (e: IOException) {
            logger.info(e.stackTraceToString())
            throw RuntimeException("Fail to parse Excel file")
        } catch (e: Exception) {
            logger.info(e.stackTraceToString())
            throw RuntimeException("Internal server error")
        }
    }

}