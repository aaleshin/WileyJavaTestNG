package test.java;

import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkResources(homePage);

        homePage.studentsLink.click();
        checkStudent(PageFactory.initElements(driver, StudentPage.class));

        Actions actions = new Actions(driver);
        actions.moveToElement(homePage.subjectsLink).build().perform();
        homePage.GoToEducation();

        checkEducation();
        homePage.logo.click();
        checkHomePage();
 /* 6 task contains  bug: "6.	Do not enter anything in the search input and press search button
                            -	Nothing happens, home page is still displayed"
    But if I click on search button - actual result is new webpage- https://www.wiley.com/en-ru/search?pq=%7Crelevance    */
        homePage.searchButton.click();
        checkEmptySearchPage();
        homePage.logo.click();
        checkFollowingWindows(PageFactory.initElements(driver, HomePage.class));
        homePage.searchButton.click();
        checkMathFilter(PageFactory.initElements(driver, HomePage.class));
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
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(page.resourcesLink));
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
        Assert.assertTrue(driver.getTitle().startsWith("Students"));
        Assert.assertTrue(page.isLinkPresent(wiley));
        Assert.assertEquals("http://wileyplus.wiley.com/", page.williyPlusLink.getAttribute("href"));
    }

    private void checkEducation() {
        Assert.assertTrue(driver.getTitle().startsWith("Education"));
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

    private void checkHomePage() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.wiley.com/en-ru", url);
    }

    private void checkEmptySearchPage() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.wiley.com/en-ru/search?pq=%7Crelevance", url);
    }

    private void checkFollowingWindows(final HomePage page) {
        page.searchInput.sendKeys("Math");

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(page.nextDiv));

        Assert.assertTrue(page.nextDiv.isDisplayed());

        int bottom = page.searchInput.getLocation().y + page.searchInput.getSize().height;
        int top = page.nextDiv.getLocation().y;
        Assert.assertEquals(top, bottom);

        Collection<WebElement> links = driver.findElements(By.cssSelector("#ui-id-2 div.ui-menu-item > a"));
        Assert.assertEquals(links.size(), 4);

        int count = 0;
        for (int i = 0; i < links.size(); ++i) {
            WebElement el = ((List<WebElement>) links).get(i);
            if (el.getText().startsWith("Math"))
                ++count;
        }
        Assert.assertEquals(4, count);

        WebElement parent = driver.findElement(By.cssSelector("#ui-id-2 .search-related-content section.products-preview-block"));
        WebElement title = parent.findElement(By.cssSelector("header h5"));
        Collection<WebElement> products = driver.findElements(By.cssSelector(".related-content-products > section.ui-menu-item h3.product-title > a"));

        Assert.assertEquals("Related Content", title.getText());

        int productCount = 0;
        for (int i = 0; i < products.size(); ++i) {
            WebElement el = ((List<WebElement>) links).get(i);
            if (el.getText().contains("Math"))
                ++productCount;
        }
        Assert.assertEquals(4, productCount);
    }


    private void checkMathFilter(final HomePage page) {
        Collection<WebElement> items = driver.findElements(By.cssSelector(".search-result-content .products-list > section.product-item"));

        Assert.assertEquals(10, items.size());

        ArrayList<String> titles = new ArrayList<String>();

        for (int i = 0; i < items.size(); ++i) {
            WebElement item = ((List<WebElement>) items).get(i);
            WebElement title = item.findElement(By.cssSelector(".product-content h3.product-title > a"));
            Collection<WebElement> cartButton = item.findElements(By.cssSelector(".table-row-content button[type=\"submit\"]"));

            String text = item.getText();
            Assert.assertTrue(text.contains("Math"));
            titles.add(text);

            int buttonCount = 0;
            for (int c = 0; c < cartButton.size(); ++c) {
                WebElement e2 = ((List<WebElement>) cartButton).get(c);
                if (e2.getText().toLowerCase().equals("add to cart"))
                    ++buttonCount;
            }

            Assert.assertTrue(buttonCount >= 1);
        }

        page.searchInput.sendKeys("Math");
        page.searchButton.click();

        ArrayList<String> newTitles = new ArrayList<String>();
        items = driver.findElements(By.cssSelector(".search-result-content .products-list > section.product-item"));
        for (int i = 0; i < items.size(); ++i) {
            WebElement item = ((List<WebElement>) items).get(i);
            newTitles.add(item.getText());
        }

        Assert.assertEquals(newTitles, titles);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
