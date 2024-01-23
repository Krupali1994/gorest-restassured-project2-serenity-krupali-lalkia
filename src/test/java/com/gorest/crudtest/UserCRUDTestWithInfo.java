package com.gorest.crudtest;

import com.gorest.info.UserSteps;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static com.gorest.utils.TestUtils.getRandomValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTestWithInfo extends TestBase {
    static int id;
    static String name = "Mike";
    static String email = "Mike" + getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Steps
    UserSteps userSteps;

    @Title("This will get all users records")
    @Test
    public void test001() {
        userSteps.getUserIDs();

    }


    @Title("This method will create a new users record and verify it by its ID")
    @Test
    public void test002() {

        ValidatableResponse getId = userSteps.createUserRecord(name, email, gender, status);
        id = getId.extract().path("id");
        ValidatableResponse response = userSteps.getUserIDs();
        ArrayList<?> userId = response.extract().path("id");
        Assert.assertTrue(userId.contains(id));

    }


    @Title("This will update user")
    @Test
    public void test003UpdateUser() {
        ValidatableResponse response = userSteps.updateUserById(id);
        response.statusCode(200);


    }

    @Title("This method will delete the existing record")
    @Test
    public void test004() {
        userSteps.deleteUserById(id).statusCode(204);
        userSteps.getUserById(id).statusCode(404);

    }

}
