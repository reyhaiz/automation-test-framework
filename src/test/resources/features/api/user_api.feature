@api
Feature: DummyAPI User Management API Tests
  As an API consumer
  I want to be able to manage users via the DummyAPI
  So that I can perform CRUD operations on user data
  
  @api @get-user
  Scenario: Get user by valid ID
    Given I have a valid user ID "60d0fe4f5311236168a109ca"
    When I send a GET request to get user by ID
    Then the response status code should be 200
    And the response should contain user ID "60d0fe4f5311236168a109ca"
    And the response should contain fields "firstName", "lastName", "email"
  
  @api @get-user
  Scenario: Get user by invalid ID returns error
    Given I have an invalid user ID "invalid_id_123"
    When I send a GET request to get user by ID
    Then the response status code should be 400
  
  @api @create-user
  Scenario: Create a new user successfully
    Given I have the following user data:
      | firstName | lastName | email                        |
      | John      | Doe      | johndoe.test@automation.com  |
    When I send a POST request to create a user
    Then the response status code should be 200
    And the response should contain firstName "John"
    And the response should contain lastName "Doe"
    And the response should contain an "id" field
    And the created user ID should be saved
  
  @api @create-user
  Scenario: Create user with missing required fields returns error
    Given I have incomplete user data with only firstName "InvalidUser"
    When I send a POST request to create a user
    Then the response status code should be 400
  
  @api @update-user
  Scenario: Update an existing user
    Given a user exists with firstName "John" lastName "Doe" email "johndoe.update@automation.com"
    When I update the user's firstName to "Jane" and lastName to "Smith"
    Then the response status code should be 200
    And the response should contain firstName "Jane"
    And the response should contain lastName "Smith"
  
  @api @delete-user
  Scenario: Delete an existing user
    Given a user exists with firstName "Delete" lastName "Me" email "deleteme.test@automation.com"
    When I send a DELETE request for that user
    Then the response status code should be 200
  
  @api @tags
  Scenario: Get list of tags
    When I send a GET request to retrieve all tags
    Then the response status code should be 200
    And the response should contain a list of tags
    And each tag should be a non-empty string