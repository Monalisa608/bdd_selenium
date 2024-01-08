package com.quickauto.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.quickauto.base.TestBase;

public class LogInTest extends TestBase {
	@Test
	public void LogIn() throws InterruptedException
	{
		System.out.println("run started");
		utilObj.logIn();
		By by=utilObj.getByVal("homeTitle");
		utilObj.isElementPresent(by,2);
		Thread.sleep(5);
	
	}

}
