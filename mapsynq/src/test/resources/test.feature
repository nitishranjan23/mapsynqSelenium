Feature: To Test and Dry run Scenario before adding to main file



  Background: Page Launch
    Given open the mapsync page with given URL
		And verify mapsync page is loaded properly
		
		
#	Scenario: Verify navigation of Sign in link
#	When user clicks on "Sign in" link
#	Then verify Sign in page is opened
	
	Scenario: Verify navigation of Register link
	When user clicks on "Register" link
	Then verify "Create your mapSYNQ account" page is opened
	