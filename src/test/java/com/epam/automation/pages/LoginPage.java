package com.epam.automation.pages;

import com.epam.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class LoginPage extends BasePage {

    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement userNameField;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMenssage;

    @FindBy(className = "app_logo")
    private WebElement headerTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void loginWithEmptyFields(String userName, String password) {

        waitForElementToBeVisible(userNameField);
        click(userNameField);
        userNameField.sendKeys(userName);

        safeClear(userNameField);

        waitForElementToBeVisible(passwordField);
        click(passwordField);
        passwordField.sendKeys(password);

        safeClear(passwordField);

        click(loginButton);
    }

    public void loginWithEmptyPassword(String userName, String password) {

        waitForElementToBeVisible(userNameField);
        userNameField.clear();
        userNameField.sendKeys(userName);

        waitForElementToBeVisible(passwordField);
        passwordField.sendKeys(password);

        safeClear(passwordField);

        click(loginButton);
    }

    public void login(String userName, String password) {

        waitForElementToBeVisible(userNameField);
        userNameField.sendKeys(userName);

        waitForElementToBeVisible(passwordField);
        passwordField.sendKeys(password);

        click(loginButton);
    }


    public boolean isLoginSucessfull() {

        try {
            waitForElementToBeVisible(headerTitle);
            return headerTitle.getText().trim().equals("Swag Labs");
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isErrorMessageDisplayed() {
        try {

            waitForElementToBeVisible(errorMenssage);
            return errorMenssage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMassagetext() {
        try {
            return errorMenssage.getText();
        } catch (Exception e) {
            return "No error found";
        }
    }
}
