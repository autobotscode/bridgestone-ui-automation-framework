# bridgestone-ui-automation-framework


**Pre-requisites:**

1. JDK 1.8
2. Chrome 94
3. Windows OS
4. IntelliJ


**Technical Stack:**
1. Selenium WebDriver
2. Page Object Model
3. Cucumber
4. JUnit
5. TestNG assertions
6. Maven
7. Log4j
8. CSV 



**Steps to run:**

1. Either right click on TireSize.feature file
        or
2. Mention tags in CukesRunner.java and run the file

   or

3. using maven  goal( change tag name for required test to run)

 clean verify "-Dcucumber.options=--tags @CaptureTireDetails"
 
 

Note: Refer "Results_XXXXX.csv" file post execution for Tire Size, Count & URL details generated at project root directoty. Observed more than 200 links during execution for capturing Tire details.


