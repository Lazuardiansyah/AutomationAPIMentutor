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
import starter.mentutor.MentutorAPIMentee;
import starter.utils.Constants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MenteeStepDef {

    @Steps
    MentutorAPIMentee mentutorAPIMentee;
    String textResponse = "";

    public static String MENTEE_TOKEN;


    //login
    @Given("Login user with email and password valid json {string}")
    public void loginUserWithEmailAndPasswordValidJson(String json) {
        File jsonFile = new File(Constants.REQ_BODY+json);
        mentutorAPIMentee.postLoginFeature(jsonFile);
    }

    @When("Send request post mentee login")
    public void sendRequestPostMenteeLogin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIMentee.LOGIN_USER);
        textResponse = response.asString();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("data.token");
        System.out.println(token);
        MENTEE_TOKEN = token;
    }

//    @Then("Status code should be {int}")
//    public void statusCodeShouldBe(int statusShould) {
//        SerenityRest.then()
//                .statusCode(statusShould);
//    }


//    @And("Validate json schema {string}")
//    public void validateJsonSchema(String json) {
//        File jsonFile = new File(Constants.JSON_SCHEMA + json);
//        SerenityRest.and().body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
//    }

    @Given(": Login user with invalid {string}")
    public void loginUserWithInvalid(String json) {
        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIMentee.postLoginFeature(jsonFile);
    }

    //update profile
    @Given("Update profile user as mentee with valid data name {string}, email {string}, password {string}, images {string}")
    public void CpdateProfileUserAsMenteeWithValidDataNameEmailPasswordImages(String name, String email, String password, String images) {
        File imageFile = new File (Constants.FORM+images);
        mentutorAPIMentee.putUpdateFeature(name,email,password,imageFile);
    }

    @When("Sent request put update profile")
    public void sentRequestPutUpdateProfile() {
        SerenityRest.when()
                .put(MentutorAPIMentee.UPDATE_USER);
    }

//    @And("Valide json schema {string}")
//    public void valideJsonSchema(String json) {
//        File jsonFile = new File (Constants.JSON_SCHEMA+ json);
//        SerenityRest.and().body(JsonSchemaValidator.matchesJsonSchema(jsonFile));
//    }

//    @Then("Status code and should be {int}")
//    public void statusCodeAndShouldBe(int statusCodeAnd){
//        SerenityRest.then().statusCode(statusCodeAnd);
//    }
    //////////////////////////
    @Given("Update profile user as Mentee with other data name {string}, email {string}, password {string}, images {string}")
    public void updateProfileUserAsMenteeWithValidDataNameEmailPasswordImages(String name, String email, String password, String images){
        Map<String, Object> formData = ValidData(name,email,password,images);
        mentutorAPIMentee.UpdateFeature1(formData);
    }
    private Map<String, Object> ValidData(String name, String email, String password, String images) {
        Map<String, Object> formData = new HashMap<>();
        formData.put("name", name);
        formData.put("email", email);
        formData.put("password", password);
        formData.put("images", images);
        return formData;
    }

    @When("Sent request update profile")
    public void sentRequestUpdateProfile() {
        SerenityRest.when().put(MentutorAPIMentee.UPDATE_USER);
    }


    //get all task
    @Given("Get all task by Mentee")
    public void getAllTaskByMentee() {
        mentutorAPIMentee.setGetAllTask();
    }

    @When("Sent request get all task by Mentee")
    public void sentRequestGetAllTaskByMentee() {
        SerenityRest.when().get(MentutorAPIMentee.GET_ALL_TASK);
    }


    @Then("Status code get should be code {int}")
    public void statusCodeGetShouldBeCode(int statusCode) {
        SerenityRest.and().statusCode(statusCode);
    }


    @Given("Get all task by Mentee with invalid token")
    public void getAllTaskByMenteeWithInvalidToken() {
        mentutorAPIMentee.setGetAllInvalidTask();
    }


    @When("Sent request get all task by Mentee with invalid token")
    public void sentRequestGetAllTaskByMenteeWithInvalidToken() {
    }

    //assign task
    @Given("Assign task Mentee with valid json {string}")
    public void assignTaskMenteeWithValidJson(String JSON) {
        File jsonFile = new File(Constants.REQ_BODY + JSON);
        mentutorAPIMentee.postAssignFeature(jsonFile);
    }

    @When("Sent request assign by mentee")
    public void sentRequestAsssignByMentee() {
        SerenityRest.when()
                .post(MentutorAPIMentee.ASSIGN_TASK);
    }


    @Given("Assign task Mentee with invalid json {string}")
    public void addComentStatusWithInvalidJson(String JSON) {
        File jsonFile = new File(Constants.REQ_BODY + JSON);
        mentutorAPIMentee.postAssignInvalidFeature(jsonFile);
    }


    @Then("Status code should be code {int}")
    public void statusCodeShouldBeCode(int statusShould) {
        SerenityRest.then()
                .statusCode(statusShould);

    }


    //add coment on status
    @Given("Add comment status with valid token {string}")
    public void addCommentStatusWithValidToken(String JSON) {
        File jsonFile = new File(Constants.REQ_BODY + JSON);
        mentutorAPIMentee.postAddFeature(jsonFile);
    }


    @When("Sent request add comment status with valid token")
    public void sentRequestAddComentStatusWithValidToken() {
        SerenityRest.when().post(MentutorAPIMentee.ADD_COMENT_STATUS);
    }

    @Given("Add comment status with invalid json {string}")
    public void addCommentStatusWithInvalidJson(String JSON) {
        File jsonFile = new File(Constants.REQ_BODY + JSON);
        mentutorAPIMentee.postAddInvalidFeature(jsonFile);
    }

    @When("Sent request add comment status with invalid json")
    public void sentRequestAddComentStatusWithInvalidJson() {
        SerenityRest.when().post(MentutorAPIMentee.ADD_COMENT_STATUS);
    }

    //get forum
    @Given("Get all forum task status by mentor and mentee with valid token mentee")
    public void getAllTaskStatusByMentorAndMenteeWithValidTokenMentee() {
        mentutorAPIMentee.getForumAddFeature();
    }


    @When("Send request get all task by mentor and mentee with valid token mentee")
    public void sendRequestGetAllTaskByMentorAndMenteeWithValidTokenMentee() {
        SerenityRest.when().get(MentutorAPIMentee.GET_ALL_STATUS_FORUM);
    }

    @Given("Get all forum task status by mentor and mentee with invalid data")
    public void getAllForumTaskStatusByMentorAndMenteeWithInvalidData() {
        mentutorAPIMentee.getForumAddInvalidFeature();
    }

    @When("Send request get all task by mentor and mentee with invalid data")
    public void sendRequestGetAllTaskByMentorAndMenteeWithInvalidData() {
        SerenityRest.when().get(MentutorAPIMentee.GET_ALL_STATUS_FORUM);
    }

    //Forum add status
    @Given("Post add status forum by mentee with valid data caption {string}, images {string}")
    public void postAddStatusForumByMenteeWithValidDataCaptionImages(String caption, String images) {
        mentutorAPIMentee.postForumAddFeature(caption,images);
    }

    @When("Send request post add status forum")
    public void sendRequestAddStatusForum() {
        SerenityRest.when().post(MentutorAPIMentee.ADD_STATUS_FORUM);
    }

}
