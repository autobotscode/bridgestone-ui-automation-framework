@TireSize
Feature: Capture Tire Count details

  Background:
    Given User navigates to "https://www.bridgestonetire.com/size/"



  @CaptureTireDetails
  Scenario: Capture Tire count details in CSV for different diameters
    When User captures available wheel size diameters
    And User captures Tire Size,Tire Count, URL for every diameter
    And User writes captured data into CSV file
