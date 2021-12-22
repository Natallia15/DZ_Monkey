import org.checkerframework.checker.units.qual.A;
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

import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
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

       /* WebElement element = driver.findElement(By.xpath("//div[@class='blog-article']//a"));

        String startHandle = driver.getWindowHandle();
        element.click();
        Thread.sleep(3000);
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
        String lastHandle = handles.get(1);
        driver.switchTo().window(lastHandle);
        System.out.println(driver.getCurrentUrl());*/

       ArrayList<WebElement> list = new ArrayList<>(driver.findElements(By.xpath("//div[@class='blog-article']//a")));
       // String startHandle = driver.getWindowHandle();
//
//        list.forEach(item ->
//                System.out.println(item.getAttribute("href")));
//        list.forEach(WebElement::click);

       String startHandle = driver.getWindowHandle();

        list.forEach(item -> {
            String expectedResult = item.getText();
            item.click();
            ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(handles.get(1));
            String actualResult = driver.findElement(By.xpath("/html/body/section[1]/div/div/div[1]/div[1]/div/div[1]/h1")).getText();
            Assert.assertEquals(actualResult, expectedResult);
            driver.close();
            driver.switchTo().window(startHandle);

        });

    }

    // ссылки из футера
@Test
public void footerLinksTest () throws InterruptedException {
    List <WebElement> footerList = driver.findElements(By.xpath("//li[@class='footer-menu-item']"));
    footerList.forEach(item ->
            System.out.println(item.getText()));
      footerList.forEach(WebElement::click);

}
    @Test
    public void footerLinksTest2() throws InterruptedException {
        ArrayList <WebElement> footerList = new ArrayList<>(driver.findElements(By.xpath("//a[@class='footer-link']")));
        String startHandle = driver.getWindowHandle();
        ArrayList<String> handles = new ArrayList<>();
        for (WebElement footerElement : footerList) {
            String x = footerElement.getAttribute("href");
            System.out.println(x);
            footerElement.click();
            Thread.sleep(1000);
            String h = driver.getWindowHandle();
           handles.add(h);
                   }


for (int i=0; i<= handles.size(); i++){
    System.out.println(handles.get(i));
//    String hendel = handles.get(i);
//    driver.switchTo().window(hendel);
//   Assert.assertEquals(driver.getCurrentUrl(),x);
}
           // String lastHandle = handles.get(1);

            //driver.switchTo().window(lastHandle);


            System.out.println(driver.getCurrentUrl());

          // Assert.assertEquals(driver.getCurrentUrl(),x);

        }}



//    private void waitElement(By locator){
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));}
//}





