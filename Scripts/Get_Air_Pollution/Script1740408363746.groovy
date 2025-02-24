import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil

// Send API Request using South Jakarta latitude and longitude
def response = WS.sendRequest(findTestObject('Get_Air_Pollution'))

// Verify Status Code
WS.verifyResponseStatusCode(response, 200)

// Parse Response Body
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())

// Verify Coordinates for South Jakarta
assert jsonResponse.coord.lat == -6.2615
assert jsonResponse.coord.lon == 106.8106

// Verify JSON Schema
assert jsonResponse.list.size() > 0

// Verify Air Quality Index (AQI) exists
assert jsonResponse.list[0].main.aqi >= 0 && jsonResponse.list[0].main.aqi <= 500

// Verify Key Pollutants Exist
assert jsonResponse.list[0].components.pm2_5 >= 0
assert jsonResponse.list[0].components.pm10 >= 0
assert jsonResponse.list[0].components.o3 >= 0
assert jsonResponse.list[0].components.nh3 >= 0
assert jsonResponse.list[0].components.so2 >= 0
assert jsonResponse.list[0].components.no2 >= 0
assert jsonResponse.list[0].components.no >= 0
assert jsonResponse.list[0].components.co >= 0
