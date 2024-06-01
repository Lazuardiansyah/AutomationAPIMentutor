package starter.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.mentutor.MentutorAPIAdmin;
import starter.mentutor.MentutorResponsesAdmin;
import starter.utils.Constants;

import java.io.File;
import java.util.List;
import java.util.Map;


import static org.hamcrest.Matchers.equalTo;

public class AdminStepDef {

    @Steps
    MentutorAPIAdmin mentutorAPIAdmin;

    String textResponse = "";

    public static String ADMIN_TOKEN;

    public static int ID_CLASS;

    public static int ID_USER;

//    LOGIN AS ADMIN
    @Given("User login as admin with valid {string}")
    public void userLoginAsAdminWithValid(String json) {

        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.loginAsAdmin(jsonFile);

    }

    @When("Send request login user as admin")
    public void sendRequestLoginUserAsAdmin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIAdmin.LOGIN_AS_ADMIN);
        textResponse = response.asString();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("data.token");
        System.out.println("Token Admin: " + token);
        ADMIN_TOKEN = token;
    }

//    REGISTER NEW USER VALID
    @Given("Post register new user as admin with valid {string}")
    public void postRegisterNewUserAsAdminWithValid(String json) {

        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.registerNewUserAsAdmin(jsonFile);
    }

    @When("Send request register user as admin")
    public void sendRequestRegisterUserAsAdmin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIAdmin.REGISTER_NEW_USERS_ADMIN);
        textResponse = response.asString();
    }

    @And("Response body should be name {string}")
    public void responseBodyShouldBeName(String name) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.USER_NAME_ADMIN, equalTo(name));
    }

    @Then("Status code should be {int}")
    public void statusCodeShouldBe(int statusCode) {
        SerenityRest.then()
                .statusCode(statusCode);
    }

    @And("Validate json schema {string}")
    public void validateJsonSchema(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA + json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));

    }

//    GET ALL USERS AS ADMIN
    @Given("Get all user as admin")
    public void getAllUserAsAdmin() {
        mentutorAPIAdmin.setGetAllUsersAdmin();
    }

    @When("Send request get all users")
    public void sendRequestGetAllUsers() {
        Response response = SerenityRest.when()
                .get(MentutorAPIAdmin.GET_ALL_USERS_ADMIN);
        textResponse = response.asString();
        JsonPath jsonPath = response.jsonPath();

        // Mendapatkan data JSON dalam bentuk list
        List<Map<String, Object>> dataList = jsonPath.getList("data");

        // Mendapatkan jumlah data dalam list
        int dataSize = dataList.size();

        // Mencari id terakhir dengan role "mentee"
        int lastMenteeId = -1; // Inisialisasi dengan nilai default
        for (int i = dataSize - 1; i >= 0; i--) {
            Map<String, Object> data = dataList.get(i);
            String role = (String) data.get("role");
            if ("mentee".equals(role)) {
                lastMenteeId = (Integer) data.get("id_user");
                break;
            }
        }

        if (lastMenteeId != -1) {
            System.out.println("ID Mentee terakhir: " + lastMenteeId);
        } else {
            System.out.println("Tidak ada Mentee.");
        }

        ID_USER = lastMenteeId;
    }

    @And("Response body should be userId {int}")
    public void responseBodyShouldBeUserId(int userId) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.ID_ALL_USER_ADMIN, equalTo(userId));
    }


//    REGISTER NEW CLASS AS ADMIN
    @Given("Post register new class as admin with valid {string}")
    public void postRegisterNewClassAsAdminWithValid(String json) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.registerNewClassAsAdmin(jsonFile);
    }

    @When("Send request register class as admin")
    public void sendRequestRegisterClassAsAdmin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIAdmin.REGISTER_NEW_CLASS_ADMIN);
        textResponse = response.asString();
    }

    @And("Response body should be message {string}")
    public void responseBodyShouldBeMessage(String message) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.REGISTER_CLASS_SUCCESS_MESSAGE_ADMIN, equalTo(message));
    }


//    GET ALL CLASS AS ADMIN
    @Given("Get all class as admin")
    public void getAllClassAsAdmin() {
        mentutorAPIAdmin.setGetAllClassAdmin();
    }


    @When("Send request get all class")
    public void sendRequestGetAllClass() {
        Response response = SerenityRest.when()
                .get(MentutorAPIAdmin.GET_ALL_CLASS_ADMIN);
        textResponse = response.asString();


        JsonPath jsonPath = response.jsonPath();

        // Mendapatkan data JSON dalam bentuk list
        List<Map<String, Object>> dataList = jsonPath.getList("data");

        // Mendapatkan jumlah data dalam list
        int dataSize = dataList.size();

        // Mengekstrak id_class terakhir
        if (dataSize > 0) {
            Map<String, Object> lastData = dataList.get(dataSize - 1);
            ID_CLASS = (Integer) lastData.get("id_class");
            System.out.println("ID Class terakhir: " + ID_CLASS);
        } else {
            System.out.println("Tidak ada data.");
        }

    }

    @And("Response body should be classId {int}")
    public void responseBodyShouldBeClassId(int classId) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.ID_ALL_CLASS_ADMIN, equalTo(classId));
    }


//    UPDATE USER AS ADMIN
    @Given("Put update user as admin with valid {string} and id_user {int}")
    public void putUpdateUserAsAdminWithValidAndUser_id(String json, int id_user) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.setUpdateUserAdmin(jsonFile, id_user);
    }

    @When("Send request update user as admin")
    public void sendRequestUpdateUserAsAdmin() {
        Response response = SerenityRest.when()
                .put(MentutorAPIAdmin.UPDATE_USER_ADMIN);
        textResponse = response.asString();
    }

    @And("Response body should be message {string} and name {string}")
    public void responseBodyShouldBeMessageAndName(String message, String name) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.UPDATE_USER_SUCCESS_MESSAGE_ADMIN, equalTo(message))
                .body(MentutorResponsesAdmin.USER_NAME_UPDATE_SUCCESS, equalTo(name));
    }


//    GET USER AS ADMIN
    @Given("Get user as admin with id_user {int}")
    public void getUserAsAdminWithId_user(int id_user) {
        mentutorAPIAdmin.setGetUserAdmin(id_user);
    }

    @When("Send request get user")
    public void sendRequestGetUser() {
        Response response = SerenityRest.when()
                .get(MentutorAPIAdmin.GET_USER_ADMIN);
        textResponse = response.asString();
    }

    @And("Response body get user should be message {string} and name {string}")
    public void responseBodyGetUserShouldBeMessageAndName(String message, String id_user) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.GET_USER_SUCCESS_MESSAGE_ADMIN, equalTo(message))
                .body(MentutorResponsesAdmin.USER_NAME_GET_SUCCESS, equalTo(id_user));
    }


//    UPDATE CLASS AS ADMIN
    @Given("Put update class as admin with valid {string} and id_class {int}")
    public void putUpdateClassAsAdminWithValidAndId_class(String json, int id_class) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.setUpdateClassAdmin(jsonFile, id_class);
    }

    @When("Send request update class as admin")
    public void sendRequestUpdateClassAsAdmin() {
        Response response = SerenityRest.when()
                .put(MentutorAPIAdmin.UPDATE_CLASS_ADMIN);
        textResponse = response.asString();
    }

    @And("Response body update class should be message {string} and name {string}")
    public void responseBodyUpdateClassShouldBeMessageAndName(String message, String class_name) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.UPDATE_CLASS_SUCCESS_MESSAGE_ADMIN, equalTo(message))
                .body(MentutorResponsesAdmin.CLASS_NAME_UPDATE_SUCCESS, equalTo(class_name));
    }


//    REGISTER USER AS ADMIN INVALID
    @Given("Post register new user as admin with invalid {string}")
    public void postRegisterNewUserAsAdminWithInvalid(String json) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.registerNewUserAsAdmin(jsonFile);
    }


    @When("Send request register user invalid as admin")
    public void sendRequestRegisterUserInvalidAsAdmin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIAdmin.REGISTER_NEW_USERS_ADMIN);
        textResponse = response.asString();
    }

    @And("Response body should be error message {string}")
    public void responseBodyShouldBeErrorMessage(String message) {
        SerenityRest.and().log().all()
                .body(MentutorResponsesAdmin.INVALID_MESSAGE_ADMIN, equalTo(message));
    }


//    UPDATE USER INVALID AS ADMIN
    @Given("Put update user as admin with valid {string} and invalid id_user {string}")
    public void putUpdateUserAsAdminWithValidAndInvalidId_user(String json, String id_user) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.setUpdateUserInvalidAdmin(jsonFile, id_user);
    }


//    UPDATE USER INVALID JSON AS ADMIN
    @Given("Put update user as admin with invalid {string} and id_user {int}")
    public void putUpdateUserAsAdminWithInvalidAndId_user(String json, int id_user) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.setUpdateUserInvalidJsonAdmin(jsonFile, id_user);
    }


//    GET USER INVALID AS ADMIN
    @Given("Get user as admin with invalid id_user {string}")
    public void getUserAsAdminWithInvalidId_user(String id_user) {
        mentutorAPIAdmin.setGetUserInvalidAdmin(id_user);
    }

//    UPDATE CLASS INVALID PATH AS ADMIN
    @Given("Put update class as admin with valid {string} and invalid id_class {string}")
    public void putUpdateClassAsAdminWithValidAndInvalidId_class(String json, String id_class) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.setUpdateClassInvalidPathAdmin(jsonFile, id_class);
    }


//    UPDATE CLASS INVALID JSON AS ADMIN
    @Given("Put update class as admin with invalid {string} and id_class {int}")
    public void putUpdateClassAsAdminWithInvalidAndId_class(String json, int id_class) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.setUpdateClassAdmin(jsonFile, id_class);
    }

//    DELETE USER AS ADMIN
    @Given("Delete user with valid id")
    public void deleteUserWithValidId() {
        mentutorAPIAdmin.setDeleteUserAdmin();
    }

    @When("Send request delete user")
    public void sendRequestDeleteUser() {
        Response response = SerenityRest.when()
                .delete(MentutorAPIAdmin.DELETE_USER_ADMIN);
        textResponse = response.asString();
    }

//    DELETE CLASS AS ADMIN
    @Given("Delete class with valid id")
    public void deleteClassWithValidId() {
        mentutorAPIAdmin.setDeleteClassAdmin();
    }

    @When("Send request delete class")
    public void sendRequestDeleteClass() {
        Response response = SerenityRest.when()
                .delete(MentutorAPIAdmin.DELETE_CLASS_ADMIN);
        textResponse = response.asString();
    }
}
