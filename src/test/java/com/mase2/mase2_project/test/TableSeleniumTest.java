package com.mase2.mase2_project.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TableSeleniumTest {
	
	String webURL;
	WebDriver driver;
	
	@Before
	public void setUp(){
		webURL="http://localhost:8080/mase2-project/";
		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get(webURL);
		driver.findElement(By.id("inputEmail")).sendKeys("michal");
		driver.findElement(By.id("inputPass")).sendKeys("123");
		driver.findElement(By.id("loginbutton")).click();
		driver.findElement(By.id("nav_Tables")).click();
		
	}
	@Test
	public void mccMncTest() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.id("mccMncTable")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("mccmnc_table")));
	}
	@Test
	public void failureTest() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.id("failureTable")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("failure_table")));
	}
	@Test
	public void ueTest() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.id("ueTable")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("ue_table")));
	}
	@Test
	public void eventTest() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.id("eventTable")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("eventcauses_table")));
	}

}
