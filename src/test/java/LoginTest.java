
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import po.LoginPage;
import po.ProductPage;
import po.BasePage;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    //za celite na ovaa laboratoriska vezba kreirav nov Gmail account i nov Twitter account
    @Test
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("skitlab09@gmail.com", "skitlab123");
        assertTrue(new ProductPage(driver).isLoaded());
    }

    @Test
    public void canNotLoginWithInvalidUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
       // System.out.println("do ovde okej PRV ASSERTRUE loadirano e");
        loginPage.login("petar2", "secret");
       // System.out.println("logingpage.login");
        String errorMessage = loginPage.getErrorMessage();
       assertEquals(errorMessage, "The username and password you entered did not match our records. Please double-check and try again.");
       // System.out.println("vtor assert");

    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/User/chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}