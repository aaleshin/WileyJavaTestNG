package test.java;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import main.java.HomePage;
import main.java.StudentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SuperTest {
    private WebDriver driver;
    private String baseUrl;
    private static final String wiley = "WileyPLUS";

    @BeforeClass
    public void setUp() throws Exception {
//    Before starting the test, copy chromedriver from http://www.seleniumhq.org/download/ and enter correct PATH.!!
        System.setProperty("webdriver.chrome.driver", "D:\\downloads\\avtotests\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = " http://www.wiley.com/WileyCDA/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void wileyTest() throws Exception {
        driver.get(baseUrl);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        checkTopMenu(homePage);
        checkResources(homePage);

        homePage.studentsLink.click();
        checkStudent(PageFactory.initElements(driver, StudentPage.class));
    }

    private void checkTopMenu(final HomePage page) {
        page.changeLocation.click();

        Collection<WebElement> links = page.topMenu.findElements(By.cssSelector(".navigation-menu-items a"));

        String[] titles = {"RESOURCES", "SUBJECTS", "ABOUT"};

        Assert.assertEquals(titles.length, links.size());
        for (int i = 0; i < links.size(); ++i) {
            WebElement el = ((List<WebElement>) links).get(i);
            Assert.assertEquals("a", el.getTagName());
            Assert.assertEquals(titles[i], el.getText());

        }
        String text = "";
        text.startsWith("Math");
        text.endsWith("sds");
        text.contains("dsds");

//        WebElement el = ((List<WebElement>) links).get(0);
//        Assert.assertEquals("a", el.getTagName());
//        Assert.assertEquals("RESOURCES", el.getText());
//
//        el = ((List<WebElement>) links).get(1);
//        Assert.assertEquals("a", el.getTagName());
//        Assert.assertEquals("SUBJECTS", el.getText());
//
//        el = ((List<WebElement>) links).get(2);
//        Assert.assertEquals("a", el.getTagName());
//        Assert.assertEquals("ABOUT", el.getText());

//        Assert.assertEquals("a", page.resourcesLink.getTagName());
//        Assert.assertEquals("#", page.resourcesLink.getAttribute("href"));
//        Assert.assertEquals("RESOURCES", page.resourcesLink.getText());
//
//        Assert.assertEquals("SUBJECTS", page.subjectsLink.getText());
//        Assert.assertEquals("ABOUT", page.aboutLink.getText());
    }

    private void checkResources(final HomePage page) {
        page.resourcesLink.click();
        Assert.assertEquals("AUTHORS", page.authorsLink.getText());
        Assert.assertEquals("CORPORATIONS", page.corporationsLink.getText());
        Assert.assertEquals("INSTITUTIONS", page.institutionsLink.getText());
        Assert.assertEquals("INSTRUCTORS", page.instructorsLink.getText());
        Assert.assertEquals("LIBRARIANS", page.librariansLink.getText());
        Assert.assertEquals("PROFESSIONALS", page.proffesionalsLink.getText());
        Assert.assertEquals("RESEARCHERS", page.researcherssLink.getText());
        Assert.assertEquals("RESELLERS", page.resellersLink.getText());
        Assert.assertEquals("SOCIETIES", page.societiessLink.getText());
        Assert.assertEquals("STUDENTS", page.studentsLink.getText());
//        page.studentsLink.getAttribute("href");

    }

    private void checkStudent(final StudentPage page) {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.wiley.com/en-ru/students", url);
        Assert.assertEquals("Students | Wiley", driver.getTitle());
        Assert.assertTrue(page.isLinkPresent(wiley));

    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
