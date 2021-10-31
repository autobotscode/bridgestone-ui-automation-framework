# bridgestone-ui-automation-framework


**Pre-requisites:**

1. JDK 1.8
2. Chrome 94
3. Windows OS
4. IntelliJ

**Steps to run:**

1. Either right click on TireSize.feature file
        or
2. Mention tags in CukesRunner.java and run the file

   or


3. using maven  goal( change tag name for required test to run)

 clean verify "-Dcucumber.options=--tags @CaptureTireDetails"

