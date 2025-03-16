Feature: Purchase the Order from Ecommerce Website

  Background:
    Given I land on Ecommerce Page

  @regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <email> and password <password>
    When I add the product <productName> from cart
    And Checkout <productName> and submit the order
    Then Message <message> is displayed on confirmation page

    Examples:
      | email             | password    | productName | message                 |
      | anshika@gmail.com | Iamking@000 | ZARA COAT 3 | THANKYOU FOR THE ORDER. |