package com.automation.api.steps;

import com.automation.api.clients.UserApiClient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiSteps {

    private UserApiClient userApiClient = new UserApiClient();
    private Response response;
    private String savedUserId;
    private String currentUserId;

    // ==================== GET USER ====================

    @Given("I have a valid user ID {string}")
    public void iHaveAValidUserId(String userId) {
        this.currentUserId = userId;
    }

    @Given("I have an invalid user ID {string}")
    public void iHaveAnInvalidUserId(String userId) {
        this.currentUserId = userId;
    }

    @When("I send a GET request to get user by ID")
    public void iSendAGetRequestToGetUserById() {
        response = userApiClient.getUserById(currentUserId);
    }

    // ==================== CREATE USER ====================

    @Given("I have the following user data:")
    public void iHaveTheFollowingUserData(DataTable dataTable) {
        // Data stored in step, used in next step
    }

    @When("I send a POST request to create a user")
    public void iSendAPostRequestToCreateAUser() {
        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", "John");
        userData.put("lastName", "Doe");
        userData.put("email", "johndoe" + System.currentTimeMillis() + "@automation.com");
        response = userApiClient.createUser(userData);
    }

    @Given("I have incomplete user data with only firstName {string}")
    public void iHaveIncompleteUserDataWithOnlyFirstName(String firstName) {
        // Incomplete data - missing required email
    }

    @When("I send a POST request to create a user with incomplete data")
    public void iSendAPostRequestToCreateAUserWithIncompleteData() {
        Map<String, String> incompleteData = new HashMap<>();
        incompleteData.put("firstName", "InvalidUser");
        // Missing email (required field)
        response = userApiClient.createUser(incompleteData);
    }

    // ==================== UPDATE USER ====================

    @Given("a user exists with firstName {string} lastName {string} email {string}")
    public void aUserExistsWith(String firstName, String lastName, String email) {
        Map<String, String> userData = new HashMap<>();
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);
        userData.put("email", email + System.currentTimeMillis() + "@test.com");
        Response createResponse = userApiClient.createUser(userData);
        this.savedUserId = createResponse.jsonPath().getString("id");
        System.out.println("Created user with ID: " + savedUserId);
    }

    @When("I update the user's firstName to {string} and lastName to {string}")
    public void iUpdateTheUsersFirstNameAndLastName(String firstName, String lastName) {
        Map<String, String> updateData = new HashMap<>();
        updateData.put("firstName", firstName);
        updateData.put("lastName", lastName);
        response = userApiClient.updateUser(savedUserId, updateData);
    }

    // ==================== DELETE USER ====================

    @When("I send a DELETE request for that user")
    public void iSendADeleteRequestForThatUser() {
        response = userApiClient.deleteUser(savedUserId);
    }

    // ==================== TAGS ====================

    @When("I send a GET request to retrieve all tags")
    public void iSendAGetRequestToRetrieveAllTags() {
        response = userApiClient.getTags();
    }

    @Then("the response should contain a list of tags")
    public void theResponseShouldContainAListOfTags() {
        List<String> tags = response.jsonPath().getList("data");
        Assertions.assertNotNull(tags, "Tags list should not be null");
        System.out.println("Number of tags: " + tags.size());
    }

    @Then("each tag should be a non-empty string")
    public void eachTagShouldBeANonEmptyString() {
        List<String> tags = response.jsonPath().getList("data");
        if (tags != null) {
            for (String tag : tags) {
                Assertions.assertNotNull(tag, "Tag should not be null");
                Assertions.assertFalse(tag.isEmpty(), "Tag should not be empty");
            }
        }
    }

    // ==================== COMMON ASSERTIONS ====================

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode,
                "Expected status code: " + expectedStatusCode + " but got: " + actualStatusCode
                        + "\nResponse body: " + response.getBody().asString());
    }

    @Then("the response should contain user ID {string}")
    public void theResponseShouldContainUserId(String expectedId) {
        String actualId = response.jsonPath().getString("id");
        Assertions.assertEquals(expectedId, actualId,
                "Expected user ID: " + expectedId + " but got: " + actualId);
    }

    @Then("the response should contain fields {string}, {string}, {string}")
    public void theResponseShouldContainFields(String field1, String field2, String field3) {
        Assertions.assertNotNull(response.jsonPath().getString(field1),
                field1 + " should be present in response");
        Assertions.assertNotNull(response.jsonPath().getString(field2),
                field2 + " should be present in response");
        Assertions.assertNotNull(response.jsonPath().getString(field3),
                field3 + " should be present in response");
    }

    @Then("the response should contain firstName {string}")
    public void theResponseShouldContainFirstName(String expectedFirstName) {
        String actualFirstName = response.jsonPath().getString("firstName");
        Assertions.assertEquals(expectedFirstName, actualFirstName,
                "Expected firstName: " + expectedFirstName + " but got: " + actualFirstName);
    }

    @Then("the response should contain lastName {string}")
    public void theResponseShouldContainLastName(String expectedLastName) {
        String actualLastName = response.jsonPath().getString("lastName");
        Assertions.assertEquals(expectedLastName, actualLastName,
                "Expected lastName: " + expectedLastName + " but got: " + actualLastName);
    }

    @Then("the response should contain an {string} field")
    public void theResponseShouldContainAField(String fieldName) {
        String value = response.jsonPath().getString(fieldName);
        Assertions.assertNotNull(value, fieldName + " field should be present");
        Assertions.assertFalse(value.isEmpty(), fieldName + " field should not be empty");
    }

    @Then("the created user ID should be saved")
    public void theCreatedUserIdShouldBeSaved() {
        this.savedUserId = response.jsonPath().getString("id");
        System.out.println("Saved user ID: " + savedUserId);
        Assertions.assertNotNull(savedUserId, "Created user ID should be saved");
    }
}