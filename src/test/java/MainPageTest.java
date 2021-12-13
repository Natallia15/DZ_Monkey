import javafx.css.CssMetaData;
import javafx.css.Styleable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainPageTest {

    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
LoginPageTest loginPageTest;
    private static final By logOutButton = By.xpath("/html/body/header/div[2]/div/div/div[2]/button");

    @BeforeTest
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//      loginPage = new LoginPage(driver);
//      driver.get("https://my.monkkee.com/#/");
//       Thread.sleep(3000);
//        loginPage.find(loginInput).sendKeys(loginPage.getLogin());
//        loginPage.find(passwordInput).sendKeys(loginPage.getPassword());
//        loginPage.click(loginButton);

       driver.get("https://my.monkkee.com/#/entries");
        Thread.sleep(3000);
    }

    @Test
    public void logOutTest(){
        //loginPage.find(loginInput).sendKeys(loginPage.getLogin());
        //loginPage.find(passwordInput).sendKeys(loginPage.getPassword());
        mainPage.click(logOutButton);

       // WebElement logOut = driver.findElement(By.xpath("//button[@class='user-menu-btn][@ng-click='logout($event)']"));
       // logOut.click();
    }
}
