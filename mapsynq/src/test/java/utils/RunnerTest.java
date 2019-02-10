package utils;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features", 
					glue = "src/test/java/stepDefinitions",
					format = { "pretty", "json:target/cucumber.json", "html:target/site/cucumber-pretty"}
					//,tags = { "~@ignore" }
					)
public class RunnerTest {

	
}
