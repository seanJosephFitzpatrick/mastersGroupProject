package com.mase2.mase2_project.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class QuerySeleniumTest {
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
		driver.findElement(By.id("inputEmail")).sendKeys("admin@mase2.ie");
		driver.findElement(By.id("inputPass")).sendKeys("123");
		driver.findElement(By.id("loginbutton")).click();
		
		
	}
	
	@Test
	public void dateQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_seQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("dateQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).click();
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("DateDataTable")));

		
	}
	@Test
	public void imsiQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_csrQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("imsiQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("imsi")).sendKeys("191911000599586");
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("ImsiDataTable")));

		
	}
	@Test
	public void modelQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_seQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("countFailuresQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("model")).sendKeys("Alcatel 9100 Type 9109 HC 500");
		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).click();
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertEquals(driver.findElement(By.tagName("h3")).getText(),"Model: Alcatel 9100 Type 9109 HC 500");
		
	}
	@Test
	public void durationQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_nmeQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("sumDurationQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).click();
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("SumAndCountDataTable")));
		
	}
	@Test
	public void topTenQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_nmeQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("topTenQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).click();
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("TopTenDataTable")));
		
	}
	@Test
	public void IMSIFailuresQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_csrQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("showIMSIFailuresQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("imsi")).sendKeys("191911000599586");
		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).click();
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertEquals(driver.findElement(By.tagName("h3")).getText(),"IMSI: 191911000599586");
		
	}
	@Test
	public void uniqueQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_nmeQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("uniqueIdAndCodeQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("model")).sendKeys("Alcatel 9100 Type 9109 HC 500");
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("UniqueEventAndCauseTable")));
		
	}
	
	@Test
	public void topTenIMSIsQueryTest() throws InterruptedException{
		driver.findElement(By.id("nav_nmeQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("topTenIMSIQuery")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).sendKeys("2011-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_start")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).sendKeys("2018-02-01");
		Thread.sleep(1000);
		driver.findElement(By.id("date_timepicker_end")).click();
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("TopTenIMSIDataTable")));
		
	}

	@Test
	public void failuresForGivenIMSI() throws InterruptedException{
		driver.findElement(By.id("nav_seQueries")).click();
		Thread.sleep(500);
		driver.findElement(By.id("IMSIsforGivenFailureClass")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("failure")).sendKeys("1");
		driver.findElement(By.id("submitquery")).click();
		Thread.sleep(500);
		assertNotNull(driver.findElement(By.id("ImsibyFailureClassDataTable")));
		
	}

}
