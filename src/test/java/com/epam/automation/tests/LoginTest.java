package com.epam.automation.tests;

import com.epam.automation.base.BaseTest;
import com.epam.automation.pages.LoginPage;
import com.epam.automation.utils.TestLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        TestLogger.logger.info("LoginPage initialized");
    }

    @Test(description = "UC-1 Test Login form with empty credentials")
    public void testLoginWithEmptyFields() {
        loginPage.loginWithEmptyFields("error", "error123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "No error message appeared when the fields were empty");
        TestLogger.logger.info("Check the error messages: " + loginPage.getErrorMassagetext());
    }

    @Test(description = "UC-2 Test Login form with credentials by passing Username")
    public void testLoginConPasswordInvalido() {
        loginPage.loginWithEmptyPassword("standard_user", "password_incorrect");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "No error message displayed");
        TestLogger.logger.info("Check the error messages: " + loginPage.getErrorMassagetext());
    }

    @Test(description = "UC-3 Test Login form with credentials by passing Username & Password")
    public void testLoginSuccessful() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isLoginSucessfull(), "The login failed or the page title is incorrect");
        TestLogger.logger.info("The login was successful and the page title was validated correctly");
    }
}



