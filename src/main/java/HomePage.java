package main.java;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    @FindBy(css = "button.large.button-dark-gray")
    public WebElement changeLocation;

    @FindBy(xpath = "//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li")
    public WebElement topMenu;

    @FindBy(xpath = "//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li[1]/a")
    public WebElement resourcesLink;

    @FindBy(xpath = "//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li[2]/a")
    public WebElement subjectsLink;

    @FindBy(xpath = "//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li[3]/a")
    public WebElement aboutLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[1]/h3/a")
    public WebElement authorsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[2]/h3/a")
    public WebElement corporationsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[3]/h3/a")
    public WebElement institutionsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[4]/h3/a")
    public WebElement instructorsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[5]/h3/a")
    public WebElement librariansLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[6]/h3/a")
    public WebElement proffesionalsLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[7]/h3/a")
    public WebElement researcherssLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[8]/h3/a")
    public WebElement resellersLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[9]/h3/a")
    public WebElement societiessLink;

    @FindBy(xpath = "//*[@id=\"navigationNode_00000RS2\"]/div[10]/h3/a")
    public WebElement studentsLink;
}
