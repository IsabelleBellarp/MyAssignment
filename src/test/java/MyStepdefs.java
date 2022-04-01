import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {

    WebDriver driver;

    @Given("I have opened the {string} browser")
    public void iHaveOpenedTheBrowser(String browser) {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
    }

    @Given("I write email {string}")
    public void iWriteEmail(String email) {
        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys(email);
    }

    @Given("I write username {string}")
    public void iWriteUsername(String username) {
        WebElement Username = driver.findElement(By.id("new_username"));
        Username.sendKeys(username);
    }

    @Given("I write a password {string}")
    public void iWriteAPassword(String password) {
        WebElement Password = driver.findElement(By.id("new_password"));
        Password.sendKeys(password);
    }

    @When("I click the sign up button")
    public void iClickTheSignUpButton() {

        WebElement signUp = driver.findElement(By.xpath("//*[@id=\"create-account\"]"));
        signUp.click();
    }

    @Then("I get the result {string}")
    public void theAccountSucessfullyCreated(String Text) {

        assertEquals("Check your email", driver.findElement(By.className("!margin-bottom--1v3 no-transform center-on-medium")));
        driver.quit();
    }

    @Then("I get result back {string}")
    public void iGetResult(String result) {
        String expected = "Enter a value less than 100 characters";
        assertEquals("Enter a value less than 100 characters long", driver.findElement(By.className("invalid av-text")));
        driver.quit();
    }

    @Then("I get result that user already exist")
    public void UserAlreadyExist(String exist) {
        String expected = "The user already exist";
        assertEquals("The user already exist", driver.findElement(By.xpath("//*[@id=\\\"signup-form\\\"]/fieldset/div[2]/div/span")));
        driver.quit();
    }

    @Then("I get result that email is missing")
    public void EmailIsMissing(String missing) {
        String expected = "Please enter a value";
        assertEquals("Please enter a value", driver.findElement(By.xpath("//*[@id=\\\"signup-form\\\"]/fieldset/div[1]/div/span")));
        driver.quit();
    }

}

