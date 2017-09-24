package st;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SEOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\riddhi\\Downloads\\geckodriver-v0.19.0-win64\\geckodriver.exe");

		String[] searchkeywords =  {
				"liferay consulting",
				"Liferay offshore",
				"Android offshore development company",
				};
		
		WebDriver driver = new FirefoxDriver();

		for (String keyword : searchkeywords) {
		    driver.get("http://www.google.com");
		    driver.findElement(By.linkText("Use Google.com")).click();
			searchKeyword(keyword, driver);
		}

	}
	
	
	public static void searchKeyword(String searchKeyword, WebDriver driver) {
		
		System.out.println(searchKeyword);
		FirefoxProfile firefoxProfile = new FirefoxProfile();    
		firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
			    
	    WebElement element = driver.findElement(By.name("q"));
	    element.sendKeys(searchKeyword+"\n"); // send also a "\n"
	    element.submit();

	    int count = 0;   
	    while(count != 10) {
	    	
		    // wait until the google page shows the result
		    WebElement myDynamicElement = (new WebDriverWait(driver, 100))
		              .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

		    List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

		    // this are all the links you like to visit
		    for (WebElement webElement : findElements)
		    {	        
		        if (webElement.getAttribute("href").contains("surekhatech.com")) {
		        	System.out.println();
		        	System.out.println(webElement.getAttribute("href"));
		        	System.out.println(webElement.getAttribute("onmousedown"));
		        	String result = webElement.getAttribute("onmousedown");
		        	parseResult(result);
		        	return;
		        }
		    }
		    
		    driver.findElement(By.id("pnnext")).click();
		    count++;
	    }
	    	System.out.println("Not found  for keyword"+ searchKeyword);
	}

	
	public static void parseResult(String result) {
			
		String regex = "('\\d+')";		
		Pattern pattern  = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(result);
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
	}
	
}
