package com.example.camerarent.bdd;

import com.example.camerarent.service.UserService;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserSignupSteps {

    private UserService userService;
    private String username;
    private String password;
    private String result;

    @Given("I have entered a valid username {string}")
    public void i_have_entered_a_valid_username(String username) {
        this.username = username;
    }

    @Given("I have entered a valid password {string}")
    public void i_have_entered_a_valid_password(String password) {
        this.password = password;
    }

    @Given("I have entered an invalid username {string}")
    public void i_have_entered_an_invalid_username(String username) {
        this.username = username;
    }

    @Given("I have entered an invalid password {string}")
    public void i_have_entered_an_invalid_password(String password) {
        this.password = password;
    }

    @When("I submit the signup form")
    public void i_submit_the_signup_form() {
        try {
            userService = new UserService();
            userService.signup(username, password);
            result = "Success";
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
    }

    @Then("my account should be created successfully")
    public void my_account_should_be_created_successfully() {
        assertEquals("Success", result);
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedMessage) {
        assertEquals(expectedMessage, result);
    }
}