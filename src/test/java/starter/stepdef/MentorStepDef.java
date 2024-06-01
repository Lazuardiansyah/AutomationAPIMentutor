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
import starter.mentutor.MentutorAPIMentor;
import starter.utils.Constants;

import java.io.File;

public class MentorStepDef {

    //    Mentor Login
    @Steps
    MentutorAPIMentor mentutorAPIMentor;

    String textResponse = "";

    public static String MENTOR_TOKEN;
    public static Integer TASK_ID;


//    Login Mentor

    @Given("Create posts with valid json {string}")
    public void createPostsWithValidJson(String json) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIMentor.postLoginFeature(jsonFile);
    }

    @When("Send request post login")
    public void sendRequestPostLogin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIMentor.LOGIN_USER);
        textResponse = response.asString();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("data.token");
        System.out.println(token);
        MENTOR_TOKEN = token;
    }

//    @Then("Status code should be {int}")
//    public void statusCodeShouldBe(int statusCode) {
//        SerenityRest.and()
//                .statusCode(statusCode);
//    }

    @And("validate posts json {string}")
    public void validatePostsJson(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA + json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }


//    Add Task

    @Given("Add task with valid data title {string}, description {string}, images {string}, file {string}, due_date {string}")
    public void addTaskWithValidDataTitleDescriptionImagesFileDue_date(String title, String description, String images, String file, String due_date) {
        File imageFile = new File(Constants.FORM + images);
        File fileFile = new File(Constants.FORM + file);
        mentutorAPIMentor.postAddTaskFeature(title, description, imageFile, fileFile, due_date);
    }


    @When("Send request post add task")
    public void sendRequestPostAddTask() {
        Response response = SerenityRest.when()
                .post(MentutorAPIMentor.ADD_TASK);
        textResponse = response.asString();
        JsonPath jsonPath = response.jsonPath();
        Integer Task_Id = jsonPath.get("data.id_task");
        System.out.println(Task_Id);
        TASK_ID = Task_Id;
    }


    @Then("Status ADD code should be {int}")
    public void statusADDCodeShouldBe(int statusADDCode) {
        SerenityRest.then()
                .statusCode(statusADDCode);
    }

    @And("validate add task json {string}")
    public void validateAddTaskJson(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA+json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }


    @Given("Add task with invalid data title {string}, description {string}, images {string}, file {string}, due_date {string}")
    public void addTaskWithInvalidDataTitleDescriptionImagesFileDue_date(String title, String description, String images, String file, String due_date) {
        File imageFile = new File(Constants.FORM + images);
        File fileFile = new File(Constants.FORM + file);
        mentutorAPIMentor.postAddInvalidTaskFeature(title, description, imageFile, fileFile, due_date);
    }


    @When("Send request post add task with invalid due_date")
    public void sendRequestPostAddTaskWithInvalidDue_date() {
        SerenityRest.when()
                .post(MentutorAPIMentor.ADD_TASK);
    }


    @Then("Status ADD code invalid due_date should be {int}")
    public void statusADDCodeInvalidDue_dateShouldBe(int statusCode) {
        SerenityRest.then()
                .statusCode(statusCode);
    }

    @And("validate add invalid task json {string}")
    public void validateAddInvalidTaskJson(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA+json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }

//    Get All Task


    @Given("Get all task")
    public void getAllTask() {
        mentutorAPIMentor.setGetAllTask();
    }


    @When("Send request get all task")
    public void sendRequestGetAllTask() {
        SerenityRest.when()
                .get(MentutorAPIMentor.GET_ALL_TASK);
    }

//    @And("Validate json schema {string}")
//    public void validateJsonSchema(String json) {
//        File jsonFile = new File(Constants.JSON_SCHEMA+json);
//        SerenityRest.and()
//                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
//    }


//    Get Detail Task

    @Given("Get detail task with valid {string}")
    public void getDetailTaskWithValid(String task) {
        mentutorAPIMentor.setGetDetailTask(task);
    }

    @When("Send request get detail task")
    public void sendRequestGetDetailTask() {
        SerenityRest.when()
                .get(MentutorAPIMentor.GET_DETAIL_TASK);
    }

    @Then("Status code get detail should be {int}")
    public void statusCodeGetDetailShouldBe(int statusCode) {
        SerenityRest.and()
                .statusCode(statusCode);
    }

    @And("Validate json get detail schema {string}")
    public void validateJsonGetDetailSchema(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA+json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }


    @Given("Get detail task with invalid {string}")
    public void getDetailTaskWithInvalid(String task) {
        mentutorAPIMentor.setGetDetailTask(task);
    }


    @When("Send request get wit invalid detail task")
    public void sendRequestGetWitInvalidDetailTask() {
        SerenityRest.when()
                .get(MentutorAPIMentor.GET_DETAIL_TASK);
    }


    @Then("Status code get detail invalid task should be {int}")
    public void statusCodeGetDetailInvalidTaskShouldBe(int statuscode) {
        SerenityRest.then()
                .statusCode(statuscode);
    }


//    Update Task

    @Given("Update task with valid data due_date {string}")
    public void updateTaskWithValidDataDue_date(String due_date) {
        mentutorAPIMentor.putUpdateTask(due_date);
    }


    @When("Send request put update task")
    public void sendRequestPutUpdateTask() {
        SerenityRest.when()
                .put(MentutorAPIMentor.UPDATE_TASK);
    }

    @And("Validate json update schema {string}")
    public void validateJsonUpdateSchema(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA+json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }



    @Given("Update task with valid data due_date {string} but without bearer token")
    public void updateTaskWithValidDataDue_dateButWithoutBearerToken(String due_date) {
        mentutorAPIMentor.putUpdateInvalidTask(due_date);
    }


//    Delete Task

    @Given("Delete task")
    public void deleteTask() {
        mentutorAPIMentor.setDeleteTask();
    }


    @When("Send request delete task")
    public void sendRequestDeleteTask() {
        SerenityRest.when()
                .delete(MentutorAPIMentor.DELETE_TASK);
    }

    @Then("Status code delete should be {int}")
    public void statusCodeDeleteShouldBe(int statusCode) {
        SerenityRest.then()
                .statusCode(statusCode);
    }

    @Given("Delete task with invalid task")
    public void deleteTaskWithInvalidTask() {
        mentutorAPIMentor.setDeleteInvalidTask();
    }


    @When("Send request delete task with invalid task")
    public void sendRequestDeleteTaskWithInvalidTask() {
        SerenityRest.when()
                .delete(MentutorAPIMentor.DELETEINVALID_TASK);
    }


//    Submit Score

    @Given("Submit Score by mentor with valid json {string}")
    public void submitScoreByMentorWithValidJson(String json) {
        File jsonfile = new File(Constants.REQ_BODY + json);
        mentutorAPIMentor.setSubmitScore(jsonfile);
    }

    @When("Send request submit score by mentor")
    public void sendRequestSubmitScoreByMentor() {
        SerenityRest.when()
                .post(MentutorAPIMentor.SUBMIT_SCORE);
    }

    @And("Validate json submit score schema {string}")
    public void validateJsonSubmitScoreSchema(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA+json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }

    @Then("Status code submit should be {int}")
    public void statusCodeSubmitShouldBe(int statusCode) {
        SerenityRest.then()
                .statusCode(statusCode);
    }

    @Given("Submit Score by mentor with invalid json {string}")
    public void submitScoreByMentorWithInvalidJson(String json) {
        File jsonfile = new File(Constants.REQ_BODY + json);
        mentutorAPIMentor.setSubmitScore(jsonfile);
    }

    @When("Send request submit invalid score by mentor")
    public void sendRequestSubmitInvalidScoreByMentor() {
        SerenityRest.when()
                .post(MentutorAPIMentor.SUBMIT_SCORE);
    }

//    Add Comment

    @Given("Add comment by mentor with valid json {string}")
    public void addCommentByMentorWithValidJson(String json) {
        File jsonfile = new File(Constants.REQ_BODY + json);
        mentutorAPIMentor.setAddComments(jsonfile);
    }


    @When("Send request add comment by mentor")
    public void sendRequestAddCommentByMentor() {
        SerenityRest.when()
                .post(MentutorAPIMentor.ADD_COMMENTS);
    }


    @Then("Status code add comment should be {int}")
    public void statusCodeAddCommentShouldBe(int statusCode) {
        SerenityRest.then()
                .statusCode(statusCode);
    }

    @And("Validate json submit comment schema {string}")
    public void validateJsonSubmitCommentSchema(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA+json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
    }

    @Given("Add comment by mentor with invalid json {string}")
    public void addCommentByMentorWithInvalidJson(String json) {
        File jsonfile = new File(Constants.REQ_BODY + json);
        mentutorAPIMentor.setAddComments(jsonfile);
    }



}
