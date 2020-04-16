@upsTests
Feature: Tests for UPS page

  @ups01
  Scenario: Validate Create Shipment functionality
  Given I go to "ups" page
  When I go to Create Shipment page
  And I fill out shipping page with country "United States" name "Administrator" address "4970 El Camino Real" zip "94022" email "mail@example.com" phone "1234567890" city "LOS ALTOS"
  Then I submit the shipping form
  And I verify submitted data: country "US" name "Administrator" address "4970 El Camino Real" zip "94022" email "mail@example.com" phone "1234567890"
  And I cancel the form
  Then I verify form reset successfully
