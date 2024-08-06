package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Update extends BaseTest {

    @Test
    public void put() {
        ExtentReportUtil.createTest("Update User API Test");

        JSONObject putRequest = new JSONObject();
        putRequest.put("email", "akramkhaled@gmail.com");
        putRequest.put("first_name", "Akram309");
        putRequest.put("last_name", "Khaled");

        ExtentReportUtil.logInfo("PUT Request: " + putRequest.toJSONString());

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(putRequest.toJSONString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .assertThat()
                .body("email", Matchers.equalTo("akramkhaled@gmail.com"))
                .and()
                .body("first_name", Matchers.equalTo("Akram309"))
                .and()
                .body("last_name", Matchers.equalTo("Khaled"))
                .log().all()
                .extract().response();

        int statusCode = response.getStatusCode();
        ExtentReportUtil.logInfo("Response status code: " + statusCode);

        if (statusCode == 200) {
            ExtentReportUtil.logPass("Status Code: " + statusCode);
        } else {
            ExtentReportUtil.logFail("Status Code: " + statusCode);
        }

        response.then()
                .assertThat()
                .statusCode(200);

        ExtentReportUtil.logPass("Assertions Passed Successfully");
    }
}
