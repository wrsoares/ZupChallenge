package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features/challenge.feature"},
        glue = {"gherkin"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/output/report.html", "rerun:target/rerun.txt"},
        dryRun = false
)
public class Runner {
}