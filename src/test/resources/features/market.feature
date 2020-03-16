@market
Feature: Market application test suite

  @market01
  Scenario: Market basic test
    Given I go to "quote" page
    And I print page details
    Then I go to "google" page
    And I print page details
    And I go back and forward
    Then I refresh the page

  @market03
  Scenario: Resize the window
    Given I go to "quote" page
    And I change resolution to "phone"
    Then I change resolution to "desktop"

  @market04
  Scenario: Fill out required fields and submit the page
    Given I go to "quote" page
    When I fill out required fields
    And I submit the form
    Then I verify required fields

  @market05
  Scenario: Verify email field behavior
    Given I go to "quote" page
    When I verify email field behavior

  @market06
    Scenario: Fill out optional fields and submit the page
    Given I go to "quote" page
    When I fill out required fields
    And I fill out optional fields
    And I submit the form
    Then I verify that fields values recorded correctly







