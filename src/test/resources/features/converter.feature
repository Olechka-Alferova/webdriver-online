@converter
  Feature: Converter and Calculator cases

    @converter01
    Scenario: Validate Fahrenheit to Celsius
      Given I go to "converter" page
      When I click on "Temperature"
      And I set "Fahrenheit" to "Celsius"
      Then I enter into From field "54 " and verify "12.2" result

    @converter02
    Scenario Outline: Validate different converted Types and Values
      Given I go to "converter" page
      When I click on "<convertedType>"
      And I set "<calFrom>" to "<calTo>"
      Then I enter into From field "<valueFrom>" and verify "<valueTo>" result
      Examples:
        |convertedType | calFrom   | calTo     | valueFrom| valueTo|
        |Temperature   | Fahrenheit| Celsius   |54        |  12.2  |
        |Weight        | Pound     | Kilogram  |170       |    77  |
        |Length        | Mile      | Kilometer |50        |  80.4  |

    @calculator01
    Scenario: Verify calculator result
      Given I go to "calculator" page
      When I navigate to "Auto Loan Calculator"
      And I clear all calculator fields
      And I calculate
      Then I verify "Please provide a positive auto price." calculator error
      Then I verify "Please provide a positive interest value." calculator error
      And I enter "25000" price, "60" months, "4.5" interest, "5000" downpayment, "0" trade-in, "California" state, "7" percent tax, "300" fees
      And I calculate
      Then I verify monthly pay is "$372.86"
