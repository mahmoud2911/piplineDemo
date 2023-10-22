@test
Feature: Date Comparison

  Background:
    Given User is logged in with "mahmoud227572" credentials and in console home page

  Scenario Outline: Verify that compare toggle can be switched on successfully in all views and all charts are fully loaded
    When Click Try our new enhanced dashboard
    And Click "<View>" view
    And Click Compare Toggle
    Then Compare Toggle is switched on and Comparison Date is displayed
    And All charts are fully loaded
    Examples:
      | View  |
      | Day   |
      | Week  |
      | Month |

