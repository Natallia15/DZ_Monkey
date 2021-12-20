import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    WebDriver driver;
    LoginPage loginPage;

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
       // driver.get("https://www.monkkee.com/en/eating-your-own-dog-food/");

        Thread.sleep(3000);
    }

    @Test
    public void loginTest() throws InterruptedException {
        loginPage.find(loginInput).sendKeys(loginPage.getLogin());
        loginPage.find(passwordInput).sendKeys(loginPage.getPassword());
        loginPage.click(loginButton);


    }

    // проверка ссылок из раздела новостей
    @Test
    public void newsLinksTest() throws InterruptedException {
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='blog-article']//a"));
        list.forEach(item ->
                System.out.println(item.getText()));
        list.forEach(WebElement::click);
        }

    // названия раздела новостей
    @Test
    public void newsBlockTest () throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//div[@class='blog-article']//a"));
        String i = element.getText();
        System.out.println(i);
        element.click();
        Thread.sleep(3000);
        System.out.println(driver.getCurrentUrl());
        driver.get("https://www.monkkee.com/en/eating-your-own-dog-food/");
       // WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='post-title']//h1")));
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement title = driver.findElement(By.xpath("//div[@class='post-title']//h1"));
        String o = title.getText();
        System.out.println(o);
        Assert.assertEquals(o, i);
        }
       /* List<WebElement> list = driver.findElements(By.xpath("//div[@class='blog-article']//a"));
        for (WebElement elementt : list) {
            String s = elementt.getText();
            elementt.click();
            WebDriverWait waitt = new WebDriverWait(driver, 30);
            waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='post-title']//h1")));
            WebElement element2 = driver.findElement(By.xpath("//div[@class='post-title']//h1"));
            String text = element2.getText();
            // System.out.println(text);
            Assert.assertEquals(text, s);
        }
    }*/


    //  boolean flag = false;

      /*for (WebElement element : list) {
            String i = element.getText();
            System.out.println(i);
            element.click();

           // WebDriverWait wait = new WebDriverWait(driver,10);
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='post-title']")));
            driver.findElement(By.xpath("//div[@class='post-title']//h1"));
           // String o = element.getText();
            //if(i.equals(o))
             //   flag = true;
        }

        //Assert.assertTrue(flag);*/

    // ссылки из футера
@Test
public void footerLinksTest() throws InterruptedException {
    List <WebElement> footerList = driver.findElements(By.xpath("//li[@class='footer-menu-item']"));
    footerList.forEach(item ->
            System.out.println(item.getText()));
    footerList.forEach(WebElement::click);

}

    private void waitElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));}
}




