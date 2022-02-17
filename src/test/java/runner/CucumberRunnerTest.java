package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@api"
)

public class CucumberRunnerTest {

}
