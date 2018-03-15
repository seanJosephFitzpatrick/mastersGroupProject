//package com.mase2.mase2_project.test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class testSelenium {
//	String webURL;
//	WebDriver driver;
//	
//	@Before
//	public void setUp(){
//		webURL="http://localhost:8080/mase2-project/";
//		System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized");
//		driver = new ChromeDriver(options);
//
//	}
//	
//	@Test
//	public void addRowTest() throws InterruptedException{
//
//		driver.get(webURL);
//		driver.findElement(By.id("queriestoggle")).click();
//		Thread.sleep(500);
//		driver.findElement(By.id("datequery")).click();
//		Thread.sleep(500);
//		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
//		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
//		driver.findElement(By.id("date_timepicker_end")).click();
//		driver.findElement(By.id("submitdatequery")).click();
//
//		
//	}
////	@Test(expected = NoSuchElementException.class)
////	public void deleteRowTest() throws InterruptedException{
////		
////		driver.findElement(By.name("add")).click();
////		Thread.sleep(500L);
////		driver.findElement(By.id("inputMake")).sendKeys("Apple");
////		driver.findElement(By.id("inputModel")).sendKeys("Apple");
////		driver.findElement(By.id("inputBatteryLife")).sendKeys("Apple");
////		driver.findElement(By.id("inputCameraQuality")).sendKeys("Apple");
////		driver.findElement(By.id("inputColours")).sendKeys("Apple");
////		driver.findElement(By.id("inputDimensions")).sendKeys("Apple");
////		driver.findElement(By.id("inputOS")).sendKeys("Apple");
////		driver.findElement(By.id("inputPrice")).sendKeys("100");
////		driver.findElement(By.id("inputProcessor")).sendKeys("Apple");
////		driver.findElement(By.id("inputRam")).sendKeys("Apple");
////		driver.findElement(By.id("inputScreenSize")).sendKeys("Apple");
////		driver.findElement(By.id("submitbutton")).click();
////		driver.findElement(By.id("rowAppleApple"));
////		Thread.sleep(500L);
////		assertNotNull(driver.findElement(By.id("rowAppleApple")));
////		driver.findElement(By.id("rowAppleApple")).click();
////		JavascriptExecutor js = (JavascriptExecutor) driver;
////		js.executeScript("window.scrollTo(0, 0);");
////		Thread.sleep(500L);
////		driver.findElement(By.id("deleterow")).click();
////		driver.findElement(By.id("rowAppleApple"));
////	}
//
//
//
//}
