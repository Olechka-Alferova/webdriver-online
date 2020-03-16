@quoteTests
Feature: Tests for quote page

  @test01
  Scenario: Validate responsive UI behavior
    Given I open url "https://skryabin.com/market/quote.html"
    And I resize window to 1280 and 1024
    Then element with xpath "//b[@id='location']" should be displayed
    When I resize window to 800 and 1024
    Then element with xpath "//b[@id='location']" should be displayed
    When I resize window to 400 and 1024
    Then element with xpath "//b[@id='location']" should not be displayed

  @test01a
#      Scenario will be run 3 times for each xpath
  Scenario Outline: Validate responsive UI behavior with different data
    Given I open url "https://skryabin.com/market/quote.html"
    And I resize window to 1280 and 1024
    Then element with xpath "<xpath>" should be displayed
    When I resize window to 800 and 1024
    Then element with xpath "<xpath>" should be displayed
    When I resize window to 400 and 1024
    Then element with xpath "<xpath>" should not be displayed
    Examples:
      | xpath                  |
      | //b[@id='location']    |
      | //b[@id='currentDate'] |
      | //b[@id='currentTime'] |

  @test02
  Scenario: Validate minimal “Username” field length
    Given I open url "https://skryabin.com/market/quote.html"
    Then element with xpath "//input[@name='username']" should be displayed
    And element with xpath "//label[@id='username-error']" should not be present
    When I type "a" into element with xpath "//input[@name='username']"
    Then I click on element with xpath "//*[@id='formSubmit']"
    Then element with xpath "//label[@id='username-error']" should be displayed
    When I type "a" into element with xpath "//input[@name='username']"
    Then element with xpath "//label[@id='username-error']" should not be displayed

  @test03
  Scenario Outline: Validate that email field does not accept invalid email
    Given I open url "https://skryabin.com/market/quote.html"
    Then element with xpath "//input[@name='email']" should be displayed
    When I type "<email>" into element with xpath "//input[@name='email']"
    And I click on element with xpath "//*[@id='formSubmit']"
    Then element with xpath "//label[@id='email-error']" should be displayed
    When I clear element with xpath "//input[@name='email']"
    And I type "Te_st12a.te-ST@test.US" into element with xpath "//input[@name='email']"
    Then element with xpath "//label[@id='email-error']" should not be displayed
    Examples:
      | email           |
      | test.gmail.com  |
      | testgmail.com   |
      | test@@gmail.com |
      | test@gmailcom   |
#      scenario to test email |test@gmailcom| was failed - that's a bug, such email format is invalid
      | test@gmail..com |
      | test@gmail,com  |

  @test04
  Scenario: Validate that Confirm Password is disabled if Password field is empty
    Given I open url "https://skryabin.com/market/quote.html"
    Then element with xpath "//input[@id='password']" should have attribute "value" as ""
    And element with xpath "//input[@id='confirmPassword']" should be disabled
    When I type "passWord123" into element with xpath "//input[@id='password']"
    And I click on element with xpath "//input[@id='confirmPassword']"
    Then element with xpath "//input[@id='confirmPassword']" should be enabled

  @test05
  Scenario: Validate “Name” field behavior
    Given I open url "https://skryabin.com/market/quote.html"
    Then element with xpath "//input[@id='name']" should be present
    When I click on element with xpath "//input[@id='name']"
    Then element with xpath "//*[@role='dialog']" should be displayed
    When I type "Olga" into element with xpath "//input[@id='firstName']"
    And I type "Alferova" into element with xpath "//input[@id='lastName']"
    And I click on element with xpath "//span[text()='Save']"
    Then element with xpath "//input[@id='name']" should have attribute "value" as "Olga Alferova"

  @test06
  Scenario: Validate that Accepting Privacy Policy is required to submit the form
    Given I open url "https://skryabin.com/market/quote.html"
    When I click on element with xpath "//*[@id='formSubmit']"
    Then element with xpath "//label[@id='agreedToPrivacyPolicy-error']" should be displayed
    When I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
    Then element with xpath "//label[@id='agreedToPrivacyPolicy-error']" should not be displayed

  @test07a
  Scenario: Validate not required fields
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "+19165559090" into element with xpath "//input[@name='phone']"
    And I click on element with xpath "//select[@name='countryOfOrigin']/option[@value='Russia']"
    And I click on element with xpath "//input[@value='female']"
    Then I click on element with xpath "//input[@name='allowedToContact']"
    When I type "2201 Test street 34, Test City, CA, 95999 " into element with xpath "//textarea[@id='address']"
    And I click on element with xpath "//select[@name='carMake']/option[@value='BMW']"
    And I click on element with xpath "//button[@id='thirdPartyButton']"
    And I accept alert
    Then I click on element with xpath "//input[@id='dateOfBirth']"
    And I click on element with xpath "(//select[@data-handler='selectMonth']/option)[3]"
    And I click on element with xpath "//*[@data-handler='selectYear']/option[@value='1988']"
    And I click on element with xpath "//a[text()='19']"
    Then element with xpath "//input[@id='dateOfBirth']" should have attribute "value" as "03/19/1988"

  @test08
  Scenario: Submit the form and verify the data
    Given I open url "https://skryabin.com/market/quote.html"
    When I type "test" into element with xpath "//input[@name='username']"
    And I type "test@gmail.com" into element with xpath "//input[@name='email']"
    And I type "PassWord" into element with xpath "//input[@id='password']"
    And I type "PassWord" into element with xpath "//input[@id='confirmPassword']"
    When I click on element with xpath "//input[@id='name']"
    And I type "Olga" into element with xpath "//input[@id='firstName']"
    And I type "Alferova" into element with xpath "//input[@id='lastName']"
    And I click on element with xpath "//span[text()='Save']"
    Then element with xpath "//input[@id='name']" should have attribute "value" as "Olga Alferova"
    When I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
    And I click on element with xpath "//*[@id='formSubmit']"
    Then element with xpath "//b[@name='username']" should have text as "test"
    And element with xpath "//b[@name='email']" should have text as "test@gmail.com"
    And element with xpath "//b[@name='name']" should have text as "Olga Alferova"
    And element with xpath "//b[@name='password']" should not contain text "Password"