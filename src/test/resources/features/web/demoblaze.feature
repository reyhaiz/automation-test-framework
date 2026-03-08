@web
Feature: DemoBlaze E-Commerce Web UI Tests
  As a user of DemoBlaze
  I want to be able to browse, login, and purchase products
  So that I can complete my shopping experience
  
  Background:
    Given I open the DemoBlaze homepage
  
  @web @smoke
  Scenario: Verify homepage loads successfully
    Then the homepage title should be "STORE"
    And the navigation logo should be displayed
    And products should be displayed on the homepage
  
  @web @login
  Scenario: Successful user login
    When I click on the Login button
    Then the Login modal should be displayed
    When I enter username "testuser123" and password "testpass123"
    And I click the Login submit button
    Then I should be logged in successfully
  
  @web @login
  Scenario: Failed login with invalid credentials
    When I click on the Login button
    Then the Login modal should be displayed
    When I enter username "wronguser" and password "wrongpass"
    And I click the Login submit button
    Then an alert should appear with invalid credentials message
  
  @web @product
  Scenario: Browse and view product details
    When I click on the first available product
    Then I should be on the product detail page
    And the product name should be displayed
    And the product price should be displayed
    And the Add to Cart button should be visible
  
  @web @product
  Scenario: Filter products by category
    When I filter products by category "Phones"
    Then only phone products should be displayed
  
  @web @cart
  Scenario: Add product to cart
    Given I am logged in as "testuser123" with password "testpass123"
    When I click on the first available product
    And I click the Add to Cart button
    Then I should see a confirmation alert
    When I navigate to the Cart page
    Then the cart should contain at least 1 item
  
  @web @cart
  Scenario: View empty cart
    When I navigate to the Cart page
    Then the cart total should be displayed
  
  @web @navigation
  Scenario: Navigate through pagination
    Then I should be able to navigate to the next page
    And more products should be displayed