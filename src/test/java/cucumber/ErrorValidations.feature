Feature: Error at Login


  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given I land on Ecommerce Page
    Given Logged in with username <email> and password <password>
    Then <message> error message is displayed

    Examples:
      | email             | password  | message                      |
      | anshika@gmail.com | Iamking@0 | Incorrect email or password. |
