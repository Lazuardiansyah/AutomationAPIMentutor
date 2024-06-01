package starter.mentutor;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.stepdef.AdminStepDef;
import starter.utils.Constants;

import java.io.File;

public class MentutorAPIAdmin {

    public static String LOGIN_AS_ADMIN = Constants.BASE_URL + "login";

    public static String REGISTER_NEW_USERS_ADMIN = Constants.BASE_URL + "admin/users";

    public static String GET_ALL_USERS_ADMIN = Constants.BASE_URL + "admin/users";

    public static String REGISTER_NEW_CLASS_ADMIN = Constants.BASE_URL + "admin/classes";

    public static String GET_ALL_CLASS_ADMIN = Constants.BASE_URL + "admin/classes";

    public static String UPDATE_USER_ADMIN = Constants.BASE_URL + "admin/users/{id_user}";

    public static String GET_USER_ADMIN = Constants.BASE_URL + "admin/users/{id_user}";

    public static String UPDATE_CLASS_ADMIN = Constants.BASE_URL + "admin/classes/{id_class}";

    public static String DELETE_USER_ADMIN = Constants.BASE_URL + "admin/users/{id_user}";

    public static String DELETE_CLASS_ADMIN = Constants.BASE_URL + "admin/classes/{id_class}";

//    ADMIN
    @Step("Login as admin")
    public void loginAsAdmin(File json) {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Register new user as admin")
    public void registerNewUserAsAdmin(File json) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Get all users as admin")
    public void setGetAllUsersAdmin() {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN);
    }

    @Step("Register new class as admin")
    public void registerNewClassAsAdmin(File json) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Get all class as admin")
    public void setGetAllClassAdmin() {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN);
    }

    @Step("Update user as admin")
    public void setUpdateUserAdmin(File json, int id_user) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_user", id_user)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Get user as admin")
    public void setGetUserAdmin(int id_user) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_user", id_user);
    }

    @Step("Update class as admin")
    public void setUpdateClassAdmin(File json, int id_class) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_class", id_class)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Update user as admin")
    public void setUpdateUserInvalidAdmin(File json, String id_user) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_user", id_user)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Get user invalid as admin")
    public void setGetUserInvalidAdmin(String id_user) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_user", id_user);
    }

    @Step("Update class invalid path as admin")
    public void setUpdateClassInvalidPathAdmin(File json, String id_class) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_class", id_class)
                .contentType(ContentType.JSON)
                .body(json);
    }
    @Step("Update user invalid json as admin")
    public void setUpdateUserInvalidJsonAdmin(File json, int id_user) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_user", id_user)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Delete user as admin")
    public void setDeleteUserAdmin() {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_user", AdminStepDef.ID_USER);
    }

    @Step("Delete class as admin")
    public void setDeleteClassAdmin() {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .pathParam("id_class", AdminStepDef.ID_CLASS);
    }
}