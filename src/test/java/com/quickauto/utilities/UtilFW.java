package com.quickauto.utilities;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.quickauto.base.TestBase;

public class UtilFW extends TestBase {

	public void launchURL() {
		driver.get(config.getProperty("testURL"));
		driver.manage().window().maximize();
	}

	public void logIn() {
		launchURL();
//		WebElement un = driver.findElement(By.xpath(OR.getProperty("input_LogIn")));
		WebElement un =utilObj.getElement( "input_LogIn");
//		WebElement pw = driver.findElement(By.xpath(OR.getProperty("input_Password")));
		WebElement pw = utilObj.getElement("input_Password");
//		WebElement logInBtn = driver.findElement(By.xpath(OR.getProperty("btn_Submit")));
		WebElement logInBtn = utilObj.getElement("btn_Submit");
		enterData("standard_user", un);
		enterData("secret_sauce", pw);
		clickElement(logInBtn);

	}

	public void enterData(String data, WebElement ele) {
		ele.clear();
		ele.sendKeys(data);
	}

	public void clickElement(WebElement ele) {
		ele.click();
	}

	public boolean isElementPresent(By by, int timeoutSeconds) throws InterruptedException {
		for (int second = 0; second < timeoutSeconds; second++) {
			try {
				driver.findElement(by);
			} catch (NoSuchElementException e) {
				Thread.sleep(1000);
				continue;
			}
			log.debug(by.toString() + " present");
			return true;
		}
		log.debug(by.toString() + " not present");
		return false;
	}

	public WebElement getElement(String propName) {
//		By by = null;
//		String locator = OR.getProperty(propName);
//		int splitIndex = locator.indexOf(',');
//		String locatorStratergy = locator.substring(0, splitIndex);
//		String locatorVal = locator.substring(splitIndex + 1);
//
//		switch (locatorStratergy.toLowerCase()) {
//		case "class":
//			by = By.className(locatorVal);
//			break;
//		case "id":
//			by = By.id(locatorVal);
//			break;
//		case "xpath":
//			by = By.xpath(locatorVal);
//			break;
//		case "css":
//			by = By.cssSelector(locatorVal);
//			break;
//		default:
//			log.info("locator stratergy not found");
//		}

		return driver.findElement(getByVal( propName));
	}
	
	public By getByVal(String propName) {
		By by = null;
		String locator = OR.getProperty(propName);
;		int splitIndex = locator.indexOf(',');
		String locatorStratergy = locator.substring(0, splitIndex);
		String locatorVal = locator.substring(splitIndex + 1);

		switch (locatorStratergy.toLowerCase()) {
		case "class":
			by = By.className(locatorVal);
			break;
		case "id":
			by = By.id(locatorVal);
			break;
		case "xpath":
			by = By.xpath(locatorVal);
			break;
		case "css":
			by = By.cssSelector(locatorVal);
			break;
		default:
			log.info("locator stratergy not found");
		}

		return by;
	}

}
