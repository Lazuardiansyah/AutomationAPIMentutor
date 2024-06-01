@04Delete
Feature: Delete
  As admin and mentor i can delete

#    Delete user as admin
  Scenario: Delete user as admin
    Given Delete user with valid id
    When Send request delete user
    Then Status code should be 200
    And Response body should be message "Delete Success"

#    Delete class as admin
  Scenario: Delete class as admin
    Given Delete class with valid id
    When Send request delete class
    Then Status code should be 200
    And Response body should be message "Success Delete Class"

    #    Delete Task

         #Positive
  Scenario: Delete task by mentor
    Given Delete task
    When Send request delete task
    Then Status code delete should be 201