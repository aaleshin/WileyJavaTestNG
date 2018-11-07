package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class HomePage {
    public final WebDriver driver;
    @FindBy(css = "#command > div.modal-header > button > span")
    public WebElement closeButton;


    @FindBy(css = "#willey-navbar-collapse > div > div > div > ul > li")
    public List<WebElement> elements;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div/h3/a")
    public List<WebElement> resources;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void CloseButtonClick() {
        closeButton.click();
    }
    public void ResourceButtonClick(){
        Actions actions = new Actions(driver);
        actions.moveToElement(elements.get(0)).build().perform();
    }
}
