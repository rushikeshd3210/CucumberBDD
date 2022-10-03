Feature: Login

  @sanity
  Scenario: Successful Login with valid Credentials
    Given User Launch Chrome Browser
    When User opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page Title Should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title Should be "Your store. Login"
    And close browser

  @regression
  Scenario Outline: Successful Login with valid Credentials DDT
    Given User Launch Chrome Browser
    When User opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Login
    Then Page Title Should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title Should be "Your store. Login"
    And close browser

    Examples: 
      | email               | password |
      | admin@yourstore.com | admin    |
      #| test@yourstore.com  | admin    |
