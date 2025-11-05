package com.epam.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {


    // ===== Captura de pantalla =====
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = "screenshots/" + fileName;

        try {
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) screenshotDir.mkdirs();

            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.err.println("Error saving screenshot: " + e.getMessage());
            return null;
        }
    }
    // ===== Generador de emails aleatorios =====
    public static String generateRandomEmail() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return "test_" + timestamp + "@example.com";
    }

    // ===== Pausa opcional =====
    public static void sleep(int milliseconds) {
        try { Thread.sleep(milliseconds); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    // ===== Timestamp para logs o nombres de archivos =====
    public static String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    // ===== Limpieza de strings =====
    public static String cleanString(String input) {
        if (input == null) return "";
        return input.trim().replaceAll("\\s+", " ");
    }

    // ===== Extraer solo números de un string =====
    public static String extractNumbers(String input) {
        if (input == null) return "";
        return input.replaceAll("[^0-9]", "");
    }

    // Seleccionar una opción visible en un dropdown
    public static void selectByVisibleText(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    // Seleccionar por valor (opcional)
    public static void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    // Seleccionar por índice (opcional)
    public static void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }


    // Convierte cualquier lista de WebElement a lista de Strings
    public static List<String> getTextFromElements(List<WebElement> elements) {
        List<String> texts = new ArrayList<>();
        for (WebElement e : elements) {
            texts.add(e.getText());
        }
        return texts;
    }


    // Convierte cualquier lista de WebElement a lista de Doubles
    public static List<Double> getDoubleFromElements(List<WebElement> elements) {
        List<Double> prices = new ArrayList<>();
        for (WebElement price : elements) {
            prices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return prices;
    }

    public static WebElement findElementByText(List<WebElement> elements, String text) {
        return elements.stream()
                .filter(el -> el.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element no found: " + text));
    }
}
