@usps

Feature: USPS test suite

  @usps01
  Scenario: Validate ZIP code for Portnov Computer School
    Given I go to "usps" page
    When I go to Lookup ZIP page by address
    And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
    Then I validate "94022" zip code exists in the result

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
