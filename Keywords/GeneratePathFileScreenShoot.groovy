import com.kms.katalon.core.configuration.RunConfiguration

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class GeneratePathFileScreenShoot{

	@Keyword
	public String generateStorageDir(String noTCM, String modulName, String componentName, Integer orderPath){

		String pathGenerate = ""
		String path = ""
		String pathComponentName = ""
		String screenShootModulName = ""
		String screenShootComponentName = ""

		if(orderPath == 1) {
			path = GlobalVariable.screenShotPublicPath
		} else if(orderPath == 0) {
			path = RunConfiguration.getProjectDir() + '\\Screenshots\\'
		} else {
			KeywordUtil.markFailed("\'Order Directory Screenhoot\' Not Found, Please Check Manually !")
		}

		pathComponentName = modulName + "/" + noTCM
		screenShootModulName = pathComponentName + "/"+ noTCM + GlobalVariable.screenshootName + modulName
		screenShootComponentName = (screenShootModulName + componentName)
		pathGenerate = path + screenShootComponentName

		return pathGenerate
	}
}
