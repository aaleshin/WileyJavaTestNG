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
import org.openqa.selenium.interactions.Actions;
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
        baseUrl = "http://www.wiley.com/WileyCDA/";
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

        Actions actions = new Actions(driver);
        actions.moveToElement(homePage.subjectsLink).build().perform();
        homePage.GoToEducation();

        checkEducation();
        homePage.logo.click();
        CheckHomePage();

    }

    private void checkTopMenu(final HomePage page) {
        page.changeLocation.click();

        Collection<WebElement> links = driver.findElements(By.xpath("//*[@id=\"willey-navbar-collapse\"]/div/div/div/ul/li/a"));

        String[] titles = {"RESOURCES", "SUBJECTS", "ABOUT"};

        Assert.assertEquals(titles.length, links.size());
        for (int i = 0; i < links.size(); ++i) {
            WebElement el = ((List<WebElement>) links).get(i);
            Assert.assertEquals("a", el.getTagName());
            Assert.assertEquals(titles[i], el.getText());
        }
    }

    private void checkResources(final HomePage page) {
        page.resourcesLink.click();

        Collection<WebElement> resourcesLinks = driver.findElements(By.xpath("//*[@id=\"navigationNode_00000RS2\"]/div/h3/a"));

        String[] resourceTitles = {"AUTHORS", "CORPORATIONS", "INSTITUTIONS", "INSTRUCTORS", "LIBRARIANS", "PROFESSIONALS", "RESEARCHERS", "RESELLERS", "SOCIETIES", "STUDENTS"};

        Assert.assertEquals(10, resourcesLinks.size());
        Assert.assertEquals(resourceTitles.length, resourcesLinks.size());
        for (int i = 0; i < resourcesLinks.size(); ++i) {
            WebElement e2 = ((List<WebElement>) resourcesLinks).get(i);
            Assert.assertEquals("a", e2.getTagName());
            Assert.assertEquals(resourceTitles[i], e2.getText());
        }
    }

    private void checkStudent(final StudentPage page) {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.wiley.com/en-ru/students", url);
        Assert.assertEquals("Students | Wiley", driver.getTitle());
        Assert.assertTrue(page.isLinkPresent(wiley));
        Assert.assertEquals("http://wileyplus.wiley.com/", page.williyPlusLink.getAttribute("href"));
    }

    private void checkEducation() {
        Assert.assertEquals("Education | Subjects | Wiley", driver.getTitle());
        Collection<WebElement> educationLinks = driver.findElements(By.xpath("/html/body/main/div[3]/div/div/div[3]/div[1]/ul/li/a"));

        String[] educationTitles = {"Information & Library Science", "Education & Public Policy", "K-12 General", "Higher Education General", "Vocational Technology", "Conflict Resolution & Mediation (School settings)", "Curriculum Tools- General", "Special Educational Needs", "Theory of Education", "Education Special Topics", "Educational Research & Statistics", "Literacy & Reading", "Classroom Management"};

        Assert.assertEquals(13, educationLinks.size());
        Assert.assertEquals(educationTitles.length, educationLinks.size());
        for (int i = 0; i < educationLinks.size(); ++i) {
            WebElement e2 = ((List<WebElement>) educationLinks).get(i);
            Assert.assertEquals("a", e2.getTagName());
            Assert.assertEquals(educationTitles[i], e2.getText());
        }
    }

    public void CheckHomePage() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.wiley.com/en-ru", url);
    }
    //        String text = "";
//        text.startsWith("Math");
//        text.endsWith("sds");
//        text.contains("dsds");

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
