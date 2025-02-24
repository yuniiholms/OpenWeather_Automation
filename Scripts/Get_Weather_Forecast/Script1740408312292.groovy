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
import java.text.SimpleDateFormat


// Send API Request using South Jakarta latitude and longitude
def response = WS.sendRequest(findTestObject('Get_Weather_Forecast'))

// Print response details for debugging
println("Response Status: " + response.getStatusCode())
println("Response Body: " + response.getResponseBodyContent())

// Verify Status Code is 200
WS.verifyResponseStatusCode(response, 200)

// Parse JSON response
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())

// Verify Coordinates for South Jakarta
assert jsonResponse.city.coord.lat == -6.2615
assert jsonResponse.city.coord.lon == 106.8106

// Verify JSON Schema
assert jsonResponse.list.size() > 0

// Verify that forecast data contains temperature, humidity, and weather description
assert jsonResponse.list[0].main.temp != null
assert jsonResponse.list[0].main.humidity != null
assert jsonResponse.list[0].weather[0].main != null
assert jsonResponse.list[0].weather[0].description != null

// Extract the 'dt_txt' values from 'list'
List<String> dtTxtList = jsonResponse.list*.dt_txt

// Generate expected dates in the required format
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
Date currentDate = new Date()
List<String> expectedDates = (0..4).collect {
	dateFormat.format(currentDate.plus(it)) // Generate current date + next 4 days
}

// Assert that each expected date exists in at least one of the 'dt_txt' values
expectedDates.each { expectedDate ->
	boolean dateExists = dtTxtList.any { dtTxt -> dtTxt.startsWith(expectedDate) }
	assert dateExists : "Date $expectedDate not found in API response!"
}


