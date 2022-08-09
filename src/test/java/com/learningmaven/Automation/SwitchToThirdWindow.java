package com.learningmaven.Automation;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwitchToThirdWindow {
	WebDriver driver;
	
	String parentHandle;
	String firstChildHandle;
	String secondChildHandle;
	

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\\\Driver\\\\Chrome Driver\\\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("https://demoqa.com/");
		parentHandle = driver.getWindowHandle();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

	}

	@Test
	public void isNewTabOpening() {
		WebElement alertsFramesButton = driver
				.findElement(By.cssSelector("div.category-cards div.card:nth-of-type(3)  svg"));
		alertsFramesButton.click();
		WebElement browserWindowsButton = driver.findElement(
				By.cssSelector("div.left-pannel div.accordion div.element-group:nth-of-type(3) #item-0 span"));
		browserWindowsButton.click();
		WebElement tabButton = driver.findElement(By.id("tabButton"));
		tabButton.click();
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				firstChildHandle = handle;
				driver.switchTo().window(firstChildHandle);
			}

		}

		driver.switchTo().window(parentHandle);
		tabButton.click();
		handles = driver.getWindowHandles();
		for (String handlenew : handles) {
			if (!handlenew.equals(parentHandle) && !handlenew.equals(firstChildHandle)) {
				secondChildHandle = handlenew;
				driver.switchTo().window(secondChildHandle);
			}
		}
		System.out.println("Window handles are:"+handles);
		
		WebElement header = driver.findElement(By.id("sampleHeading"));
		System.out.println("The third window handle :" + secondChildHandle);
		Assert.assertEquals(header.getText(), "This is a sample page", "Not Right Page");
}
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}
