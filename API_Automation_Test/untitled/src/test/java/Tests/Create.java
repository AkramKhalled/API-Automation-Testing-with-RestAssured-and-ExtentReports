package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Create extends BaseTest {

    @Test
    public void create() {
        ExtentReportUtil.createTest("Create User API Test");

        Map<String, Object> map = new HashMap<>();
        JSONObject postRequest = new JSONObject(map);

        postRequest.put("email", "akramkhaled309@gmail.com");
        postRequest.put("first_name", "Akram");
        postRequest.put("last_name", "Khaled");

        ExtentReportUtil.logInfo("POST Request: " + postRequest.toJSONString());

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(postRequest.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .assertThat()
                .body("email", Matchers.equalTo("akramkhaled309@gmail.com"))
                .and()
                .body("first_name", Matchers.equalTo("Akram"))
                .and()
                .body("last_name", Matchers.equalTo("Khaled"))
                .log().all()
                .extract().response();

        int statusCode = response.getStatusCode();
        ExtentReportUtil.logInfo("Response status code: " + statusCode);

        if (statusCode == 201) {
            ExtentReportUtil.logPass("Status Code: " + statusCode);
        } else {
            ExtentReportUtil.logFail("Status Code: " + statusCode);
        }

        response.then()
                .assertThat()
                .statusCode(201);

        ExtentReportUtil.logPass("Assertions Passed Successfully");
    }
}
