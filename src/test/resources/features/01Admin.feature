@01Admin
  Feature: Admin
    As admin i can edit mentor mentee, add class and member


#    Login as admin
  Scenario: login as admin
    Given User login as admin with valid "AdminLogin.json"
    When Send request login user as admin
    Then Status code should be 200
    And Response body should be name "admin"
    And Validate json schema "AdminLoginJsonSchema.json"

#  Register new user as admin
  Scenario: Register new user as admin
    Given Post register new user as admin with valid "AdminRegisterUser.json"
    When Send request register user as admin
    Then Status code should be 201
    And Response body should be name "Dede B"
    And Validate json schema "AdminRegisterUserJsonSchema.json"

#  Get all users as admin
  Scenario: Get all users as admin
    Given Get all user as admin
    When Send request get all users
    Then Status code should be 200
    And Response body should be userId 1
    And Validate json schema "AdminGetAllUsersJsonSchema.json"

  #  Register new class as admin
  Scenario: Register new class as admin
    Given Post register new class as admin with valid "AdminRegisterClass.json"
    When Send request register class as admin
    Then Status code should be 201
    And Response body should be message "Success created"
    And Validate json schema "AdminRegisterClassJsonSchema.json"

#  Get all class as admin
    Scenario: Get all class as admin
      Given Get all class as admin
      When Send request get all class
      Then Status code should be 200
      And Response body should be classId 1
      And Validate json schema "AdminGetAllClassJsonSchema.json"

#  Update user as admin
  Scenario: Update user as admin
    Given Put update user as admin with valid "AdminUpdateUser.json" and id_user 11
    When Send request update user as admin
    Then Status code should be 201
    And Response body should be message "update profile successful" and name "Herry Update"
    And Validate json schema "AdminUpdateUserJsonSchema.json"

#  Get user as admin
  Scenario: Get user as admin
    Given Get user as admin with id_user 11
    When Send request get user
    Then Status code should be 200
    And Response body get user should be message "Success Get Profile" and name "Herry Update"
    And Validate json schema "AdminGetUserJsonSchema.json"

#  Update class as admin
  Scenario: Update class as admin
    Given Put update class as admin with valid "AdminUpdateClass.json" and id_class 16
    When Send request update class as admin
    Then Status code should be 201
    And Response body update class should be message "Update Class Successful" and name "Bahasa Arab Update"
    And Validate json schema "AdminUpdateClassJsonSchema.json"

#  Register new user as admin with invalid json
    Scenario Outline: Register new user as admin with invalid json
      Given Post register new user as admin with invalid "<json>"
      When Send request register user invalid as admin
      Then Status code should be 400
      And Validate json schema "AdminRegisterUserInvalidJsonSchema.json"
      And Response body should be error message "Invalid Input From Client"
      Examples:
        | json                           |
        | AdminRegisterUserInvalid.json  |
        | AdminRegisterUserInvalid1.json |
        | AdminRegisterUserInvalid2.json |

#  Register new class with existing as admin
    Scenario: Register new class with existing as admin
      Given Post register new class as admin with valid "AdminRegisterClass.json"
      When Send request register class as admin
      Then Status code should be 400
      And Validate json schema "AdminRegisterClassInvalidJsonSchema.json"
      And Response body should be error message "inpdut not valid"

#  Update user invalid path as admin
    Scenario Outline: Update user invalid path as admin
      Given Put update user as admin with valid "AdminUpdateUser.json" and invalid id_user "<id_user>"
      When Send request update user as admin
      Then Status code should be 404
      And Validate json schema "AdminUpdateUserInvalidJsonSchema.json"
      And Response body should be error message "User Not Found"
      Examples:
        | id_user      |
        | 1100         |
        | qwert1234    |
        | !@#$%%^&*()- |

#  Update user invalid json as admin
    Scenario: Update user invalid json as admin
      Given Put update user as admin with invalid "AdminUpdateUserInvalid.json" and id_user 11
      When Send request update user as admin
      Then Status code should be 400
      And Validate json schema "AdminUpdateUserInvalidJsonSchema.json"
      And Response body should be error message "Invalid Input From Client"

#  Get user invalid path as admin
    Scenario Outline: Get user invalid path as admin
      Given Get user as admin with invalid id_user "<id_user>"
      When Send request get user
      Then Status code should be 400
      And Validate json schema "AdminGetUserInvalidJsonSchema.json"
      And Response body should be error message "Invalid Input From Client"
      Examples:
        | id_user      |
        | 1100         |
        | qwert1234    |
        | !@#$%%^&*()- |

#  Update class invalid path as admin
  Scenario Outline: Update class invalid path as admin
    Given Put update class as admin with valid "AdminUpdateClass.json" and invalid id_class "<id_class>"
    When Send request update class as admin
    Then Status code should be 400
    And Validate json schema "AdminUpdateClassInvalidJsonSchema.json"
    And Response body should be error message "Invalid Input From Client"
    Examples:
      | id_class     |
      | 1100         |
      | qwert1234    |
      | !@#$%%^&*()- |

#  Update class invalid json as admin
  Scenario Outline: Update class invalid path as admin
    Given Put update class as admin with invalid "<json>" and id_class 16
    When Send request update class as admin
    Then Status code should be 400
    And Validate json schema "AdminUpdateClassInvalidJsonSchema.json"
    And Response body should be error message "Invalid Input From Client"
    Examples:
      | json                          |
      | AdminUpdateClassInvalid.json  |
      | AdminUpdateClassInvalid2.json |

##    Delete user as admin
#  Scenario: Delete user as admin
#    Given Delete user with valid id 26
#    When Send request delete user
#    Then Status code should be 200
#    And Response body should be message "Delete Success"
#
##    Delete class as admin
#  Scenario: Delete class as admin
#    Given Delete class with valid id 11
#    When Send request delete class
#    Then Status code should be 200
#    And Response body should be message "Success Delete Class"