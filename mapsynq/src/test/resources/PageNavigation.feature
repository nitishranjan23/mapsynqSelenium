#Author: Nitish
#Date: 12th Feb 2019

Feature: To test navigation of different links on mapsynq home page
I am only checking the navigation here. I am not checking the form fields of Sign in and Register page

Background: Page Launch
    Given open the mapsync page with given URL
		And verify mapsync page is loaded properly
		
	Scenario: Verify navigation of Sign in link
	When user clicks on "Sign in" link
	Then verify Sign in page is opened
	
	Scenario: Verify navigation of Register link
	When user clicks on "Register" link
	Then verify "Create your mapSYNQ account" page is opened
	
	Scenario: Verify navigation of Mobile App link
	When user clicks on "Mobile App" link
	Then verify mapsynq mobile information page is opened in new browser tab

	Scenario: Verify navigation of Galactio link
	When user clicks on "Galactio" link
	Then verify Galactio page is opened in new browser tab
	
	Scenario: Verify navigation of SG GPS Navigation link
	When user clicks on "SG GPS Navigation" link
	Then verify Google Play page is opened in new browser tab
	
	Scenario: Verify Legend link functionality
	When user clicks on "Legend" link
	Then verify "Legend" popup is displayed
	
	Scenario: Verify Calendar link functionality
	When user clicks on "Calendar" link
	Then verify "My Events" popup is displayed
	
	