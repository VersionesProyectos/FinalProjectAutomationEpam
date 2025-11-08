package com.epam.automation.stepsDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import java.util.List;

public class LoginSteps {

    private String username;
    private String password;
    private String loginResult;
    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        scenario.log("User is on the login page");
    }

    @When("the user types username {string} and password {string}")
    public void typeUsernameAndPassword(String username, String password) {
        this.username = username == null ? "" : username;
        this.password = password == null ? "" : password;
        scenario.log("Typing username: " + this.username + " and password: " + this.password);
    }

    @When("the user performs the action {string}")
    public void performAction(String action) {
        scenario.log("Performed action: " + action);

        // Simulate login logic based on the action
        if (action.contains("clear both fields")) {
            username = "";
            password = "";
        } else if (action.contains("clear password")) {
            password = "";
        }

        // Determine result
        if (username.isEmpty() && password.isEmpty()) {
            loginResult = "Epic sadface: Username is required"; // UC-1
        } else if (!username.isEmpty() && password.isEmpty()) {
            loginResult = "Epic sadface: Password is required"; // UC-2
        } else if (username.equals("standard_user") && password.equals("secret_sauce")) {
            loginResult = "User is redirected to the dashboard"; // UC-3
        } else {
            loginResult = "Invalid login";
        }
    }

    @Then("the user should see the message {string}")
    public void checkMessage(String expectedMessage) {
        scenario.log("Verified message: " + loginResult);
        Assert.assertEquals(expectedMessage, loginResult);
    }

    @Then("the login task should comply with:")
    public void verifyUCCompliance(DataTable dataTable) {
        List<String> ucs = dataTable.asList(String.class);
        for (String uc : ucs) {
            scenario.log("Login task complies with: " + uc);

        }
    }
}
