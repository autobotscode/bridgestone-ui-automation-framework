# bridgestone-ui-automation-framework


**Pre-requisites:**

1. JDK 1.8
2. Chrome 94
3. Windows OS
4. IntelliJ


**Technical Stack:**
Selenium WebDriver
Page Object Model
Cucumber
JUnit
TestNG assertions
Maven
Log4j
CSV 


**Steps to run:**

1. Either right click on TireSize.feature file
        or
2. Mention tags in CukesRunner.java and run the file

   or


3. using maven  goal( change tag name for required test to run)

 clean verify "-Dcucumber.options=--tags @CaptureTireDetails"
 
 

Note: Refer "Results_XXXXX.csv" file post execution for Tire Size, Count & URL details generated at project root directoty.
