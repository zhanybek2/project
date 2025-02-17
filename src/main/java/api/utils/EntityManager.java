package api.utils;

import api.entity.register.UserRegister;
import api.entity.register.UserRegisterResponse;
import api.entity.create_user.CreateUsersAndUpdate;
import api.entity.user_login.UserLogin;
import com.github.javafaker.Faker;

public class EntityManager {
    private static final Faker faker = new Faker();

    public static CreateUsersAndUpdate generateUsers() {
        return CreateUsersAndUpdate.builder()  // Используется паттерн Builder для создания экземпляра Users.
                .name(faker.name().name())  // Генерирует случайное имя.
                .job(faker.job().title())  // Генерирует случайную профессию.
                .build();
    }

    public static UserRegister generateUserRegister() {
        return UserRegister.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

    }


    public static UserLogin generateUserLogin() {
        return UserLogin.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
    }

}
