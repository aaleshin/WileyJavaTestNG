package test.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuperTest {
    private WebDriver driver;
    private String baseUrl;
    private static final String wiley = "WileyPLUS";

    @BeforeClass
    public void setUp() {
//    Before starting the test, copy chromedriver from http://www.seleniumhq.org/download/ and enter correct PATH.!!
        //System.setProperty("webdåriver.chrome.driver", "D:\\downloads\\avtotests\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://www.wiley.com/WileyCDA/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void iWont() throws InterruptedException {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("#command > div.modal-header > button > span")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("#willey-navbar-collapse > div > div > div > ul > li"));

        String[] names = {"RESOURCES", "SUBJECTS", "ABOUT"};
        String[] urls = {"#", "/en-us/subjects", "/en-us/aboutus"};

        Assert.assertEquals(elements.size(), names.length);

        for (int i = 0; i < elements.size(); i++){
            Assert.assertEquals(elements.get(i).getText(), names[i]);
            Assert.assertTrue(elements.get(i).findElement(By.tagName("a")).getAttribute("href").contains(urls[i]));
        }
        Thread.sleep(3000);


    }

















    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
