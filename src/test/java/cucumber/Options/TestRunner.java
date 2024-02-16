package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",//Path to the feature files-features
    glue = {"stepDefinations"},// Package name where step definitions are located-glue
    plugin = {"pretty","json:target/jsonReports/cucumber-report.json"},
    //plugin = {"pretty","html:target/jsonReports/cucumberhtml-reports.html"},
    monochrome=true		
    //,tags = ("@DeletePlace")//to run tests individualy from the feature files
)
public class TestRunner {

}

