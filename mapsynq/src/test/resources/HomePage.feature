#Author: Nitish
#Date: 10th Feb 2019
Feature: To test basic features of mapsynq home page

  Background: Page Launch
    Given open the mapsync page with given URL
    And verify mapsync page is loaded properly

  Scenario: Home Page
    When Galactio popup is displayed on the page
    Then close the Galactio popup
    And verify page defaults to "Live" tab

  Scenario: Top right corner links
    Then verify following links are present on the top right corner of the page
      | Sign in           |
      | Register          |
      | Mobile App        |
      | Galactio          |
      | SG GPS Navigation |
      | Legend            |
      | Calendar          |

  Scenario: Top Left Tabs
    Then verify following tabs are present on the top left corner
      | Directions |
      | Personal   |
      | Live       |

  Scenario: Live Tab
    Verifications of Live Tab and its sub-tabs

    When user clicks on "Live" tab
    Then verify following sub tabs are present
      | Incidents |
      | Cameras   |
      | Tolls     |

  Scenario: Incidents Sub-tab of Live Tab
      Verifications of Incident sub-tab of Live tab

    When user clicks on "Live" tab
    And click on "Incidents" sub-tab
    Then verify incident list is displayed
    And verify "Search incident location" textbox is present
    And verify "Date" dropdown is present
    Then click on "Date" dropdown
    And verify past 2 dates are present in the "Date" dropdown
    Then verify the search functionality in Incident sub-tab

  Scenario: Cameras Sub-tab of Live
      Verifications of Cameras sub-tab of Live tab

    When user clicks on "Live" tab
    And click on "Cameras" sub-tab
    Then verify camera list is displayed
    And verify "Search camera location" textbox is present
    Then verify search functionality on Cameras sub-tab

  Scenario: Tolls Sub-tab of Live Tab
     Verifications of Tolls sub-tab of Live tab

    When user clicks on "Live" tab
    And click on "Tolls" sub-tab
    Then verify Tolls list is displayed
    And verify "Search gantry location" textbox is present
    Then verify search functionality on Tolls tab

  Scenario: Personal Tab
    Before sign in

    When user clicks on "Personal" tab
    Then verify following buttons are present
      | Sign in  |
      | Register |

  Scenario: Directions Tab
    When user clicks on "Directions" tab
    Then verify To/Origin and From/Destination textboxes are present
    And verify following checkboxes are present
      | Traffic aware |
      | Toll Aware    |
      | Fastest       |
      | Shortest      |
    Then verify swap origin destination button is present
    Then Type "US" in Destination textbox
    And verify search auto-complete suggestions are displayed
    Then select one of the suggestions
    And verify Destination textbox is filled
    Then verify clicking on swap origin destination button swaps origin and destination

  Scenario: Get Directions Alert
    When user clicks on "Directions" tab
    Then verify To/Origin and From/Destination textboxes are present
    Then Type "US" in Destination textbox
    And verify search auto-complete suggestions are displayed
    Then select one of the suggestions
    And verify Destination textbox is filled
    Then click on "Get Directions" button
    And verify Alert is displayed with message as "Please grant permission to you use the location service on your browser to use your 'Current location'."
