package com.quickauto.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.quickauto.utilities.UtilFW;


public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static  UtilFW utilObj=new UtilFW();
	public static Logger log=Logger.getLogger(TestBase.class);

	@BeforeSuite
	public void setUp() throws IOException {
		
		initializePropFile();
		String browserName=config.getProperty("browser");
		
		System.out.println("browser Name"+ browserName);
		log.info("browser Name : "+ browserName);
		browserInitialize(browserName);

	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
	}

	public void initializePropFile() throws IOException {
		String workSpacePAth = System.getProperty("user.dir");
//		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		PropertyConfigurator.configure(workSpacePAth+"\\src\\test\\resources\\properties\\log4j.properties");
		if (driver == null) {
			fis = new FileInputStream(workSpacePAth + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);

			fis = new FileInputStream(workSpacePAth + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
		}

	}

	public void browserInitialize(String browserName) {
		if (driver == null) {
			switch (browserName.toLowerCase()) {
			case "chrome":
				driver=new ChromeDriver();
				break;
			case "firefox":
				driver=new FirefoxDriver();
				break;
			case "edge":
				driver=new EdgeDriver();
				break;
			default:
				System.out.println("browser name: "+browserName+" not found.");

			}
		}

	}

}
