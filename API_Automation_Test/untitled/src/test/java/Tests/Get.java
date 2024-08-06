package Tests;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Get extends BaseTest {

    @Test
    public void get() {
        ExtentReportUtil.createTest("GET Users API Test");

        Response response = given()
                .get("https://reqres.in/api/users")
                .then()
                .log().all()
                .extract().response();

        ExtentReportUtil.logInfo("GET Request to /api/users");

        int statusCode = response.getStatusCode();
        ExtentReportUtil.logInfo("Response status code: " + statusCode);

        if (statusCode == 200) {
            ExtentReportUtil.logPass("Status Code: " + statusCode);
        } else {
            ExtentReportUtil.logFail("Status Code: " + statusCode);
        }

        response.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("data[0].id", Matchers.equalTo(1))
                .and()
                .body("data[0].email", Matchers.equalTo("george.bluth@reqres.in"))
                .and()
                .body("data[0].first_name", Matchers.equalTo("George"))
                .body("data[0].last_name", Matchers.equalTo("Bluth"));

        ExtentReportUtil.logPass("Assertions Passed Successfully");
    }
}
