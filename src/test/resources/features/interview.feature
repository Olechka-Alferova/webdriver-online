@interview
Feature: Interview questions

  @challenge01
  Scenario: Code challenges - swap two elements
    Given I swap two variables 2 and 3 values
    And I swap two array elements
    Then I swap two map values

  @challenge02
    Scenario Outline: Code challenges - test divisible numbers
    Given I test that <number> divisible number
    Examples:
    |number|
    | 8 |
    | 9 |
    | 12|
    | 17 |

  @challenge02
  Scenario: Perform coding challenges
  Given I perform coding challenges