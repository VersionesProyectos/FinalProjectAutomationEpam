package com.epam.automation.pages;

import com.epam.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



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
        userNameField.sendKeys(userName);
        userNameField.clear();

        waitForElementToBeVisible(passwordField);
        passwordField.sendKeys(password);
        passwordField.clear();

        click(loginButton);
    }

    public void loginWithEmptyPassword(String userName, String password) {

        waitForElementToBeVisible(userNameField);
        userNameField.sendKeys(userName);

        waitForElementToBeVisible(passwordField);
        passwordField.sendKeys(password);
        passwordField.clear();

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
            // Compara el texto del título con "Swag Labs"
            return headerTitle.getText().trim().equals("Swag Labs");
        } catch (Exception e) {
            return false;
        }

    }
    // Valida que se muestra un mensaje de error
    public boolean isErrorMessageDisplayed() {
        try {

            waitForElementToBeVisible(errorMenssage);
            return errorMenssage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Obtiene el error mensaje
    public String getErrorMassagetext() {
        try {
            return errorMenssage.getText();
        } catch (Exception e) {
            return "No error found";
        }
    }
}
