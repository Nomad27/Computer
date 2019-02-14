package com.backbase.computerdb.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	//Intialize the Webdriver and Properties
	public static WebDriver driver;
	public static Properties prop;
	public static void intialize() {
		
		try {
			//System get the user directory
			String path = System.getProperty("user.dir");
			//Read the property file 
			FileInputStream input = new FileInputStream(path+"\\src\\main\\java\\com\\backbase\\computerdb\\config\\config.properties");
			prop = new Properties();
			//load the properties file
			prop.load(input);
			//get the browser and url name
			String browserName = prop.getProperty("browser");
			String url = prop.getProperty("url");
			if(browserName.equals("chrome")) {
				//System property set up
				System.setProperty("wedriver.chrome.driver", ".\\resources\\chromedriver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
