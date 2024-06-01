@03Mentee
Feature: Mentee & forum


#login mentee
  Scenario: Login as Mentee
    Given Login user with email and password valid json "LoginUserValid.json"
    When Send request post mentee login
    Then Status code should be 200
    And Validate json schema "LoginEmailPasswordSchema.json"

#update mentee
  Scenario: Update profile as Mentee with valid data
    Given Update profile user as mentee with valid data name "Katyusya Lala", email "xwisekid@gmail.com", password "Xwisekid123!", images "zero.jpg"
    When Sent request put update profile
    Then Status code should be 201
    And Validate json schema "ResponseUpdateValidDataSchema.json"

  Scenario: Update profile as Mentee with another data
    Given Update profile user as Mentee with other data name "", email "xwisekid@gmail.com", password "Xwisekid123!", images "zero.jpg"
    When Sent request update profile
    Then Status code should be 400
    And Validate json schema "ResponseUpdateInvalidDataSchema.json"

#get all task
  Scenario: Get all task Mentee by valid data
    Given Get all task by Mentee
    When Sent request get all task by Mentee
    Then Status code get should be code 200
    And Validate json schema "GetAllTaskValidDataSchema.json"

#  Scenario: Get all task Mentee by invalid token
#    Given Get all task by Mentee with invalid token
#    When Sent request get all task by Mentee with invalid token
#    Then Status code get should be code 400

#assign all task
  Scenario: Assign task Mentee valid json
    Given Assign task Mentee with valid json "AssignTaskValidJson.json"
    When Sent request assign by mentee
    Then Status code should be code 201
    And Validate json schema "AssignTaskValidJsonSchema.json"

  Scenario: Assign task Mentee invalid json
    Given Assign task Mentee with invalid json "AssignTaskInvalidJson.json"
    When Sent request assign by mentee
    Then Status code should be code 400

#add comment om status
  Scenario: Add comment on status with valid token
    Given Add comment status with valid token "AddCommentValidToken.json"
    When Sent request add comment status with valid token
    Then Status code should be code 201
    And Validate json schema "AddCommentValidTokenSchema.json"

  Scenario: Add comment on status with invalid body json
    Given Add comment status with invalid json "InvalidComment.json"
    When Sent request add comment status with invalid json
    Then Status code should be code 400
    And Validate json schema "InvalidCommentSchema.json"

#get all forum
  Scenario: Get all forum status by mentor and mentee with valid token mentee
    Given Get all forum task status by mentor and mentee with valid token mentee
    When Send request get all task by mentor and mentee with valid token mentee
    Then Status code should be code 200
    And Validate json schema "ForumGetAllTaskSchema.json"

#  Scenario: Get all forum status by mentor and mentee invalid data
#    Given Get all forum task status by mentor and mentee with invalid data
#    When Send request get all task by mentor and mentee with invalid data
#    Then Status code should be code 200
#    And Validate json schema "InvalidForumGetAllTaskSchema.json"

#add status forum by mentee
  Scenario: Add status forum by mentee with valid token mentee
    Given Post add status forum by mentee with valid data caption "halo gimana?", images "tugas.jpg"
    When Send request post add status forum
    Then Status code should be code 201
    And Validate json schema "AddStatusForumSchema.json"

  Scenario Outline: Post login user with invalid data
    Given : Login user with invalid "<JSON>"
    When Send request post mentee login
    Then Status code should be 400
    Examples:
      | JSON                                         |  |
      | LoginUserInvalidEmail.json                   |  |
      | LoginUserInvalidPassword.json                |  |
      | LoginUserInvalidEmailAndInvalidPassword.json |  |