package com.epam.automation.tests;

import com.epam.automation.base.BaseTest;
import com.epam.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;


    @BeforeMethod
    public void setUpPage() {loginPage = new LoginPage(driver);}

    @Test(description = "UC-1 Test Login form with empty credentials")
    public void testLoginWithEmptyFields() {
        loginPage.loginWithEmptyFields("error", "error123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "No error message appeared when the fields were empty");
        System.out.println("Check the error messages: " + loginPage.getErrorMassagetext());
    }

    @Test(description = "UC-2 Test Login form with credentials by passing Username")
    public void testLoginConPasswordInvalido() {
        loginPage.loginWithEmptyPassword("standard_user", "password_incorrect");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "No error message displayed");
        System.out.println("Check the error messages: " + loginPage.getErrorMassagetext());
    }

    @Test(description = "UC-3 Test Login form with credentials by passing Username & Password")
    public void testLoginSuccessful() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isLoginSucessfull(), "The login failed or the page title is incorrect");
        System.out.println("The login was successful and the page title was validated correctly");

    }
}


//import com.epam.automation.base.BaseTest;
//import com.epam.automation.pages.LoginPage;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//
//public class LoginTest extends BaseTest {
//
//    private LoginPage loginPage;
//    private static final Logger logger = LogManager.getLogger(LoginTest.class);
//
//    @BeforeMethod
//    public void setUpPage() {
//        loginPage = new LoginPage(driver);
//    }
//
//    // 📦 DataProvider con los 3 casos de uso (UC-1, UC-2, UC-3)
//    @DataProvider(name = "loginData")
//    public Object[][] loginData() {
//        return new Object[][]{
//                // UC-1: Empty fields
//                {"standard_user", "standard_user", "Username is required"},
//                // UC-2: Username provided, password cleared
//                {"standard_user", "", "Password is required"},
//                // UC-3: Valid credentials
//                {"standard_user", "secret_sauce", "Success"}
//        };
//    }
//
//    @Test(dataProvider = "loginData", description = "UC-1, UC-2, UC-3 Login Scenarios")
//    public void testLoginUCs(String username, String password, String expectedMessage) {
//        logger.info("=== Starting test with Username: '{}' and Password: '{}' ===", username, password);
//
//        // Lógica según el caso de uso
//        if (username.isEmpty() && password.isEmpty()) {
//            logger.info("Executing UC-1: Empty credentials");
//            loginPage.loginWithEmptyFields(username, password);
//        } else if (!username.isEmpty() && password.isEmpty()) {
//            logger.info("Executing UC-2: Password cleared");
//            loginPage.loginWithEmptyPassword(username, password);
//        } else {
//            logger.info("Executing UC-3: Valid credentials");
//            loginPage.login(username, password);
//        }
//
//        // Validaciones
//        if (expectedMessage.equals("Success")) {
//            Assert.assertTrue(loginPage.isLoginSucessfull(), "Login should be successful");
//            logger.info("UC-3 passed: Login successful!");
//        } else {
//            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
//
//            // Validar que el mensaje de error contenga lo que esperamos
//            String actualMessage = loginPage.getErrorMassagetext();
//            Assert.assertTrue(actualMessage.contains(expectedMessage),
//                    "Expected error message not found. Expected: '" + expectedMessage + "', Actual: '" + actualMessage + "'");
//
//            logger.info("UC passed: Error message '{}' validated successfully", expectedMessage);
//        }
//    }
//}
