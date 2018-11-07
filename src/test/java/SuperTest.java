package test.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.HomePage;

public class SuperTest {
    private WebDriver driver;
    private String baseUrl;
    private static final String wiley = "WileyPLUS";

    @BeforeClass
    public void setUp() {
//    Before starting the test, copy chromedriver from http://www.seleniumhq.org/download/ and enter correct PATH.!!
        System.setProperty("webdriver.chrome.driver", "/home/osboxes/Downloads/selenium/selenium/chrome_driver/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://www.wiley.com/WileyCDA/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void supertest() throws InterruptedException {
        driver.get(baseUrl);

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.CloseButtonClick();

        String[] names = {"RESOURCES", "SUBJECTS", "ABOUT"};
        String[] urls = {"#", "/en-us/subjects", "/en-us/aboutus"};

        Assert.assertEquals(homePage.elements.size(), names.length);

        for (int i = 0; i < homePage.elements.size(); i++) {
            Assert.assertEquals(homePage.elements.get(i).getText(), names[i]);
            Assert.assertTrue(homePage.elements.get(i).findElement(By.tagName("a")).getAttribute("href").contains(urls[i]));
        }

    }
    @Test
        public void supertest2()throws InterruptedException{
        driver.get(baseUrl);

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.CloseButtonClick();
        homePage.ResourceButtonClick();
        //String[] names = {"Students", "Instructors", "Researchers", "Professionals", "Journalists", "Librarians", "Institutions", "Authors", "Resellers", "Corporations", "Societies"};
        String[] names = {"Authors", "Corporations", "Institutions", "Instructors", "Journalists", "Librarians", "Professionals", "Researchers", "Resellers", "Societies", "Students"};
        Assert.assertEquals(homePage.resources.size(), names.length);

        for (int i = 0; i < homePage.resources.size(); i++) {
            Assert.assertEquals(homePage.resources.get(i).getAttribute("title"), names[i]);
        }
        Thread.sleep(3000);
        }



















    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
