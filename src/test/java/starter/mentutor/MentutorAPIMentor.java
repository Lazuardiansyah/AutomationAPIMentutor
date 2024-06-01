package starter.mentutor;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.checkerframework.checker.units.qual.C;
import starter.stepdef.MentorStepDef;
import starter.utils.Constants;

import java.io.File;

public class MentutorAPIMentor {

    public static String LOGIN_USER = Constants.BASE_URL + "login";

    public static String ADD_TASK = Constants.BASE_URL + "mentors/tasks";

    public static String GET_ALL_TASK = Constants.BASE_URL + "mentors/tasks";

    public static String GET_DETAIL_TASK = Constants.BASE_URL + "mentors/tasks/{id}";

    public static String UPDATE_TASK = Constants.BASE_URL + "mentors/tasks/{id}";

    public static String DELETE_TASK = Constants.BASE_URL + "mentors/tasks/{id}";

    public static String DELETEINVALID_TASK = Constants.BASE_URL + "mentors/tasks/#!@2";

    public static String SUBMIT_SCORE = Constants.BASE_URL + "mentors/submission/2";

    public static String ADD_COMMENTS = Constants.BASE_URL + "forum/1";


    //Login Mentor
    @Step("Login user with email and password")
    public void postLoginFeature(File json) {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    //ADD TASK
    @Step("Mentor Add Task")
    public void postAddTaskFeature(String title, String description, File images, File file, String due_date) {
        SerenityRest.given().log().all()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .contentType("multipart/form-data")
                .multiPart("title", title)
                .multiPart("description", description)
                .multiPart("images", images)
                .multiPart("file", file)
                .multiPart("due_date", due_date)
                .post(MentutorAPIMentor.ADD_TASK);
    }

    @Step("Mentor Add Invalid Task")
    public void postAddInvalidTaskFeature(String title, String description, File images, File file, String due_date) {
        SerenityRest.given().log().all()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .contentType("multipart/form-data")
                .multiPart("title", title)
                .multiPart("description", description)
                .multiPart("images", images)
                .multiPart("file", file)
                .multiPart("due_date", due_date)
                .post(MentutorAPIMentor.ADD_TASK);
    }

    //GET ALL TASK
    @Step("Mentor Get All Task")
    public void setGetAllTask() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN);
    }

    //GET DETAIL TASK
    @Step("Mentor Get Detail Task")
    public void setGetDetailTask(String id) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .pathParam("id", id);
    }

    @Step("Mentor Get Detail With invalid Task")
    public void setGetDetailInvalidTask(String id) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .pathParam("id", id);
    }

    //UPDATE TASK
    @Step("Update Task")
    public void putUpdateTask(String due_date) {
        SerenityRest.given().log().all()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .multiPart("due_date", due_date)
                .pathParam("id", MentorStepDef.TASK_ID);
//                .put(MentutorAPIMentor.UPDATE_TASK);
    }

    @Step("Update Without Authorization Task")
    public void putUpdateInvalidTask(String due_date) {
        SerenityRest.given().log().all()
                .multiPart("due_date", due_date)
                .pathParam("id", MentorStepDef.TASK_ID);
//                .put(MentutorAPIMentor.UPDATE_TASK);
    }

    //DELETE TASK
    @Step("Delete Task")
    public void setDeleteTask() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .pathParam("id", MentorStepDef.TASK_ID);
    }

    @Step("Delete with invalid task")
    public void setDeleteInvalidTask() {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN);
    }

    //SUBMIT SCORE
    @Step("Submit Score")
    public void setSubmitScore(File json) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    //ADD COMMENT
    @Step("Add Comment")
    public void setAddComments(File json) {
        SerenityRest.given()
                .header("Authorization", MentutorResponseMentor.MENTOR_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

}
