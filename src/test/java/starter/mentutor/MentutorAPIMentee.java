package starter.mentutor;

import io.cucumber.java.et.Ja;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.stepdef.MentorStepDef;
import starter.utils.Constants;
import java.io.File;
import java.util.Map;

public class MentutorAPIMentee {

    public static String LOGIN_USER = Constants.BASE_URL + "login";

    public static String UPDATE_USER = Constants.BASE_URL + "users";

    public static String GET_ALL_TASK = Constants.BASE_URL + "mentees/tasks";

    public static String ASSIGN_TASK = Constants.BASE_URL + "mentees/submission/{id}";

    public static String ADD_COMENT_STATUS = Constants.BASE_URL + "forum/1";

    public static String GET_ALL_STATUS_FORUM = Constants.BASE_URL + "forum";

    public static String ADD_STATUS_FORUM = Constants.BASE_URL + "forum";

    //Login user
    @Step("Login user with email and password")
    public void postLoginFeature(File json) {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    //Update user
    @Step("Mentee update profile")
    public void putUpdateFeature(String name, String email, String password, File images){
        SerenityRest.given().log().all()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType("multipart/form-data")
                .multiPart("name",name)
                .multiPart("email",email)
                .multiPart("password",password)
                .multiPart("images",images)
                .put(MentutorAPIMentee.UPDATE_USER);
    }

    //Update user
    @Step("Mentee update profile")
    public void UpdateFeature1(Map<String, ?> formData){
        SerenityRest.given().log().all()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType("multipart/form-data")
                .multiPart("name", formData.get("name"))
                .multiPart("email", formData.get("email"))
                .multiPart("password", formData.get("password"))
                .multiPart("images", formData.get("images"));
    }

    //Get all task
    @Step("Get all tasks")
    public void setGetAllTask() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN);
    }

    //Get all task  invalid
    @Step("Get all tasks")
    public void setGetAllInvalidTask() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN_INVALID);
    }

    //Assign task
    @Step("Assign task")
    public void postAssignFeature(File JSON) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType(ContentType.JSON).body(JSON)
                .pathParam("id", MentorStepDef.TASK_ID);
    }

    @Step("Assign task invalid")
    public void postAssignInvalidFeature(File JSON) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType(ContentType.JSON).body(JSON)
                .pathParam("id", MentorStepDef.TASK_ID);
    }

    //Add coment
    @Step("add comment")
    public void postAddFeature(File JSON) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType(ContentType.JSON).body(JSON);
    }

    //Add coment invalid
    @Step("add  comment invalid")
    public void postAddInvalidFeature(File JSON) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType(ContentType.JSON).body(JSON);
    }

    //Forum get all task
    @Step("get all task")
    public void getForumAddFeature() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN);
    }

    //Forum get all task invalid
    @Step("get all task invalid")
    public void getForumAddInvalidFeature() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN);
    }

    //Forum post status
    @Step("Mentee update profile")
    public void postForumAddFeature(String caption,String images){
        SerenityRest.given().log().all()
                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
                .contentType("multipart/form-data")
                .multiPart("caption",caption)
                .multiPart("images",images);
    }

//    @Step("Mentee update profile")
//    public void putUpdateFeature(String name, String email, String password, File images){
//        SerenityRest.given().log().all()
//                .header("Authorization", MentutorResponseMentee.MENTEE_TOKEN)
//                .contentType("multipart/form-data")
//                .multiPart("name",name)
//                .multiPart("email",email)
//                .multiPart("password",password)
//                .multiPart("images",images)
//                .put(MentutorAPIMentee.UPDATE_USER);
//    }

}
