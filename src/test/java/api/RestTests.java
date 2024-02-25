package api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class RestTests {


    @BeforeAll
    public static void beforeAllSetup(){
        RestAssured.baseURI = "https://reqres.in/api";
    }
    @Test
    @DisplayName("GET users - json validator org.hamcrest.MatcherAssert")
    @Description("This test attempts to call the /users and validate against the json schema using the org.hamcrest.MatcherAssert")
    @Severity(NORMAL)
    @Owner("Razvan")
    @Link(name = "Regres.in", url = "https://reqres.in/api")
    @Issue("Jira-01")
    public void getUsersValidateJsonHamcrestWay() {

        var response =  given()
               // .log().all() // Log request details
                .when()
                .get("/users") // When: Sending the GET request to /users endpoint
                .then()
               // .log().all() // Log response details
                .statusCode(200) // Then: Verify response status code is 200 OK
                .body("data", not(empty()));


        MatcherAssert.assertThat(
                "Validate json schema",
                response.extract().response().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("usersSchema.json")
        );

    }

    @Test
    @DisplayName("GET users - json validator io.restassured.module.jsv.JsonSchemaValidator")
    @Description("This test attempts to call the /users and validate against the json schema using the io.restassured.module.jsv.JsonSchemaValidator")
    @Severity(NORMAL)
    @Owner("Razvan")
    @Link(name = "Regres.in", url = "https://reqres.in/api")
    @Issue("Jira-02")
    public void GetUsersValidateJsonRestAssuredWay() {

                 given()
                .when()
                    .get("/users")
                .then()
                    .statusCode(200)
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("usersSchema.json"));

    }
}
