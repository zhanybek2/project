package api_tests;

import api.asserts.ApiAssert;
import api.controllers.UserController;

import api.entity.get_users.Users;
import api.entity.get_users.UsersResponse;
import api.entity.register.UnSuccessReg;
import api.entity.register.UserRegister;
import api.entity.register.UserRegisterResponse;
import api.entity.create_user.CreateUsersAndUpdate;
import api.entity.user_login.UserLogin;
import api.entity.user_login.UserLoginResponse;
import api.utils.EntityManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserTest extends BaseApiTest {
    UserController userController;


    @BeforeClass(alwaysRun = true)
    public void beforClass() {
        userController = application.getUserController();
    }


    @Test(description = "Получение всех пользователей")
    public void getAllUsersTest() {

        UsersResponse response = userController.getUsers();
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);
    }

    @Test(description = "Получение пользователя по ID")
    public void getUserByIDTest() {
        Users user = userController.getUserById("9");
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);

    }

    @Test (description = "Создаем нового пользователя")
    public void createUserTest() {
        CreateUsersAndUpdate expectedUser = EntityManager.generateUsers();
        CreateUsersAndUpdate actualUser = userController.createUser(expectedUser);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(201);
    }



    @Test (description = "Проверяем успешную регистрацию (с email и паролем)")
    public void successRegistrationTest() {

        UserRegister user = EntityManager.generateUserRegister();
        UserRegisterResponse response = userController.userRegisterResponse(user);
        ApiAssert.assertThat(userController.getResponse()).isCorrectStatusCode(200);
    }

    @Test(description = "Проверяем ошибку (если пароль отсутствует)")
    public void unSuccessRegistrationTest() {

        UserRegister invalidUser = new UserRegister("sydney@fife", null);
        UnSuccessReg errorResponse = userController.unSuccessReg(invalidUser);
        ApiAssert.assertThat(userController.getResponse()).

                isCorrectStatusCode(400);
        Assert.assertEquals("Missing password", errorResponse.getError());

    }
    @Test(description = "Проверяем успешный вход в систему и получение токена")
    public void userLoginTest() {
        UserLogin userLogin = EntityManager.generateUserLogin();
        UserLoginResponse response = userController.userLogin(userLogin);

        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }
    @Test(description = "Удаляем пользователя")
    public void deleteUserTest() {
        userController.deleteUser("7");

        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(204);
    }

    @Test(description = "Проверяем успешное обновление пользователя")
    public void updateUserTest(){
        CreateUsersAndUpdate userUpdate = EntityManager.generateUsers();
        userController.editUser(userUpdate);
    }
}



