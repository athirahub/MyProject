package com.learningmaven.Automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment {

	Random random = new Random();
	int randomint;
	String randomname = "";
	String password = "";
	String email = "";
	Select sc;
	Actions ac;
	

	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\\\Driver\\\\Chrome Driver\\\\chromedriver.exe");
		driver = new ChromeDriver();
		ac = new Actions(driver);
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(16000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(14000, TimeUnit.MILLISECONDS);
	}

	@Test(priority = 1)
	public void testingRegisterDone() {
		driver.findElement(By.cssSelector("#top-links ul:first-of-type li.dropdown span:first-of-type")).click();
		driver.findElement(By.cssSelector("#top-links ul:first-of-type ul >li:first-of-type a")).click();
		String name = "Abcdefghjklmnopqrstuvwxyz";

		for (int i = 0; i < 7; i++) {
			randomint = random.nextInt(25 - 10) + 10;
			;
			randomname += name.charAt(randomint);
		}

		email = randomname + randomint + "@gmail.com";
		password = randomname + randomint + "@";

		driver.findElement(By.cssSelector("#input-firstname")).sendKeys(randomname.substring(0, 4));

		driver.findElement(By.cssSelector("#input-lastname")).sendKeys(randomname.substring(4, 7));

		driver.findElement(By.cssSelector("#input-email")).sendKeys(email);

		driver.findElement(By.cssSelector("#input-telephone")).sendKeys("64756889" + randomint);

		driver.findElement(By.cssSelector("#input-password")).sendKeys(password);

		driver.findElement(By.cssSelector("#input-confirm")).sendKeys(password);

		driver.findElement(By.cssSelector("div.pull-right >input:first-of-type")).click();

		driver.findElement(By.cssSelector("div.pull-right:nth-of-type(1) input:last-of-type")).submit();

		WebElement message = driver.findElement(By.cssSelector("#content h1"));
		String text = message.getText();
		Assert.assertEquals(text, "Your Account Has Been Created!", "Texts  match");

	}

	@Test(dependsOnMethods = "testingRegisterDone")
	public void purchasetest() throws InterruptedException, AWTException {

		driver.findElement(By.cssSelector("#top-links ul:first-of-type li.dropdown span:first-of-type")).click();
		driver.findElement(By.cssSelector("#top-links ul:last-of-type ul >li:last-of-type a")).click();
		driver.findElement(By.cssSelector("#input-email")).sendKeys(email);
		driver.findElement(By.cssSelector("#input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("#content form input.btn")).submit();
		String text = driver.findElement(By.cssSelector("#content h2:first-of-type")).getText();
		Assert.assertEquals(text, "My Account", "Text doesnt match");
		driver.findElement(By.cssSelector("div.collapse.navbar-collapse >ul:first-of-type >li:nth-of-type(3) >a"))
				.click();
		driver.findElement(By.cssSelector("ul.nav.navbar-nav li.dropdown:nth-of-type(3) li:nth-of-type(2) a")).click();
		driver.findElement(By.cssSelector("div.row div.product-layout:first-of-type  div.product-thumb button span"))
				.click();

		driver.findElement(By.cssSelector("#input-option218 div.radio:last-of-type input")).click();
		driver.findElement(By.cssSelector("#input-option223 div.checkbox:first-of-type input")).click();
		sc = new Select(driver.findElement(By.cssSelector("#input-option217")));
		sc.selectByValue("3");
		driver.findElement(By.cssSelector("#input-option209")).sendKeys(randomname);
		WebElement upload = driver.findElement(By.cssSelector("#button-upload222"));
		upload.click();
		Thread.sleep(1000);
		Robot robo = new Robot();

		StringSelection str = new StringSelection("\"C:\\Users\\my pc\\Pictures\\puppy.jpg\"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);

		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);

		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);

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

		Thread.sleep(16000);
		driver.switchTo().alert().accept();
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
		sc = new Select(driver.findElement(By.cssSelector("#input-payment-country")));
		sc.selectByValue("38");
		sc = new Select(driver.findElement(By.cssSelector("#input-payment-zone")));
		sc.selectByValue("610");
		
		driver.findElement(By.cssSelector("#button-payment-address")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#button-shipping-address")).click();
		driver.findElement(By.cssSelector("#button-shipping-method")).click();
		driver.findElement(By.cssSelector(" div.panel-body >div.buttons div.pull-right a+input")).click();
		driver.findElement(By.cssSelector("#button-payment-method:last-of-type")).click();
		driver.findElement(By.cssSelector("#button-confirm:first-of-type")).click();
		Thread.sleep(5000);
		String message = driver.findElement(By.cssSelector("#content h1")).getText();
		Assert.assertEquals(message, "Your order has been placed!", "Texts match");
		driver.findElement(By.cssSelector("#top-links ul:first-of-type li.dropdown span:first-of-type")).click();
		driver.findElement(By.cssSelector("ul.dropdown-menu li:nth-of-type(5) a")).click();
		Thread.sleep(1000);
		message = driver.findElement(By.cssSelector("#content p:first-of-type")).getText();
		Assert.assertEquals(message, "You have been logged off your account. It is now safe to leave the computer.",
				"Texts match");

	}

	@AfterMethod
	public void teardown() {
		driver.close();

	}

}
