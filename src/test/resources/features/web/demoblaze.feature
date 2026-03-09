@web
Feature: DemoBlaze Web Store

  Background:
    Given I open the DemoBlaze homepage

  @smoke
  Scenario: Verify homepage loads successfully
    Then the page title should contain "STORE"
    And the navigation logo should be visible
    And at least 1 product should be displayed

  @login
  Scenario: Successful user login
    When I click the Login button
    Then the Login modal should appear
    When I enter username "testuser123" and password "testpass123"
    And I submit the login form
    Then an alert dialog should appear

  @login
  Scenario: Failed login with invalid credentials
    When I click the Login button
    Then the Login modal should appear
    When I enter username "wronguser" and password "wrongpass"
    And I submit the login form
    Then an alert dialog should appear

  @product
  Scenario: Browse and view product details
    When I click the first product
    Then the product name should not be empty
    And the product price should not be empty
    And the Add to Cart button should be present

  @category
  Scenario: Filter products by category
    When I select the "Phones" category
    And at least 1 product should be displayed

  @cart
  Scenario: Add product to cart
    When I click the first product
    And I click Add to Cart
    Then a confirmation alert should appear

  @cart
  Scenario: View cart page
    When I go to the Cart page
    Then the cart total element should be present

  @navigation
  Scenario: Navigate through pagination
    When I click the Next button
    Then at least 1 product should be displayed
