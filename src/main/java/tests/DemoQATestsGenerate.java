package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyManager;
import java.net.MalformedURLException;

public class DemoQATestsGenerate {
    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_URL2");

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
    public void addAndEditRecordInWebTable() {

        //ADD butonuna tıklama
        WebElement element = driver.findElement(By.cssSelector("#addNewRecordButton"));
        element.click();

        // Yeni kayıt ekranındaki bilgileri doldurun (örneğin, ad ve soyad)
        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
        WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
        WebElement emailInput = driver.findElement(By.cssSelector("#userEmail"));
        WebElement ageInput = driver.findElement(By.cssSelector("#age"));
        WebElement salaryInput = driver.findElement(By.cssSelector("#salary"));
        WebElement departmentInput = driver.findElement(By.cssSelector("#department"));
        WebElement submitBtn = driver.findElement(By.cssSelector("#submit"));

        // Örnek veri girişi
        firstNameInput.sendKeys("Meltem");
        lastNameInput.sendKeys("Sert");
        emailInput.sendKeys("meltemsert29@gmail.com");
        ageInput.sendKeys("30");
        salaryInput.sendKeys("50000");
        departmentInput.sendKeys("EGM");
        submitBtn.click();

        // Eklenen kaydın düzenleme düğmesini tıklayın (örnekte ilk satır düzenleniyor)
        WebElement editBtn = driver.findElement(By.cssSelector("#edit-record-4"));
        editBtn.click();

        WebElement ageEditInput = driver.findElement(By.cssSelector("#age"));
        WebElement salaryEditInput = driver.findElement(By.cssSelector("#salary"));
        WebElement saveBtn = driver.findElement(By.cssSelector("#submit"));

        // Yeni veri girişi
        ageEditInput.clear();
        ageEditInput.sendKeys("29");
        salaryEditInput.clear();
        salaryEditInput.sendKeys("55000");
        saveBtn.click();

    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        webDriver.quitDriver();
    }
}
