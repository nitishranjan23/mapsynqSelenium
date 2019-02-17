###Author: Nitish
###Date: 17th Feb 2019
Feature: To test search related functionality on mapsynq home page

  Background: Page Launch
    Given open the mapsync page with given URL
    And verify mapsync page is loaded properly
    When Galactio popup is displayed on the page
    Then close the Galactio popup

  Scenario: Search
    When search text box is displayed on the page
    Then type "US" in the search text box
    And verify global search auto-complete suggestions are displayed
    Then select one of the global search suggestions
    And verify global search result is displayed

  Scenario: Search result context menu
    When search text box is displayed on the page
    Then type "US" in the search text box
    And verify global search auto-complete suggestions are displayed
    Then select one of the global search suggestions
    And verify global search result is displayed
    Then click on triangle icon beside any search result
    And verify context menu is opened
    And verify following options are present in the context menu
      | To Here      |
      | From Here    |
      | Zoom In      |
      | Whats Nearby |
      | Add To       |
      | My Places    |
      | Calendar     |
      | Share using  |
      | Email        |
    Then verify fbShare icon is present

  Scenario: Informations in Search Information popup on map
    When search text box is displayed on the page
    Then type "US" in the search text box
    And verify global search auto-complete suggestions are displayed
    Then select one of the global search suggestions
    And verify global search result is displayed
    Then verify clicking on any global search result displays that on map
    Then verify following category-wise links are present in the information popup
      | Category  | Links                      |
      | Route     | From Here,To Here          |
      | See       | Zoom in,Nearby,Street View |
      | Add to my | Calendar,Places            |
