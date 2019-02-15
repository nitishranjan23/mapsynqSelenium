package utils;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources"}, 
					glue = "src/test/java/stepDefinitions",
					plugin = { "pretty", "json:target/cucumber.json", "html:target/cucumber-html-reports"}
					//,tags = { "~@ignore" }
					)
public class RunnerTest {

	
}
