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
