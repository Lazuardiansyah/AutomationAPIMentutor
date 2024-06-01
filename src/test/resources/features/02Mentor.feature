@02Mentor
Feature: Mentor


#Login Mentor

  #Positive
  Scenario: Create posts with valid json
    Given Create posts with valid json "LoginMentorValid.json"
    When  Send request post login
    Then Status code should be 200
    And validate posts json "LoginMentorJsonSchema.json"

#     Add Task

  #Positive
  Scenario: Create posts with valid json
    Given Add task with valid data title "TaskAPI", description "TaskTrialAPI", images "image.jpg", file "File.pdf", due_date "2022-12-12"
    When  Send request post add task
    Then Status ADD code should be 201
    And validate add task json "AddTaskValidSchema.json"

#    Get All Task

  Scenario: Get all task
    Given Get all task
    When Send request get all task
    Then Status code should be 201
    And Validate json schema "GetAllTaskSchema.json"


#    Get Detail Task

  #Positive
  Scenario Outline: Get detail task
    Given Get detail task with valid "<task>"
    When Send request get detail task
    Then Status code get detail should be 201
    And Validate json get detail schema "GetDetailTask.json"
    Examples:
      | task |
      | 15   |

  #Negative
  Scenario Outline: Get detail task
    Given Get detail task with invalid "<task>"
    When Send request get wit invalid detail task
    Then Status code get detail invalid task should be 404
    Examples:
      | task |
      | #12A |


#  Update Task

  #Positive
  Scenario: Update task with valid json
    Given Update task with valid data due_date "2024-12-12"
    When  Send request put update task
    Then Status ADD code should be 201
    And Validate json update schema "UpdateTaskSchema.json"

  #Negative
  Scenario: Update task with valid json but without bearer token
    Given Update task with valid data due_date "2024-12-12" but without bearer token
    When  Send request put update task
    Then Status ADD code should be 400


#   Submit Score

   #Positive
  Scenario: Submit Score by mentor with valid json
    Given Submit Score by mentor with valid json "SubmitScore.json"
    When Send request submit score by mentor
    Then Status code submit should be 201
    And Validate json submit score schema "SubmitScoreSchema.json"

  #Negative
  Scenario: Submit Score by mentor with invalid json
    Given Submit Score by mentor with invalid json "InvalidSubmit.json"
    When Send request submit invalid score by mentor
    Then Status code submit should be 400


#    Add Comment

  #Positive
  Scenario: Add comment by mentor with valid json
    Given Add comment by mentor with valid json "AddComment.json"
    When Send request add comment by mentor
    Then Status code add comment should be 201
    And Validate json submit comment schema "AddCommentSchema.json"

  #Negative
  Scenario: Add comment by mentor with invalid json
    Given Add comment by mentor with invalid json "AddInvalidComment.json"
    When Send request add comment by mentor
    Then Status code add comment should be 400


#  Delete task

  #Negative
  Scenario: Delete invalid task by mentor
    Given Delete task with invalid task
    When Send request delete task with invalid task
    Then Status code delete should be 405


#    Login Negative

  #Negative
  Scenario Outline: Create posts with invalid json
    Given Create posts with valid json "<JSON>"
    When  Send request post login
    Then Status code should be 400
    Examples:
      | JSON                                           |
      | LoginMentorInvalidEmail.json                   |
      | LoginMentorInvalidPassword.json                |
      | LoginMentorInvalidEmailAndInvalidPassword.json |


##    Delete Task
#
#         #Positive
#  Scenario: Delete task by mentor
#    Given Delete task
#    When Send request delete task
#    Then Status code delete should be 201

#    Add Task

      #Negative
  Scenario: Create posts with invalid json
    Given Add task with invalid data title "TaskAPI", description "TaskTrialAPI", images "image.jpg", file "File.pdf", due_date "Tahun"
    When  Send request post add task with invalid due_date
    Then Status ADD code invalid due_date should be 400
    And validate add invalid task json "AddTaskInvalidSchema.json"