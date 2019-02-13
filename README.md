# mapsynq

A Selenium-Cucumber-Java project to automate mapsynq home page.
Application URL: http://www.mapsynq.com 

## Getting Started

This is a maven project so you need not to worry about any project related dependency such as downloading any jars and adding it to your buid path. Maven will take care of that for you.

Just clone or download this project on your local system.


These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

  
### Prerequisites

* Java JDK should be installed and its PATH should be added in Environment variable
* Maven should be present in the system and is PATH should be added in Environment variable

### Setting Path in Environment Variable

Please refer to this(https://www.java.com/en/download/help/path.xml) link for adding PATH

## Running the tests

Application URL and Browser choice of execution is provided in properties\globalConfig.properties file

Test Environment Details:
========================
This code is being tested on below environment (but it is not Environment dependent).  
* Browser: Chrome (Version: 71)
* Chromedriver Version: 2.46.628402
* Platform: Windows
* OS: Windows 8

To execute in any other browser please add compatable driver for that browser.

Run via executables/bat file:
============================
* For complete execution, run the run.bat file
* For feature-wise execution run the particularFeature.bat file

Run with Eclipse:
================================

1. Launch Eclipse
2. click on File -> Import
3. Select Maven -> Existing Maven Projects and click on Next button
4. Click on Browse and navigate to git directory and select folder
5. Select pom.xml and click on finish button.
6. For feature wise execution Right click on any feature file and select Run As -> Cucumber Feature
7. For Complete execution Right click on the project folder and select Run As -> Maven install

## Reporting
Here default Cucumber report is used for reporting purpose.
Cucumber Report can be found in target/cucumber-html-reports

## Built With

* [Selenium](https://www.seleniumhq.org/) - The web automation tool
* [Cucumber](https://cucumber.io/) - BDD framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

I have used [GitHub](https://github.com/) for versioning. For the versions available, see the [tags on this repository](https://github.com/nitishranjan23/mapsynqSelenium/tags). 

## Authors

* **Nitish Ranjan** - [Nitish](https://github.com/nitishranjan23)
