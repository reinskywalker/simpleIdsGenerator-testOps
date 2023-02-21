import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFClientAnchor
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.aspectj.weaver.patterns.ThrowsPattern

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import net.bytebuddy.implementation.bytecode.Throw

public class WriteToExcell {

	@Keyword
	public void writeToExcelTrxSalesOrder(HashMap<String, String> params, String pathFile, String nameFile, String sheetName) throws IOException{

		String custName = params.get('custName')
		String trxNo = params.get('trxNo')
		String reqPurchase = params.get('reqPurchase')
		String orderSales = params.get('orderSales')

		String path = RunConfiguration.getProjectDir()
		FileInputStream fis = new FileInputStream(path+'\\'+pathFile+'\\'+nameFile)
		XSSFWorkbook workBook = new XSSFWorkbook(fis)
		XSSFSheet sheet = workBook.getSheet(sheetName)
		int rowNumber = sheet.getLastRowNum()

		XSSFRow row = null
		if(row==null){
			row=sheet.createRow(rowNumber+1)
		}

		XSSFCell cellCustName=row.createCell(0)
		cellCustName.setCellValue(custName)

		XSSFCell cellNoTrx=row.createCell(1)
		cellNoTrx.setCellValue(trxNo)

		XSSFCell cellTrxType=row.createCell(2)
		cellTrxType.setCellValue(reqPurchase)

		XSSFCell cellOrderType=row.createCell(3)
		cellOrderType.setCellValue(orderSales)

		FileOutputStream fos = new FileOutputStream(path+'\\'+pathFile+'\\'+nameFile)
		workBook.write(fos)
		fos.close()
	}
}
