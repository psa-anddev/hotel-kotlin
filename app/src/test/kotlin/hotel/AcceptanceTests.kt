package hotel

import org.junit.platform.suite.api.*
import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME

@Suite 
@IncludeEngines("cucumber")
@SelectClasspathResource("features/hotel")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "hotel.steps")
class AcceptanceTests
