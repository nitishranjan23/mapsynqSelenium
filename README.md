# mapsynq

A Selenium-Cucumber-Java project to automate testing of mapsynq website.
Application URL: http://www.mapsynq.com 

## Getting Started

This is a maven project so you need not to worry about any project related dependency such as downloading any jars and adding it to your buid path. Maven will take care of that for you.

Just clone or download this project on your local system and you are ready to go!

### Framework Features
* Multiple browser support (Tested on Chrome, Firefox and Opera) and multiple platform support (Tested on Windows and Mac).
* **Headless** Execution support.
* Independent of browser drivers.
* Configurable for application's different URLs.
* Generates **Cucumber report** in both tabular format as well as graphical representation.
* Captures **screenshot** of passed as well as failed scenarios in separate folder.
* Attaches failed scenario screenshot in report file just after that scenario result.
* Generates execution **Log** in both HTML as well as .log format.
* Can be executed through command line, eclipse or .bat files.

### Framework Description/Folder Structure
* **src/test/resources** contains the ***feature files***.
* **src/test/java/pageobjects** contains the page objects or locators.
* **src/test/java/steps** contains the step defination methods and ***Setup file*** where driver initialization and browser launching code for different browsers are written.
* **src/test/java/utils** contains ***Runner*** class and re-usable methods like Action Methods and Reading Properties file.
* **exe** contains executables for IEDriverServer and Chromedriver(in case WebDriverManager is not working properly in your machine).
* **logs** contains application log of our execution both in HTML and .log format.
* **properties** contains ***globalConfig.properties*** file where we are providing application URL and browser of our choice.
* **screenshots/failed** contains screenshots of failed scenario.
* **screenshots/passed** contains last screen screenshot of passed scenario.
* **target** contains different types of reports. 
* **target/cucumber-html-reports/feature-overview.html** is the ***Cucumber report*** which is generated when we run the maven project.
* **log4j.xml** used for logging purpose.
* **pom.xml** contains project/maven dependencies.
* different **bat files** to run the project/module with a single click.

### Prerequisites

* Java JDK should be installed and its PATH should be added in Environment variable
* Maven should be present in the system and is PATH should be added in Environment variable

### Setting Path in Environment Variable

Please refer to [this link](https://www.java.com/en/download/help/path.xml) for adding PATH

## Running the tests

Before execution provide the following paramentes in **properties\globalConfig.properties** file
* **URL:** Application URL 
* **browser:** Browser in which you want to run the execution
* **headless:** For headless browser execution provide ***headless=true*** else keep headless=false

### Test Environment Details:
This code is being developed and/or tested on below environment (but it is not Environment dependent).  
* Browser: Chrome (Version: 71), Firefox (Version: 65.0.1), Opera (Version: 58.0.3135.65_0)
* Chromedriver Version: 2.46.628402
* Platform: Windows, Mac
* OS: Windows 8

Driver is managed by WebDriverManager which will automatically download the latest driver according to the browser choice.
Currently it is configured to use for three browsers namely Chrome, Firefox and Opera.

### Run via executables/bat file:

* For complete execution, run the **run.bat** file
* For feature-wise execution run the particularFeature.bat file

### Run with Eclipse:

1. Launch Eclipse
2. click on File -> Import
3. Select Maven -> Existing Maven Projects and click on Next button
4. Click on Browse and navigate to git directory and select folder
5. Select pom.xml and click on finish button.
6. For feature wise execution Right click on any feature file and select **Run As -> Cucumber Feature** *(This process will not generate the Cucumber report)*
7. For Complete execution Right click on the project folder and select **Run As -> Maven install** *(This process will generate the Cucumber report)*

## Reporting

Here default Cucumber report is used for reporting purpose.
When the project is run with **bat file** or by using option **"Run as > Maven install"** from eclipse, cucumber report will be generated.
Cucumber Report can be found in **target/cucumber-html-reports/feature-overview.html** file. The report also contains screenshot of failed scenarios.

## Techinical Tradeoff/Issues/Challenges and Solutions
* Execution with **Opera** browser: Need to provide Opera executables file (Opera.exe) path of system in which it is executing. Please provide it in ***src/test/java/steps/SetUp.java*** file Line Number: 125 (options.setBinary)
* Firefox: Window switching and some of the map related functionality validataion is not working properly.
* During batch execution if execution gets stucks (due to environment issue or else), Open Task Manager and close the particular driver.exe instaance. **e.g.** *If your execution is going on in Chrome browser and execution stucks at some point of time, kill the chromedriver.exe process from Task Manager. or run **taskkill /f /im chromedriver.exe** from command line*

## Built With

* [Selenium](https://www.seleniumhq.org/) - The web automation tool
* [Cucumber](https://cucumber.io/) - BDD framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

I have used [GitHub](https://github.com/) for versioning.

## Author

* **Nitish Ranjan** - [Nitish](https://github.com/nitishranjan23)
