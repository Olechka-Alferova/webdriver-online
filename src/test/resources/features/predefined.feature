@predefined
Feature: Smoke steps

  @predefined1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "(//input[@name='btnK'])[1]"
    Then I wait for element with xpath "//*[@id='res']" to be present
    Then element with xpath "//*[@id='res']" should contain text "Cucumber"

  @predifined2
  Scenario: Predifined steps for Bing
    Given I open url "https://www.bing.com"
    Then I should see page title as "Bing"
    Then element with xpath "//*[@id='sb_form_q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@id='sb_form_q']"
    Then I click on element using JavaScript with xpath "//label[@for='sb_form_go']"
    Then I wait for element with xpath "//ol[@id='b_results']" to be present
    Then element with xpath "//*[@id='b_results']" should contain text "Cucumber"

  @predifined3
  Scenario: Predifined steps for DuckDuckGo
    Given I open url "https://duckduckgo.com"
    Then I should see page title contains "DuckDuckGo"
    Then element with xpath "//*[@id='search_form_homepage']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@id='search_form_input_homepage']"
    Then I click on element with xpath "//*[@id='search_button_homepage']"
    Then I wait for element with xpath "//*[@id='r1-0']" to be present
    Then element with xpath "//*[@id='links']" should contain text "Cucumber"

  @predifined4
  Scenario: Predifined steps for Yahoo
    Given I open url "https://www.yahoo.com"
    Then I should see page title as "Yahoo"
    Then I wait for element with xpath "//input[@id='header-search-input']" to be present
    When I type "Behavior Driven Development" into element with xpath "//*[@id='header-search-input']"
    Then I click on element with xpath "//*[@id='header-desktop-search-button']"
    Then I wait for element with xpath "//*[@id='web']" to be present
    Then element with xpath "//*[@id='web']" should contain text "Cucumber"

  @predifined5
  Scenario: Predifined steps for Gibiru
    Given I open url "http://gibiru.com"
    Then I should see page title contains "Gibiru"
    Then element with xpath "//*[@id='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//*[@id='q']"
    Then I click on element with xpath "//*[@id='button-addon2']"
    Then I wait for element with xpath "//*[@class='gsc-resultsbox-visible']" to be present
    Then element with xpath "//*[@id='___gcse_0']" should contain text "Cucumber"

  @predifined6
  Scenario: Predifined steps for Swisscows
    Given I open url "https://swisscows.com"
    Then I should see page title contains "Swisscows"
    Then element with xpath "//input[@name='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='query']"
    Then I click on element with xpath "//button[@class='search-submit']"
    Then I wait for element with xpath "//article" to be present
    Then element with xpath "//*[@class='page-results']" should contain text "Cucumber"

  @predifined7
  Scenario: Predifined steps for Search Encrypt
    Given I open url "https://www.searchencrypt.com"
    Then I should see page title contains "Search Encrypt"
    Then element with xpath "(//input[@name='q'])[1]" should be present
    When I type "Behavior Driven Development" into element with xpath "(//input[@name='q'])[1]"
    Then I click on element with xpath "(//button[@id='btn-secure-search'])[1]"
    Then I wait for element with xpath "//*[@class='search-result sr cf']" to be present
    Then element with xpath "//*[@id='results-list']" should contain text "Cucumber"

  @predifined8
  Scenario: Predifined steps for Startpage
    Given I open url "https://www.startpage.com"
    Then I should see page title contains "Startpage"
    Then element with xpath "//input[@id='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='query']"
    Then I click on element with xpath "//button[@class='search-form__button']"
    Then I wait for element with xpath "//*[@class='pagination__num']" to be clickable
#    Then I wait for 3 sec
    Then element with xpath "//*[@class='w-gl w-gl--default']" should contain text "Cucumber"

  @predifined9
  Scenario: Predifined steps for Yandex
    Given I open url "https://www.yandex.com"
    Then I should see page title as "Yandex"
    Then element with xpath "//input[@id='text']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='text']"
    Then I click on element with xpath "//button[@type='submit']"
    Then I wait for element with xpath "//li[@class='serp-item']" to be present
    Then element with xpath "//div[@class='content__left']" should contain text "Cucumber"

  @predifined10
  Scenario: Predifined steps for Boardreader
    Given I open url "http://boardreader.com"
    Then I should see page title contains "Boardreader"
    Then element with xpath "//input[@id='title-query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='title-query']"
    Then I click on element using JavaScript with xpath "//button[@id='title-submit']"
    Then I wait for element with xpath "//ul[@class='mdl-list']/li" to be present
    Then element with xpath "//ul[@class='mdl-list']" should contain text "Cucumber"

  @predifined11
  Scenario: Predifined steps for Ecosia
    Given I open url "https://www.ecosia.org"
    Then I should see page title contains "Ecosia"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//button[@type='submit']"
    Then I wait for element with xpath "//div[@class='mainline-results']" to be present
    Then element with xpath "//div[@class='mainline-results']" should contain text "Cucumber"

  @predifined12
  Scenario: Predifined steps for Amazon
    Given I open url "https://www.amazon.com/"
    Then I should see page title contains "Amazon"
    Then element with xpath "//*[@id='nav-search']" should be present
    And element with xpath "//option[text()='All Departments']" should be selected
    When I type "Behavior Driven Development" into element with xpath "//input[@id='twotabsearchtextbox']"
    Then I click on element with xpath "//span[@id='nav-search-submit-text']/..//input"
    Then I wait for element with xpath "//div[@class='sg-col-inner']" to be present
    Then element with xpath "//div[@id='search']" should contain text "Cucumber"

  @predifined13
  Scenario: Predifined steps for Youtube
    Given I open url "https://www.youtube.com/"
    Then I should see page title contains "YouTube"
    Then element with xpath "//input[@id='search']" should be present
    When I type "Portnov computer school" into element with xpath "//input[@id='search']"
    Then I click on element with xpath "//button[@id='search-icon-legacy']"
    Then I wait for element with xpath "//div[@id='content-section']" to be present
    Then element with xpath "//div[@id='container']//div[@id='primary']" should contain text "Software testing"

