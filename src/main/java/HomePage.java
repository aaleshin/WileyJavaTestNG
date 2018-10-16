package main.java;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage {
    @FindBy(css = "button.large.button-dark-gray")
    public WebElement changeLocation;

    @FindBy(xpath = "//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li[1]/a")
    public WebElement resourcesLink;

    @FindBy(xpath = "//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li[2]/a")
    public WebElement subjectsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[11]/h3/a")
    public WebElement studentsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS5\"]/div[2]/h3/a")
    private WebElement elLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS5\"]/div[2]/h3/ul/li[2]/a")
    private WebElement educationLink;

    @FindBy(css = "#willey-navbar-collapse > div > div > div > div > div")
    public WebElement logo;

    @FindBy(css = "div.input-group > span > button")
    public WebElement searchButton;

    @FindBy(id = "js-site-search-input")
    public WebElement searchInput;

    @FindBy(xpath = "//div[contains(@class,'input-group')]/following-sibling::div")
    public WebElement nextDiv;


    public void GoToEducation() {
        elLink.click();
        educationLink.click();

    }
}
