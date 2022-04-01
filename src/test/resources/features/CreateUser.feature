Feature: User

  Background:
    Given I have opened the "chrome" browser

  Scenario: Create a user and everything goes as expected
    Given I write email "Hello@test.com"
    Given I write username "Hello12342300"
    Given I write a password "HellloWorld12!"
    When I click the sign up button
    Then I get the result "Check your email"

  Scenario: Create user with username longer then 100 characters
    Given I write email "Banankladdkaka@test.com"
    Given I write username "Calleskavihjhjar86730000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
    Given I write a password "HelloWoorrldd!839?"
    When I click the sign up button
    Then I get the result "Enter a value less than 100 characters"

  Scenario: Create a user that already exist
    Given I write email "Hello@test.com"
    Given I write username "HELLOWORLD"
    Given I write a password "HelloMyWorld!296"
    When I click the sign up button
    Then I get the result "User already exist"

  Scenario: Create user where email is missing
    Given I write username "Helloooo@test.com"
    Given I write a password "HejSvejs!?903"
    When I click the sign up button
    Then I get the result "Please enter a value"

