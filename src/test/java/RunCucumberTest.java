import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages; // Changed
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("features") // Use this if your .feature files are in src/test/resources/features
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "steps")
public class RunCucumberTest {
}
