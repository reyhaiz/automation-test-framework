package com.automation.api.runners;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/api")
@ConfigurationParameter(key = "cucumber.glue", value = "com.automation.api.steps")
@ConfigurationParameter(key = "cucumber.plugin", value =
        "pretty," +
                "html:build/reports/cucumber/api/report.html," +
                "json:build/reports/cucumber/api/report.json")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@api")
public class ApiTestRunner {
}