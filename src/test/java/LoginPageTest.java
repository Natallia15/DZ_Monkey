import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    private static final By loginInput = By.xpath(".//input[@id='login']");
    private static final By passwordInput = By.xpath(".//input[@id='password']");
    private static final By loginButton = By.xpath(".//button[@type='submit']");



    @BeforeTest
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        driver.get("https://my.monkkee.com/#/");
        Thread.sleep(3000);
    }

    @Test
    public void loginTest() throws InterruptedException {
        loginPage.find(loginInput).sendKeys(loginPage.getLogin());
        loginPage.find(passwordInput).sendKeys(loginPage.getPassword());
        loginPage.click(loginButton);
//        System.out.println(driver.getCurrentUrl());
//        driver.get("https://my.monkkee.com/#/entries");
//        System.out.println(driver.getCurrentUrl());
//        Thread.sleep(1000);
//       WebElement logOut = driver.findElement(By.xpath("//button[@class = 'user-menu-btn']"));
//       logOut.click();


    }

    @Test
    public void newsBlockTest (){
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='blog-article']//a"));
        list.forEach(item ->
                System.out.println(item.getText()));
        list.forEach(WebElement::click);

    }

    @Test
    public void logOutTest (){
        WebElement logOut = driver.findElement(By.xpath("/html/body/header/div[2]/div/div/div[2]/button"));
        logOut.click();

    }
}
