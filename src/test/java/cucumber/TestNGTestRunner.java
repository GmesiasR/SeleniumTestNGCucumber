package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber",
    glue = "stepdefinitions",
    monochrome = true,
    tags = "@Solotodo",
    plugin = {"html:target/cucumber.html",
    }
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
