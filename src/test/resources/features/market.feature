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

  @market07
  Scenario: Multi-select
    Given I go to "quote" page
    And I fill multi-select using Select class
    Then I fill multi-select using Action class

  @market07
  Scenario: Quote alert tests
    Given I go to "quote" page
    When I validate the alert window have message "Do you accept third party agreement? Hit OK if accept."
    And I "accept" third-party agreement
    Then I "dismiss" third-party agreement

  @market08
  Scenario: Additional info - test for iFrame
    Given I go to "quote" page
    And fill out additional info with name "John Doe" and phone "1234567890"
    And I fill out required fields
    Then I submit the form
    And I verify that iFrame fields values recorded correctly with name "John Doe" and phone "1234567890"

  @market09
  Scenario: Related docs
    Given I go to "quote" page
    And I verify "Document 2" present on related docs page
    And I fill out required fields
    Then I submit the form

  @market10
  Scenario: JavaScript click
    Given I go to "ecosia" page
    And I fill out search engine field with "BDD" and search