#Author: Nitish
#Date: 11th Feb 2019
Feature: To test Google Map related features of mapsynq home page
  Here I am going to validate different map related feature like display of icons and popup informations on the map

  Background: Page Launch
    Given open the mapsync page with given URL
    And verify mapsync page is loaded properly
    When Galactio popup is displayed on the page
    Then close the Galactio popup

  Scenario: Top pannel buttons on the map
    When Google map is loaded
    Then verify following pannel buttons are present on top of the map
      | Traffic   |
      | Incidents |
      | Parking   |
      | Cameras   |
      | Tolls     |
    Then click on "Incidents" button to make it active
    And verify incidents are marked on the map
    Then click on "Traffic" button to make it active
    And verify Live Traffic information is displayed on the map

  Scenario: Zoom bar and direction slider buttons
    Here I am checking the presence of zoom bar and four buttons to move the map in four directions

    When Google map is loaded
    Then verify following direction slider buttons are present on the map
      | up    |
      | down  |
      | left  |
      | right |
    Then verify "zoomin" and "zoomout" buttons are present on the map
    And verify zoombar is present on the map

  Scenario: Functioning of Direction buttons
    When Google map is loaded
    Then click on "up" direction button
    And verify google map is slided to show upper area into view
    Then click on "down" direction button
    And verify google map is slided to show lower area into view
    Then click on "left" direction button
    And verify google map is slided to show left area into view
    Then click on "right" direction button
    And verify google map is slided to show right area into view

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

  Scenario: Display of Cameras on Google map
    When user clicks on "Live" tab
    And click on "Cameras" sub-tab
    Then verify camera list is displayed
    Then verify clicking on any camera displays that camera on the map
    And verify "Cameras" pannel is active on the top of the map
    Then click on the close button in the popup
    And verify information popup is closed
    Then click on any camera icon on map
    And verify information popup is displayed
    
    Scenario: Display of Tolls on Google map
    When user clicks on "Live" tab
    And click on "Tolls" sub-tab
    Then verify Tolls list is displayed
    Then verify clicking on any Tolls displays that Tolls on the map
    And verify "Tolls" pannel is active on the top of the map
    Then click on the close button in the popup
    And verify information popup is closed
    Then click on any Tolls icon on map
    And verify information popup is displayed
