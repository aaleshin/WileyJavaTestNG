package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class StudentPage {
    private final WebDriver driver;
    public StudentPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isLinkPresent(String text) {
        return isElementPresent(By.linkText(text));
    }
}
