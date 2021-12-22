import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class HomePageTest {

    private static final By loginInput = By.xpath(".//input[@id='login']");
    private static final By passwordInput = By.xpath(".//input[@id='password']");
    private static final By loginButton = By.xpath(".//button[@type='submit']");
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    private static final By logOutButton = By.xpath("//button[@class = 'user-menu-btn']");
   // public static final By deleteButton = By.xpath("//a[@id='delete-entries']");
    public static final By checkAllButton = By.xpath("//input[@ng-model='model.allChecked']");

    @BeforeTest
    public void setUp() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.get("https://my.monkkee.com/#/");
        Thread.sleep(2000);
        loginPage.find(loginInput).sendKeys(loginPage.getLogin());
        loginPage.find(passwordInput).sendKeys(loginPage.getPassword());
        loginPage.click(loginButton);



    }

// сделать запись
    @Test
    public void addEntry() throws InterruptedException {
        waitElement(HomePage.addButton);
        homePage.find(HomePage.addButton).click();
        waitElement(HomePage.editableArea);
        String testText = UUID.randomUUID().toString();
        homePage.find(HomePage.editableArea).sendKeys(testText);
        homePage.find(HomePage.homeButton).click();

        waitElement(HomePage.itemBodyList);

        List<WebElement> elements = driver.findElements(HomePage.itemBodyList);

        boolean flag = false;

        for (WebElement element : elements) {
            if (element.getText().equals(testText))
                flag = true;
        }

        Assert.assertTrue(flag);

    }
// логаут
    @Test
    public void logOut() throws InterruptedException {
        waitElement(logOutButton);
        homePage.click(logOutButton);
    }

    // найти запись с нужным текстом
    @Test
    public void findMassage() throws InterruptedException {
        waitElement(HomePage.itemBodyList);
        homePage.find(HomePage.itemBodyList);
        //List<WebElement> zapisi = driver.findElements(HomePage.itemBodyList);
       //div[@class='body ']
        String text = "Hi";
       List<WebElement> list = driver.findElements(By.xpath("//div[@class='body '][contains(text(),'" + text + "')]"));
        Assert.assertTrue(list.size()>0, "text is found");

    }
// удалить рандомную запись
    @Test
    public void delMassage() throws InterruptedException {
        for (int i=1; i<4; i++){
        waitElement(HomePage.addButton);
        homePage.find(HomePage.addButton).click();
        waitElement(HomePage.editableArea);
            String testText = UUID.randomUUID().toString();
            homePage.find(HomePage.editableArea).sendKeys(testText);
            homePage.find(HomePage.homeButton).click();

        }

        Random random = new Random ();
        int x = random.nextInt(10);
        System.out.println(x);
        waitElement(HomePage.itemBodyList);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='checkbox-wrapper']//input"));
        Thread.sleep(1000);
        elements.get(x).click();
        waitElement(homePage.deleteButton);
        homePage.click(homePage.deleteButton);
        driver.switchTo().alert().accept();

    }

    @Test
    public void delAllMassage() throws InterruptedException {

        waitElement(HomePage.addButton);
        homePage.find(HomePage.addButton).click();
        waitElement(HomePage.editableArea);
        String testText = UUID.randomUUID().toString();
        homePage.find(HomePage.editableArea).sendKeys(testText);
        homePage.find(HomePage.homeButton).click();

        waitElement(checkAllButton);
        homePage.click(checkAllButton);
        waitElement(homePage.deleteButton);
        homePage.click(homePage.deleteButton);
        driver.switchTo().alert().accept();

    }

    private void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

