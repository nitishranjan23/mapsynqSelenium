Feature: To Test and Dry run Scenario before adding to main file

  Background: Page Launch
    Given open the mapsync page with given URL
    And verify mapsync page is loaded properly
    When Galactio popup is displayed on the page
    Then close the Galactio popup

  Scenario: Display of Incidents on Google map
    When user clicks on "Live" tab
    And click on "Incidents" sub-tab
    Then verify incident list is displayed
    Then verify clicking on any incident displays that incident on the map
    And verify "Incidents" pannel is active on the top of the map
    Then click on the close button in the popup
    And verify information popup is closed
    Then click on any icon incident icon on map
    And verify information popup is displayed