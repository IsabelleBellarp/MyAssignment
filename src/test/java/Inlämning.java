import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Random;


public class Inlämning {

    @Test // SKAPA ANVÄNDARE - ALLT GÅR SOM FÖRVÄNTAT
    public void testCreateUser() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("email"));

        String generatedEmail = GenerateRandomString(97, 122, 10);

        email.sendKeys(generatedEmail + "@test.com");
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("new_username"));
        username.sendKeys(generatedEmail + "@test.com");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.id("new_password"));

        String generatedPassword = GenerateRandomString(97, 122, 10);

        password.sendKeys(generatedPassword + "A1?");
        Thread.sleep(2000);

        WebElement signUp = driver.findElement(By.id("create-account"));
        signUp.click();
        Thread.sleep(2000);

        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Success | Mailchimp";
        assertEquals(ExpectedTitle, ActualTitle);

        driver.quit();
    }


    @Test //SKAPA ANVÄNDARE - LÅNGT ANVÄNDARNAMN (MER ÄN 100 TECKEN)

    public void testCreateUserWithLongUserName() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("email"));

        String generatedEmail = GenerateRandomString(97, 122, 10);

        email.sendKeys(generatedEmail + "@test.com");
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("new_username"));

        String generatedUserName = GenerateRandomString(122, 122, 110);

        username.sendKeys(generatedUserName);
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.id("new_password"));

        String generatedPassword = GenerateRandomString(97, 122, 10);

        password.sendKeys(generatedPassword + "A1?");
        Thread.sleep(2000);

        WebElement signUp = driver.findElement(By.id("create-account"));
        signUp.click();
        Thread.sleep(2000);

        WebElement error = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
        String actual = error.getText();
        String expectedText = "Enter a value less than 100 characters long";
        assertEquals(expectedText, actual);

        driver.quit();
    }

    @Test //SKAPA ANVÄNDARE - ANVÄNDARE REDAN UPPTAGEN

    public void createUserWithTakenUserName() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("email"));

        String generatedEmail = GenerateRandomString(97, 122, 10);
        String combinedMail = generatedEmail + "@test.com";
        email.sendKeys(combinedMail);
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("new_username"));
        username.sendKeys(combinedMail);
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.id("new_password"));
        String generatedPassword = GenerateRandomString(97, 122, 10);
        password.sendKeys(generatedPassword + "A1?");
        Thread.sleep(2000);

        WebElement signUp = driver.findElement(By.id("create-account"));
        signUp.click();
        Thread.sleep(2000);

        //CREATE SECOND USER
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(2000);
        WebElement email2 = driver.findElement(By.id("email"));
        email2.sendKeys(GenerateRandomString(97, 122, 10) + "@test.com");
        Thread.sleep(2000);

        WebElement username2 = driver.findElement(By.id("new_username"));
        username2.sendKeys(combinedMail);
        Thread.sleep(2000);

        WebElement password2 = driver.findElement(By.id("new_password"));
        String generatedPassword2 = GenerateRandomString(97, 122, 10);
        password2.sendKeys(generatedPassword2 + "A1?");
        Thread.sleep(2000);

        WebElement signUp2 = driver.findElement(By.id("create-account"));
        signUp2.click();

        WebElement taken = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
        String actual = taken.getText();
        System.out.println(actual);
        String expectedText= "Another user with this username already exists. Maybe it's your evil twin. Spooky.";
        assertEquals(expectedText, actual);

        driver.quit();

    }

    @Test //SKAPA ANVÄNDARE - EMAIL SAKNAS

    public void createUserButMissingEmail() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://login.mailchimp.com/signup/");
        Thread.sleep(2000);
        WebElement email = driver.findElement(By.id("email"));

        String generatedEmail = GenerateRandomString(97, 122, 10);

        email.sendKeys("");
        Thread.sleep(2000);

        WebElement Username = driver.findElement(By.id("new_username"));
        Username.sendKeys(generatedEmail + "@test.com");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.id("new_password"));

        String generatedPassword = GenerateRandomString(97, 122, 10);

        password.sendKeys(generatedPassword + "A1?");
        Thread.sleep(2000);

        WebElement signUp = driver.findElement(By.id("create-account"));
        signUp.click();
        Thread.sleep(2000);


        WebElement emailMissing = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[1]/div/span"));
        String actual = emailMissing.getText();
        String expectedText = "Please enter a value";
        assertEquals(expectedText, actual);


        //WebElement taken = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
        //String actual = taken.getText();
        //System.out.println(actual);
        //String expectedText= "Another user with this username already exists. Maybe it's your evil twin. Spooky.";
        // assertEquals(expectedText, actual);

        driver.quit();


    }

    private String GenerateRandomString(int min, int max, int length) {
        Random random = new Random();
        StringBuilder buf = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = min + (int)
                    (random.nextFloat() * (max - min + 1));
            buf.append((char) randomLimitedInt);
        }
        return buf.toString();
    }
}
