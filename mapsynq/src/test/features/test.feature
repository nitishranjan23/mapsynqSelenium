Feature: To Test and Dry run Scenario before adding to main file

Background: 
    #Given open the mapsync page with given URL
#		And verify mapsync page is loaded properly
		
		
  #Scenario: Home Page
  #	When Galactio popup is displayed on the page
  #	Then close the Galactio popup 
    #And verify page defaults to "Live" tab

    
    Scenario: Top right corner links
    Then verify following links are present on the top right corner of the page
      | Sign in           |
      | Register          |
      | Mobile App        |
      | Galactio          |
      | SG GPS Navigation |
      | Legend            |
      | Calendar          |