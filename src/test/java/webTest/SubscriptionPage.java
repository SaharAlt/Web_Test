package webTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class SubscriptionPage {
	
	    private WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	    	
	    	System.setProperty("webdriver.gecko.driver", "C:\\browserdrivers\\geckodriver.exe");
	        driver = new FirefoxDriver();
	        driver.manage().window().maximize();
	        driver.get("https://subscribe.stctv.com/sa-en");
	        
	    }

	    @DataProvider(name = "countries")
	    public Object[][] createData() {
	        return new Object[][]{
	                {"SA", "SAR"},
	                {"Kuwait", "KWD"},
	                {"Bahrain", "BHD"}
	        };
	    }

	    @Test(dataProvider = "countries")
	    public void validateSubscriptionPackages(String country, String currency) {

	        List<WebElement> packages = driver.findElements(By.cssSelector(".package-container"));
	        for (WebElement pack : packages) {
	            String type = pack.findElement(By.cssSelector(".package-type")).getText();
	            String price = pack.findElement(By.cssSelector(".package-price")).getText();
	            String displayedCurrency = price.replaceAll("[0-9]", "").trim();

	            Assert.assertTrue(!type.isEmpty(), "The package type is empty");
	            Assert.assertEquals(displayedCurrency, currency, "The currency mismatch for country: " + country);
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}

