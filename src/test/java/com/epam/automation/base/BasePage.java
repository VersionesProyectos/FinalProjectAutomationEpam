package com.epam.automation.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException("WebDriver must not be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void safeClear(WebElement element) {
        try {
            click(element);
            String value = element.getAttribute("value");
            if (value != null && !value.isEmpty()) {

                for (int i = 0; i < value.length(); i++) {
                    element.sendKeys(Keys.BACK_SPACE);
                }
            }
        } catch (Exception e) {
            System.out.println("Field could not be cleared: " + e.getMessage());
        }
    }
}
