package Tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.when;

public class Delete extends BaseTest {

    @Test
    public void delete() {
        ExtentReportUtil.createTest("Delete User API Test");

        when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);

        ExtentReportUtil.logPass("User deleted successfully");
    }
}
