package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;


public class DemoQATests {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_URL");

    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoqaPageTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "DEMOQA");
    }

    @Test
    public void clickButtonAndReadMessage() {

        // Buttons seçeneğine tıklama
        WebElement buttonsOption = driver.findElement(By.cssSelector("#item-4"));
        buttonsOption.click();

        // Click Me düğmesine tıklama
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Butonları bulun
        List<WebElement> clickButtons = driver.findElements(new By.ByCssSelector("button[type=button]"));

//        Optional<WebElement> clickButton= clickButtons.stream().filter(webElement ->
//                webElement.getText().equals("Click Me")).findFirst();
//        clickButton.ifPresent(WebElement::click);

 //     List içindeki her bir elemanı kontrol etme
        for (WebElement button : clickButtons) {

            // Bekleme ekleyin (elementin tıklanabilir olmasını bekleyin)
            wait.until(ExpectedConditions.elementToBeClickable(button));

            // Butonun metnini kontrol edin ve eğer "Click Me" ise tıklayın
            if ("Click Me".equals(button.getText())) {
                button.click();
                break; // İlk eşleşmeyi bulduktan sonra döngüden çıkın
            }
        }
        // Görünen mesajı okuma
        WebElement messageElement = driver.findElement(By.cssSelector("#dynamicClickMessage"));
        String messageText = messageElement.getText();
        String expectedMessage = "You have done a double click";

        // Mesajı kontrol et
        Assert.assertEquals(messageText, expectedMessage);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        webDriver.quitDriver();
    }
}