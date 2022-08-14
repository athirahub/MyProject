package com.learningmaven.Automation;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaleElementExample {
	WebDriver driver;
	Actions action;
	Random random = new Random();
	int randomint;
	String parenthandle = "";

	@BeforeMethod
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\\\Driver\\\\Chrome Driver\\\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.get("https://demoqa.com/");
		parenthandle = driver.getWindowHandle();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

	}

	@Test
	public void staleExceptionTest() {
		driver.findElement(By.cssSelector("div.category-cards div.card:nth-of-type(3) svg")).click();
		driver.findElement(By.cssSelector("div.accordion div.element-group:nth-of-type(3) #item-0")).click();
		WebElement header = driver.findElement(By.cssSelector("div.container.playgound-body >div:first-of-type div"));
		driver.findElement(By.cssSelector("#windowButton")).click();
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parenthandle)) {
				driver.switchTo().window(handle);
			}
		}

		driver.switchTo().defaultContent();
		System.out.println(header.getText());

	}

	public void closeBrowser() {
		driver.quit();
	}
}
