package dev.tpcoder.springbootpoi.service.impl

import dev.tpcoder.springbootpoi.model.enum.CustomCellStyle
import dev.tpcoder.springbootpoi.service.ReportService
import dev.tpcoder.springbootpoi.util.StylesGenerator
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.time.LocalDate

@Service
class ReportServiceImpl(private val stylesGenerator: StylesGenerator) : ReportService {

    // For extension .xlsx
    override fun generateXlsxReport(): ByteArray {
        val wb = XSSFWorkbook()
        return generateReport(wb)
    }

    fun isFileEmpty(file: MultipartFile): Boolean = file.isEmpty

    private fun generateReport(wb: Workbook): ByteArray {
        val styles = stylesGenerator.prepareStyles(wb)
        val sheet: Sheet = wb.createSheet("Example sheet name")

        setColumnsWidth(sheet)

        createHeaderRow(sheet, styles)
        createStringsRow(sheet, styles)
        createDoublesRow(sheet, styles)
        createDatesRow(sheet, styles)

        val out = ByteArrayOutputStream()
        wb.write(out)

        out.close()
        wb.close()

        return out.toByteArray()
    }

    private fun setColumnsWidth(sheet: Sheet) {
        sheet.setColumnWidth(0, 256 * 20)

        for (columnIndex in 1 until 5) {
            sheet.setColumnWidth(columnIndex, 256 * 15)
        }
    }

    private fun createHeaderRow(sheet: Sheet, styles: Map<CustomCellStyle, CellStyle>) {
        val row = sheet.createRow(0)

        for (columnNumber in 1 until 5) {
            val cell = row.createCell(columnNumber)

            cell.setCellValue("Column $columnNumber")
            cell.cellStyle = styles[CustomCellStyle.GREY_CENTERED_BOLD_ARIAL_WITH_BORDER]
        }
    }

    private fun createRowLabelCell(row: Row, styles: Map<CustomCellStyle, CellStyle>, label: String) {
        val rowLabel = row.createCell(0)
        rowLabel.setCellValue(label)
        rowLabel.cellStyle = styles[CustomCellStyle.RED_BOLD_ARIAL_WITH_BORDER]
    }

    private fun createStringsRow(sheet: Sheet, styles: Map<CustomCellStyle, CellStyle>) {
        val row = sheet.createRow(1)
        createRowLabelCell(row, styles, "Strings row")

        for (columnNumber in 1 until 5) {
            val cell = row.createCell(columnNumber)

            cell.setCellValue("String $columnNumber")
            cell.cellStyle = styles[CustomCellStyle.RIGHT_ALIGNED]
        }
    }

    private fun createDoublesRow(sheet: Sheet, styles: Map<CustomCellStyle, CellStyle>) {
        val row = sheet.createRow(2)
        createRowLabelCell(row, styles, "Doubles row")

        for (columnNumber in 1 until 5) {
            val cell = row.createCell(columnNumber)

            cell.setCellValue(BigDecimal("${columnNumber}.99").toDouble())
            cell.cellStyle = styles[CustomCellStyle.RIGHT_ALIGNED]
        }
    }

    private fun createDatesRow(sheet: Sheet, styles: Map<CustomCellStyle, CellStyle>) {
        val row = sheet.createRow(3)
        createRowLabelCell(row, styles, "Dates row")

        for (columnNumber in 1 until 5) {
            val cell = row.createCell(columnNumber)

            cell.setCellValue((LocalDate.now()))
            cell.cellStyle = styles[CustomCellStyle.RIGHT_ALIGNED_DATE_FORMAT]
        }
    }
}