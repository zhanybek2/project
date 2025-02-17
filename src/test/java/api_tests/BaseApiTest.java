package api_tests;

import api.application.Application;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static api.Endpoints.URL;

public class BaseApiTest {

    protected Application application;

    @BeforeSuite
    public void setup() {
        application = new Application(URL);
    }



}
