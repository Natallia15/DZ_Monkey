import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BasePage {

    private String login = "15_03_1980@mail.ru";
    private String password = "06052007Yura";

    LoginPage(WebDriver driver) {
        super(driver);}

        public String getLogin(){
            return login;
        }

        public String getPassword (){
            return password;
        }




    }


