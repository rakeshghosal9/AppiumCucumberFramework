Feature: General Store

  @GENERAL_STORE
  Scenario Outline: Sample test for General Store
    When I set the name field as "<name>"
    And I select the gender as "<gender>"
    And I select the country as "<country>" from dropdown
    Then click on the submit button
    Examples:
      | name   | gender | country |
      | Rakesh | Female | India   |