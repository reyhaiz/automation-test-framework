package com.automation.web.runners;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/web")
@ConfigurationParameter(key = "cucumber.glue", value = "com.automation.web.steps")
@ConfigurationParameter(key = "cucumber.plugin", value =
        "pretty," +
                "html:build/reports/cucumber/web/report.html," +
                "json:build/reports/cucumber/web/report.json")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@web")
public class WebTestRunner {
}