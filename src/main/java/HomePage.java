package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage {
    @FindBy(css = "button.large.button-dark-gray")
    public WebElement changeLocation;

    @FindBy(xpath = "//*[@id=\"main-header-navbar\"]/ul[1]/li[1]/a")
    public WebElement resourcesLink;

    @FindBy(xpath = "//*[@id=\"main-header-navbar\"]/ul[1]/li[2]/a")
    public WebElement subjectsLink;

    @FindBy(xpath = "//*[@id=\"Level1NavNode1\"]/ul/li[1]/a")
    public WebElement studentsLink;

    @FindBy(xpath = "//*[@id=\"Level1NavNode2\"]/ul/li[9]/a")
    private WebElement educationLink;

    @FindBy(xpath = "//*[@id=\"main-header-container\"]/div/div[1]/div/div/div/a/img")
    public WebElement logo;

    @FindBy(css = "div.input-group > span > button")
    public WebElement searchButton;

    @FindBy(id = "js-site-search-input")
    public WebElement searchInput;

    @FindBy(xpath = "//div[contains(@class,'input-group')]/following-sibling::div")
    public WebElement nextDiv;



    public void GoToEducation() {
        educationLink.click();

    }
    public boolean isLogoPresent() {
        return logo.isDisplayed();
    }

}
