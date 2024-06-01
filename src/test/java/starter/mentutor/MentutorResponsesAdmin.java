package starter.mentutor;

import starter.stepdef.AdminStepDef;

public class MentutorResponsesAdmin {

    public static String ADMIN_TOKEN = "Bearer " + AdminStepDef.ADMIN_TOKEN;

//    public static String ADMIN_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJZENsYXNzIjowLCJhdXRob3JpemVkIjp0cnVlLCJleHAiOjE3MTQ3MTY1NjIsInJvbGUiOiJhZG1pbiIsInVzZXJJZCI6MX0.AhO4uz6g4BaYBzmvEYKkZI0l07AuWX08dnxbhxW4D7Q" ;

    public static String USER_NAME_ADMIN = "data.name";

    public static String ID_ALL_USER_ADMIN = "data[0].id_user";

    public static String REGISTER_CLASS_SUCCESS_MESSAGE_ADMIN = "message";

    public static String ID_ALL_CLASS_ADMIN = "data[0].id_class";

    public static String UPDATE_USER_SUCCESS_MESSAGE_ADMIN = "message";

    public static String USER_NAME_UPDATE_SUCCESS = "data.name";

    public static String GET_USER_SUCCESS_MESSAGE_ADMIN = "message";

    public static String USER_NAME_GET_SUCCESS = "data.name";

    public static String UPDATE_CLASS_SUCCESS_MESSAGE_ADMIN = "message";

    public static String CLASS_NAME_UPDATE_SUCCESS = "data.class_name";

    public static String INVALID_MESSAGE_ADMIN = "/message error/";

}
