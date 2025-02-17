package api.application;

import api.controllers.UserController;
import lombok.Data;

@Data
public class Application {
private UserController userController;
    public Application(String url) {
        this.userController = new UserController(url);
    }
}
