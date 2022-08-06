package com.learningmaven.Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest {
	public class SignUp {
		WebDriver driver;

		@BeforeMethod
		public void openBrowser() {
			System.setProperty("webdriver.chrome.driver", "C:\\Driver\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
			driver.manage().window().maximize();
		}

		@Test
		public void test1() {

			WebElement firstNameField = driver.findElement(By.cssSelector("#input-firstname"));
			firstNameField.sendKeys("samuel");
			WebElement lastNameField = driver.findElement(By.cssSelector("#input-lastname"));
			lastNameField.sendKeys("richard");
			WebElement emailField = driver.findElement(By.cssSelector("#input-email"));
			emailField.sendKeys("samrich23@gmail.com");
			WebElement telephoneField = driver.findElement(By.cssSelector("#input-telephone"));
			telephoneField.sendKeys("2262178906");
			WebElement passwordField = driver.findElement(By.cssSelector("#input-password"));
			passwordField.sendKeys("samuel345@");
			WebElement confirmPasswordField = driver.findElement(By.cssSelector("#input-confirm"));
			confirmPasswordField.sendKeys("samuel345@");
			WebElement subscribeCheckBox = driver.findElement(By.cssSelector("label.radio-inline:last-of-type input"));
			boolean isSubscribeChecked = subscribeCheckBox.isSelected();
			if (isSubscribeChecked) {
				WebElement submitButton = driver.findElement(By.cssSelector("div.pull-right:nth-of-type(1) input:last-of-type"));
				submitButton.click();
			} else {
				subscribeCheckBox.click();
				WebElement submitButton = driver.findElement(By.cssSelector("div.pull-right:nth-of-type(1) input:last-of-type"));
				submitButton.click();

			}
			WebElement errormessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));
			String text = errormessage.getText();
			Assert.assertEquals(text, "Warning: You must agree to the Privacy Policy!", "Texts  match");

		}

		@AfterMethod
		public void teardown() {
			driver.close();

		}
	}

}
