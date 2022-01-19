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

    // найти запись на странице с нужным текстом - без встроенной стоки поиска
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


    // удалить запись при помощи строки поиска
   @Test
    public void dellSearchMassage() throws InterruptedException {
       // добавляем запись
        waitElement(HomePage.addButton);
       homePage.find(HomePage.addButton).click();
       waitElement(HomePage.editableArea);
       String testText = UUID.randomUUID().toString();
       //String testText = "странно";
       homePage.find(HomePage.editableArea).sendKeys(testText);
       homePage.find(HomePage.homeButton).click();
     //отправляем запись в строку поиска
       Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='appendedInputButton']")));
        WebElement searchField = driver.findElement(By.xpath("//input[@id='appendedInputButton']"));
       Thread.sleep(1000);
        searchField.sendKeys(testText);
        //searchField.submit();
        String expectedResult = searchField.getText();
        Thread.sleep(1000);
       WebElement lupaButton = driver.findElement(By.xpath("//button[@Class='btn btn-primary input-group-addon']"));
       lupaButton.click();
       Thread.sleep(4000);
       // проверяем её наличие
       List <WebElement> list = driver.findElements(By.xpath("//div[@class='body ']"));
       list.forEach(item ->{
           String actualResult = item.getText();
           Assert.assertTrue(actualResult.contains(testText));
               });


// удаляем записи, содержащие этот текст

    }
    @Test
    public void SearchTest() throws InterruptedException {
        //   driver.get("https://www.monkkee.com/en/blog/");
        Thread.sleep(5000);
        String text = "38f4277d-55f7-4782-b570-b2d08dfa2e0a";
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"appendedInputButton\"]"));
        element1.sendKeys(text);
        element1.submit();
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='body ']"));
        list.forEach(item -> {

            String str = item.getText();
            System.out.println(str);
            Assert.assertTrue(str.contains(text));});

    }

    // переход по стрелкам
    @Test
    public void putMassage () throws InterruptedException {
        waitElement(HomePage.itemBodyList);
        homePage.find(HomePage.itemBodyList).click();
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/div/a[4]"));
        element1.click();
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/div/a[5]/i"));
        element2.click();
    }

    private void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

