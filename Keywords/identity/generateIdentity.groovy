package identity

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.Random
import internal.GlobalVariable

//package option
		@Keyword
		def phoneNumber() {
		   def prefixA = "0851"
		   def prefixB = "081"
		   def prefixC = "021"
		   def val = (0..9).toSet()
		   def r = new Random(System.currentTimeMillis())
		   def phone = new StringBuilder()
		   phone.append(prefixA)
		   for(i in 0..6) {
			   phone.append(val.toArray()[r.nextInt(val.size())])
		   }
		   return phone
		}
		@Keyword
		def getName() {
			def names = new File('./Data/nameCollection.txt').readLines()
			def rand = new Random().nextInt(names.size())
			def fName = names[rand+5]
			def lName = names[rand-5]
			def fullName = "${fName} ${lName}"
			return fullName
			}
		@Keyword
		def getEmail() {
			def names = new File('./Data/nameCollection.txt').readLines()
			def email = new File('./Data/emailDomainList.txt').readLines()
			def eRand = new Random().nextInt(email.size())
			def nRand = new Random().nextInt(names.size())
			def name = names[nRand]
			def domain = email[eRand]
			return "${name}@${domain}"
			}
		@Keyword
		def getKTP() {
			def val = (1..11).toSet()
			def r = new Random(System.currentTimeMillis())
			def ktp = new StringBuilder()
			ktp.append("3275")
			for(i in 1..11) {
		  ktp.append(val.toArray()[r.nextInt(val.size())])
	   }
	   return ktp
	}
	
