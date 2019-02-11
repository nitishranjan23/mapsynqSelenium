Feature: To Test and Dry run Scenario before adding to main file

  Background: Page Launch
    #Given open the mapsync page with given URL
    #And verify mapsync page is loaded properly


  Scenario: Incidents Sub-tab
    Verifications of Incident sub-tab of Live tab

    When user clicks on "Live" tab
    And click on "Incidents" sub-tab
		Then verify incident list is displayed
		And verify "Search incident location" textbox is present
		Then verify search functionality on Incidents tab 
		And verify "Date" dropdown is present
		Then click on "Date" dropdown
		And verify past 2 dates are present in the "Date" dropdown
		Then verify the search functionality in Incident sub-tab
		
		Scenario: Cameras Sub-tab
    Verifications of Cameras sub-tab of Live tab

    When user clicks on "Live" tab
    And click on "Cameras" sub-tab
    Then verify camera list is displayed
    And verify "Search camera location" textbox is present
		Then verify search functionality on Cameras tab
		
		Scenario: Tolls Sub-tab
    Verifications of Tolls sub-tab of Live tab

    When user clicks on "Live" tab
    And click on "Tolls" sub-tab
    Then verify Tolls list is displayed
    And verify "Search gantry location" textbox is present
		Then verify search functionality on Tolls tab
		
    
		