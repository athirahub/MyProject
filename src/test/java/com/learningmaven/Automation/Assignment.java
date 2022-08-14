package com.learningmaven.Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment {

	Random random = new Random();
	int randomint;
	int randomnumber;
	String randomname = "";
	String password = "";
	String email = "";


	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.edge.driver", "C:\\Driver\\Edge Driver\\msedgedriver.exe");
		driver = new EdgeDriver();
		
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(14000, TimeUnit.MILLISECONDS);
	}

	@Test(priority = 1)
	public void testingRegisterDone() {
		driver.findElement(By.cssSelector("#top-links ul:first-of-type li.dropdown span:first-of-type")).click();
		driver.findElement(By.cssSelector("#top-links ul:first-of-type ul >li:first-of-type a")).click();
		String name = "Abcdefghjklmnopqrstuvwxyz";
		randomnumber = random.nextInt(99);
		for (int i = 0; i < 7; i++) {
			randomint = random.nextInt(7);
			;
			randomname += name.charAt(randomint);
		}

		email = randomname + randomnumber + "@gmail.com";
		password = randomname + randomnumber + "@";

		driver.findElement(By.cssSelector("#input-firstname")).sendKeys(randomname.substring(0, 4));

		driver.findElement(By.cssSelector("#input-lastname")).sendKeys(randomname.substring(4, 7));

		driver.findElement(By.cssSelector("#input-email")).sendKeys(email);

		driver.findElement(By.cssSelector("#input-telephone")).sendKeys("647568895" + randomint);

		driver.findElement(By.cssSelector("#input-password")).sendKeys(password);

		driver.findElement(By.cssSelector("#input-confirm")).sendKeys(password);

		driver.findElement(By.cssSelector("div.pull-right >input:first-of-type")).click();

		driver.findElement(By.cssSelector("div.pull-right:nth-of-type(1) input:last-of-type")).submit();
		
		WebElement message = driver.findElement(By.cssSelector("#content p:first-of-type"));
		String text = message.getText();
		Assert.assertEquals(text, "Congratulations! Your new account has been successfully created!", "Texts  match");

	}

	@Test(dependsOnMethods = "testingRegisterDone")
	public void purchaseTest() throws AWTException  {

		driver.findElement(By.cssSelector("#top-links ul:first-of-type li.dropdown span:first-of-type")).click();
		driver.findElement(By.cssSelector("#top-links ul:last-of-type ul >li:last-of-type a")).click();
		driver.findElement(By.cssSelector("#input-email")).sendKeys(email);
		driver.findElement(By.cssSelector("#input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("#content form input.btn")).submit();
		String text = driver.findElement(By.cssSelector("#content h2:first-of-type")).getText();
		Assert.assertEquals(text, "My Account", "Not Logged in");
		driver.findElement(By.cssSelector("div.collapse.navbar-collapse >ul:first-of-type >li:nth-of-type(3) >a"))
				.click();
		driver.findElement(By.cssSelector("ul.nav.navbar-nav li.dropdown:nth-of-type(3) li:nth-of-type(2) a")).click();
		driver.findElement(By.cssSelector("div.row div.product-layout:first-of-type  div.product-thumb button span"))
				.click();

		driver.findElement(By.cssSelector("#input-option218 div.radio:last-of-type input")).click();
		driver.findElement(By.cssSelector("#input-option223 div.checkbox:first-of-type input")).click();
		generatingSelect(driver.findElement(By.cssSelector("#input-option217"))).selectByValue("3");
		
		driver.findElement(By.cssSelector("#input-option209")).sendKeys(randomname);
		WebElement upload = driver.findElement(By.cssSelector("#button-upload222"));
		upload.click();
		
		Robot robo = new Robot();
		robo.delay(1000);

		StringSelection str = new StringSelection("\"C:\\Users\\my pc\\Pictures\\puppy.jpg\"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);

		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);

		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent()).accept();;


		WebElement date = driver.findElement(By.cssSelector("div.input-group.date input"));
		date.clear();
		String dates = "2006-10-03";
		date.sendKeys(dates);
		WebElement time = driver.findElement(By.cssSelector("#input-option221"));
		time.clear();
		String times = "10.22";
		time.sendKeys(times);
		WebElement datetime = driver.findElement(By.cssSelector("#input-option220"));
		datetime.clear();

		datetime.sendKeys(dates + " " + times);

		
		driver.findElement(By.cssSelector("#button-cart:last-of-type")).click();
		driver.findElement(By.cssSelector("#cart-total")).click();

		driver.findElement(By.cssSelector("ul.dropdown-menu li:last-of-type p a:last-of-type strong")).click();

		driver.findElement(By.cssSelector("#input-payment-firstname")).sendKeys(randomname.substring(0, 4));
		driver.findElement(By.cssSelector("#input-payment-lastname")).sendKeys(randomname.substring(4, 7));
		driver.findElement(By.cssSelector("#input-payment-company")).sendKeys("NILL");
		driver.findElement(By.cssSelector("#input-payment-address-1"))
				.sendKeys(randomint + "victor cres,london,on,n9c1z9");
		driver.findElement(By.cssSelector("#input-payment-address-2"))
				.sendKeys(randomint + "victor cres,london,on,n9c1z9");
		driver.findElement(By.cssSelector("#input-payment-city")).sendKeys("london");
		driver.findElement(By.cssSelector("#input-payment-postcode")).sendKeys("n9c1z9");
		driver.findElement(By.cssSelector("#input-payment-postcode")).sendKeys("n9c1z9");
		generatingSelect(driver.findElement(By.cssSelector("#input-payment-country"))).selectByValue("38");
		
		generatingSelect(driver.findElement(By.cssSelector("#input-payment-zone"))).selectByValue("610");
		

		WebElement billingContinueBtn=driver.findElement(By.cssSelector("#button-payment-address"));
		billingContinueBtn.click();
		WebElement address=wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#button-shipping-address:last-of-type")));
		
		address.click();
		
		
		driver.findElement(By.cssSelector("#button-shipping-method")).click();
		driver.findElement(By.cssSelector(" div.panel-body >div.buttons div.pull-right a+input")).click();
		driver.findElement(By.cssSelector("#button-payment-method:last-of-type")).click();
		driver.findElement(By.cssSelector("#button-confirm:first-of-type")).click();
	
		String message = driver.findElement(By.cssSelector("#content >p:first-of-type")).getText();
		Assert.assertEquals(message, "Your order has been successfully processed!", "Order not placed");
		driver.findElement(By.cssSelector("#top-links ul:first-of-type li.dropdown span:first-of-type")).click();
		driver.findElement(By.cssSelector("ul.dropdown-menu li:nth-of-type(5) a")).click();
	
		driver.navigate().refresh();
		message = driver.findElement(By.cssSelector("#content p:first-of-type")).getText();
		Assert.assertEquals(message, "You have been logged off your account. It is now safe to leave the computer.",
				"Not Loged out");

	}
	public Select generatingSelect(WebElement element) {
		Select sc=new Select(element);
		return sc;
	}
	

	@AfterMethod
	public void teardown() {
		driver.close();

	}

}
