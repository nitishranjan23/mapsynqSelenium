# mapsynq

A Selenium-Cucumber-Java project to automate mapsynq website.
Application URL: http://www.mapsynq.com 

## Getting Started

This is a maven project so you need not to worry about any project related dependency such as downloading any jars and adding it to your buid path. Maven will take care of that for you.

Just clone or download this project on your local system and you are ready to go!

### Framework Description/Folder Structure
* src/test/resources contains the feature files.
* src/test/java/pageObject contains the page objects or locators.
* src/test/java/reusableMethods contains the re-usable methods like Action Methods and Reading Properties file.
* src/test/java/stepDefinitions contains the step defination methods.
* src/test/java/utlis contains Setup file where driver initialization and browser launching code for different browser is written.
* exe contains executables for IEDriverServer and Chromedriver in case WebDriverManager is not working properly.
* logs contains application log of our execution.
* properties contains globalConfig.properties file where we are providing application URL and browser of our choice.
* target contains different types of reports. 
* target/cucumber-html-reports/feature-overview.html is the Cucumber report which is generated when we run the maven project.
* log4j.xml used for logging purpose.
* pom.xml contains project dependencies.
* different bat files to run the project/module with a single click. (Right now I am facing some issue in this and I am working on this)

### Prerequisites

* Java JDK should be installed and its PATH should be added in Environment variable
* Maven should be present in the system and is PATH should be added in Environment variable

### Setting Path in Environment Variable

Please refer to [this link](https://www.java.com/en/download/help/path.xml) for adding PATH

## Running the tests

Application URL and Browser choice of execution is provided in properties\globalConfig.properties file
For headless browser execution use headless= true in the properties\globalConfig.properties file

### Test Environment Details:
This code is being developed and tested on below environment (but it is not Environment dependent).  
* Browser: Chrome (Version: 71)
* Chromedriver Version: 2.46.628402
* Platform: Windows
* OS: Windows 8

Driver is managed by WebDriverManager which will automatically download the latest driver according to the browser choice.
Currently it is configured to use for three browsers namely Chrome, IE and Safari.

### Run via executables/bat file:
At present I am facing some techinical issue in this part and currently working on this.

* For complete execution, run the run.bat file
* For feature-wise execution run the particularFeature.bat file

### Run with Eclipse:

1. Launch Eclipse
2. click on File -> Import
3. Select Maven -> Existing Maven Projects and click on Next button
4. Click on Browse and navigate to git directory and select folder
5. Select pom.xml and click on finish button.
6. For feature wise execution Right click on any feature file and select Run As -> Cucumber Feature
7. For Complete execution Right click on the project folder and select Run As -> Maven install (Right now I am facing some issue in this and I am currently working on this)

## Reporting
Here default Cucumber report is used for reporting purpose.
When the project is run with bat file or by using option "Run as > Maven install" from eclipse, cucumber report will be generated.
Cucumber Report can be found in target/cucumber-html-reports/feature-overview.html file. The report also contains screenshot of failed scenarios.

## Built With

* [Selenium](https://www.seleniumhq.org/) - The web automation tool
* [Cucumber](https://cucumber.io/) - BDD framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

I have used [GitHub](https://github.com/) for versioning. For the versions available, see the [tags on this repository](https://github.com/nitishranjan23/mapsynqSelenium/tags). 

## Author

* **Nitish Ranjan** - [Nitish](https://github.com/nitishranjan23)
