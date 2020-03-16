@java
Feature: Java day 04 and 05

  @java01
  Scenario: Hello world
    Given I say hello world 3 times
    And I say "Hello again"
    When I say "Hey guys!"
    Then I say "How are you?"
    Then I print a name

  @java02
  Scenario: Variables
    Given I perform actions with "`myvar something" and "myvar Something"
    And I compared 5 number
    And I print URL for "google" page
    And I print if number 50 is positive
    And I print if number "-5" is positive
    And I print if number "0" is positive
    And I print "7" th day of week

  @java03
  Scenario: Site URL validation
    Given I print URL for "google" page
    And I print URL for "yahoo" page
    And I print URL for "quote" page
    And I print URL for "Yahoo" page
    And I print URL for "Gogle" page

  @java04
  Scenario: Site URL validation using switch
    Given I print URL for "google" page using switch
    And I print URL for "Yahoo" page using switch
    And I print URL for "yahoo" page using switch
    Then I print URL for "quote" page using switch

  @java05
  Scenario: Tests for Array
    Given I work with arrays
    Then I work with array List

  @java06
  Scenario: Maps
    Given I work with maps

