@regression
Feature: Checkout of authorization

  Background:
    Given start Trello page is open

  Scenario:
    When user insert the RUS language and clicks the logIn button
    Then user inserts his or her e-mail and password and clicks enter button
    Then user confirms his password and clicks enter button
    Then user checks his or her user information
    And user logs out of authorization