package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


@Slf4j
@Data
public abstract class ApiRequest {

    protected String url;
    protected RequestSpecification requestSpecification;
    protected Response response;
    private static String SLASH = "/";

    public ApiRequest(String url) {
        this.url = url;
        this.requestSpecification = given()
                .baseUri(url);
//                .header("Authorization", "Bearer " + ApiConfigReader.getValue("token"));
    }

    private void logResponse() {
        log.warn("Response is:");
        log.warn(getResponse().getBody().asPrettyString());
        log.warn("Status code is {}", getResponse().getStatusCode());
    }

    protected static String getEndpoint(String... endpoints) {
        StringBuilder endpoint = new StringBuilder();
        for (String arg : endpoints) {
            endpoint.append(arg).append(SLASH);
        }
        return endpoint.substring(0, endpoint.length() - 1);
    }

    public static String formParameter(HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();

    }

    protected Response get(String endPoint) {
        log.info("performed GET {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    protected Response post(String endPoint, String body) {
        log.info("performed POST {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    protected Response post(String endPoint, Map<String, String> params) {
        log.info("performed POST {}", endPoint);
        log.info("Params is {}", params);
        this.response = given()
                .spec(requestSpecification)
                .formParams(params)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    protected Response put(String endPoint, String body) {
        log.info("Performed PUT {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .body(body)
                .put(endPoint);
        logResponse();
        return this.response;
    }
    protected Response delete(String endPoint) {
        log.info("Performed DELETE {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .delete(endPoint);
        logResponse();
        return this.response;
    }
    protected Response put(String endPoint, Map<String, String> params) {
        log.info("Performed PUT {}", endPoint);
        log.info("Params is {}", params);

        // Отправляем PUT запрос с параметрами в теле запроса
        this.response = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)// Спецификация запроса (например, базовые настройки)
                .body(params)  // Передаем параметры в теле запроса
                .put(endPoint);  // Выполняем PUT запрос
        logResponse();  // Логируем ответ

        return this.response;  // Возвращаем ответ
    }

}