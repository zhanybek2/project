package api.controllers;

import api.ApiRequest;

import api.entity.get_users.Users;
import api.entity.get_users.UsersResponse;
import api.entity.register.UnSuccessReg;
import api.entity.register.UserRegister;
import api.entity.register.UserRegisterResponse;
import api.entity.create_user.CreateUsersAndUpdate;
import api.entity.user_login.UserLogin;
import api.entity.user_login.UserLoginResponse;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


import static api.Endpoints.*;

public class UserController extends ApiRequest {
    public UserController(String url) {
        super(url);
    }

    public UsersResponse getUsers() {
        return super.get(getEndpoint(API, USERSPAGE)).as(UsersResponse.class);
    }
//    public Users getUserById(String id) {
//        return super.get(getEndpoint(API, USERS, id)).as(Users.class);
//    }


    public Users getUserBy(By by, String value) {
        HashMap<String, String> parameters = new HashMap<>() {{
            put(by.getKey(), value);
        }};
        return super.get(getEndpoint(API,USERS, formParameter(parameters))).as(Users.class);
    }

    public CreateUsersAndUpdate createUser(CreateUsersAndUpdate user) {
        return super.post(getEndpoint(API, USERS), user.toJson()).as(CreateUsersAndUpdate.class);
    }

    public UserRegisterResponse userRegisterResponse(UserRegister user) {
        return super.post(getEndpoint(API, REGISTER), user.toJson()).as(UserRegisterResponse.class);
    }

    public UnSuccessReg unSuccessReg(UserRegister user) {
        return super.post(getEndpoint(API, REGISTER), user.toJson())
                .as(UnSuccessReg.class);
    }

    public UserLoginResponse userLogin(UserLogin user) {
        return super.post(getEndpoint(API, LOGIN), user.toJson())
                .as(UserLoginResponse.class);
    }
    public void deleteUser(String userId) {
        String endpoint = getEndpoint(API,USERS, userId); // Формируем URL с userId
        super.delete(endpoint);
    }

    public void editUser(CreateUsersAndUpdate user) {
        Map<String, String> params = new HashMap<>() {{

            put("name", user.getName());
            put("job", user.getJob());
        }};
        super.put(getEndpoint(API, USERS, TWO), params);

    }

        @Getter
    public static enum By {
        ID("id"),
        USERNAME("username"),
        EMAIL("email");
        public final String key;

        By(String key) {
            this.key = key;
        }
    }

}
