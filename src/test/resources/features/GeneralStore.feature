@GENERAL_STORE_ALL_SCENARIO
Feature: General Store

  @GENERAL_STORE
  Scenario Outline: Sample test for General Store
    When I set the name field as "<name>"
    And I select the gender as "<gender>"
    And I select the country as "<country>" from dropdown
    Then click on the submit button
    Examples:
      | name        | gender | country    |
      | Sample Name | Female | Bangladesh |


  @GENERAL_STORE_CART
  Scenario Outline: Validate Cart Value for General Store
    When I set the name field as "<name>"
    And I select the gender as "<gender>"
    And I select the country as "<country>" from dropdown
    And click on the submit button
    And add the first two products to cart
    And click on the cart symbol
    Then validate the total value of the cart
    Examples:
      | name        | gender | country    |
      | Sample Name | Female | Bangladesh |