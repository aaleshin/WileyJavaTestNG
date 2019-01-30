package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StudentPage {
    private final WebDriver driver;

    public StudentPage(WebDriver driver) {
        this.driver = driver;
    }

    private boolean isElementPresent(By by) {
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


    @FindBy(xpath = "/html/body/main/div[2]/div/div[1]/div[2]/article/div/p[5]/span/a")
    public WebElement williyPlusLink;

    @FindBy(xpath = "//*[@id=\"main-header-container\"]/div/div[1]/div/div/div/a")
    public WebElement logo;

    public boolean isLogoPresent() {
        return logo.isDisplayed();
    }

}
