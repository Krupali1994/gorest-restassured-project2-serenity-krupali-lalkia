package com.gorest.info;

import com.gorest.constants.EndPoints;
import com.gorest.constants.Path;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import static com.gorest.utils.TestUtils.getRandomValue;

public class UserSteps {

    static final String token = "03fce5af5bfff118402a50a1961ef191f6ab065e46dcdf6f5d96ea18c9496704";

    @Step("Creating new user record with name: {0}, email: {1}, gender: {2} and status: {3}")
    public ValidatableResponse createUserRecord(String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .post("/users")
                .then()
                .log().all().statusCode(201);
    }

    @Step("Getting all users records")
    public ValidatableResponse getUserIDs() {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.GET_USERS)
                .then().log().all().statusCode(200);
    }


    @Step("Updating user details")
    public ValidatableResponse updateUserById(int userID) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("ABC XYZ MNOP");
        userPojo.setEmail(getRandomValue() + "@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .body(userPojo)
                .put(Path.USER + EndPoints.UPDATE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Getting user details")
    public ValidatableResponse getUserById(int userID) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .get(Path.USER + EndPoints.GET_SINGLE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Deleting user details")
    public ValidatableResponse deleteUserById(int userID) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c8fa4af5449aff5c104930482cc994318f2ae1a097c32bb596799dae3b2f6f3b")
                .header("Connection", "keep-alive")
                .pathParam("userID", userID)
                .when()
                .delete(Path.USER + EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }
}
