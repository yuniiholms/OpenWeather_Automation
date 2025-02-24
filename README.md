# ✨Steps To Run

1. Navigate to "Test Suites" menu
2. Click test suite "Weather_API_TestSuite"
3. Click ▶ button
 
 <br>
   
# ✨How To Get The Report

## For Katalon Enterprise user
1. Navigate to the "Reports" menu in Katalon Studio
2. Click on the related date folder
3. Click on Weather_API_TestSuite folder
4. Open the HTML, PDF, or CSV report to review the results

## For Katalon Free user
1. Navigate to the "Reports" menu in Katalon Studio
2. Click on the related date folder
3. Click on Weather_API_TestSuite folder
4. Right-click on the related file
5. Select "Open Containing Folder" (System will open the folder)
6. Open the HTML file to view the report

<br>

# ✨Project Structure

- In Test Cases Folder, contains test cases for automation testing.<br>
Consist of:
	- Get_Weather_Forecast.tc: Test case for 5-day weather forecast
	- Get_Air_Pollution.tc: Test case for current air pollution data

- In Object Repository Folder, stores API request objects.<br>
Consist of:
	- Get_Weather_Forecast.rs: The request for fetching weather data
	- Get_Air_Pollution.rs: The request for fetching air pollution data

- In Test Suites Folder, contains Test Suites that group multiple test cases.<br>
Consist of:
	- Weather_API_TestSuite.ts: Runs both weather and air pollution test cases

- In Reports Folder, stores execution reports