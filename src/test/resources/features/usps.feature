@usps

Feature: USPS test suite

  @usps01
  Scenario: Validate ZIP code for Portnov Computer School
    Given I go to "usps" page
    When I go to Lookup ZIP page by address
    And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
    Then I validate "94022" zip code exists in the result
    And I verify each row has "94022" zip code

  @usps02
  Scenario: Validate ZIP code for California capitol building
    Given I go to "usps" page
    When I go to Lookup ZIP page by address
    And I fill out "1315 10th St room b-27" street, "Sacramento" city, "CA" state
    Then I validate "95814" zip code exists in the result

  @usps03
  Scenario: Calculate price for Postcard
    Given I go to "usps" page
    When I go to Calculate Price Page
    And I select "United Kingdom (United Kingdom of Great Britain and Northern Ireland)" with "Postcard" shape
    And I define "2" quantity
    Then I calculate the price and validate cost is "$2.40"

  @usps04
  Scenario: Calculate price for Box
    Given I go to "usps" page
    When I go to Calculate Price Page
    And I select "Russia" with "Box" shape
    Then I validate the price is "$107.05"
    And I validate the price is "$81.75"
    Then I validate the price is "$38.60"

  @usps05
  Scenario: Phone number of the nearest Accountable Mail Pickup Service Post Office for Portnov Computer School
    Given I go to "usps" page
    When I go to Find a Location Page
    And I filter by "Post Offices™" Location Types, "Pickup Services" Services, "Accountable Mail" Available Services
    And I fill in "4970 El Camino Real 110" street, "Los Altos" city, "CA" state
    Then I print the phone number and validate it is "800-275-8777"

  @usps06
  Scenario: Test to print console log
    Given I go to "yahoo" page
    And I print browser log in the console

  @usps07
  Scenario: Mouseover cases
    Given I go to "usps" page
    When I go to MailAndShip tab
    Then I go to QuickTools tab
    And I validate mouseover for header tabs

  @usps08
  Scenario Outline: Validate ZIP codes for different addresses  // Scenario Template the same
    Given I go to "usps" page
    When I go to Lookup ZIP page by address
    And I fill out "<address>" street, "<city>" city, "<state>" state
    Then I validate "<zipCode>" zip code exists in the result
    Examples:
      | address             | city      | state | zipCode |
      | 4970 El Camino Real | Los Altos | CA    | 94022   |
      | 11 Wall street      | Ney York  | NY    | 10005   |
      | 111 S Michigan Av   | Chicago   | IL    | 60603   |

  @usps09
  Scenario: Quadcopters delivery
    Given I go to "usps" page
    When I go to "Help" tab
    And I perform "Quadcopters delivery" help search
    Then I verify that no results of "Quadcopters delivery" available in help search

  @usps10
  Scenario: Every door direct mail
    Given I go to "usps" page
    When I go to "Every Door Direct Mail" under "Business"
    And I search for "4970 El Camino Real, Los Altos, CA 94022"
    And I click "Show Table" on the map
    When I click "Select All" on the table
    And I close modal window
    Then I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary

@usps11
Scenario: Informed delivery enabled
  Given I go to "usps" page
  When I go to "Informed delivery" section
  And I enter "94022" zip code for informed delivery
  Then I verify that informed delivery is "enabled"

  @usps12
  Scenario: Informed delivery disabled
    Given I go to "usps" page
    When I go to "Informed delivery" section
    And I enter "23424" zip code for informed delivery
    Then I verify that informed delivery is "disabled"

  @usps13
  Scenario: Verify location
    Given I go to "usps" page
    When I perform "Free boxes" search
    And I set "Mail & Ship" in filters
    Then I verify that "7" results found
    When I select "Priority Mail | USPS" in results
    And I click "Ship now" button
    Then I validate that Sign in is required