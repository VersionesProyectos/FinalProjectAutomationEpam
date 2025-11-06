package com.epam.automation.stepsDefinition;

import com.epam.automation.base.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class loginSteps extends BaseTest {

    private WebDriverWait wait;

    @Before
    public void setUpCucumber() {
        // Inicializa el driver aquí para Cucumber
        driver = initializeDriver("chrome"); // o parametrizar si quieres
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        driver.get(BASE_URL);
    }

    @When("the user enters a username and password")
    public void enterAnyUsernameAndPassword() {
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
    }

    @When("the user clears both fields and clicks the login button")
    public void clearFieldsAndClickLogin() {
        clearFields();
        clickLoginButton();
    }

    @When("the user types any credentials in the username field and enters a password")
    public void enterUsernameAndEmptyPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
    }

    @When("the user clears the password input and clicks the login button")
    public void clearPasswordAndClickLogin() {
        driver.findElement(By.id("password")).clear();
        clickLoginButton();
    }

    @When("the user types valid credentials \\(username and password\\)")
    public void enterValidCredentials() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @When("the user clicks the {string} button")
    public void clickButton(String buttonName) {
        if(buttonName.equalsIgnoreCase("Login")) {
            clickLoginButton();
        }
    }

    @Then("the message {string} should appear")
    public void messageShouldAppear(String expectedMessage) {
        String actualMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        ).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The error message is wrong");
    }

    @Then("the user should be redirected to the dashboard")
    public void redirectedToDashboard() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "The user was not redirected to the dashboard");
    }


    private void clickLoginButton() {
        driver.findElement(By.id("login-button")).click();
    }

    private void clearFields() {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();
    }
}
