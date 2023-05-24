package com.rahul.spring.ram;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import browserSetup.driverSetup;

import org.apache.commons.io.FileUtils;
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Project {
	public static WebDriver driver;
	public static String currentHandle;
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public  void startTest()
	{
	report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
	test = report.startTest("ExtentDemo");
	}
//Calling driverConfif method from driverSetup class to RunBrowser.	
	
	static void driverConfig() throws IOException, InterruptedException {
		System.out.println("Enter Your Browser Choice:\n 1. Chrome\n 2. Edge");
		Scanner sc = new Scanner(System.in);
		int choice = Integer.parseInt(sc.nextLine());
		if (choice == 1)
		{
			driver = driverSetup.driverInstantiate("Chrome");
		} 
		else if (choice == 2)
		{
			driver = driverSetup.driverInstantiate("Edge");
		} 
		else 
		{
			System.out.println("Wrong Input");
			System.exit(0);
		}
		sc.close();
		//driver = driverSetup.driverInstantiate("chrome");
		currentHandle = driver.getWindowHandle();

	}
	@Test
	public static void run() throws InterruptedException, IOException {
		// System.out.println( "Hello World!" );
		// System.setProperty("webdriver.chrome.driver",
		// "C:\Users\2266801\Downloads\chromedriver_win32\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();
		// driver.get("https://www.bookswagon.com/");
		// driver.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
		// Thread.sleep(1000);

		driverConfig();
		elementCall();
		ddlsort();
		System.out.println("The Automation Test Completed.");
		driver.close();

	}

	//calling for WebElement.
	
	public static void elementCall() throws IOException, InterruptedException {
		WebElement inputbar = driver.findElement(By.id("inputbar"));
		inputbar.sendKeys("Selenium Webdriver");
		WebElement searchButton = driver.findElement(By.id("btnTopSearch"));
		searchButton.click();
//         Thread.sleep(2000);
		WebElement count = driver.findElement(By.className("preferences-show"));
//         System.out.println(count.getText());
		int num = Integer.parseInt((count).getText().substring(0, 2));
		if (num > 10) {
			System.out.println(count.getText());
			System.out.println("The result is greater than 10");
			System.out.println();
		} else {
			System.out.println("The result is less than 10");
			System.out.println();
		}
		Thread.sleep(1000);
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(f,
				new File("C:/Users/2266801/eclipse-workspace/ram/src/main/java/screenShot/screenshot-Search_Bar01.png"));
		
		
	}
//Using select functionality from drop down.
	
	public static void ddlsort() throws InterruptedException, IOException {
		Select ddlsort = new Select(driver.findElement(By.id("ddlSort")));
		ddlsort.selectByValue("Product_ActualPrice asc");
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(1000);
		List<WebElement> list = driver.findElements(By.className("list-view-books"));
		for (int i = 0; i < 5; i++) {
			WebElement listElement = list.get(i);
			System.out.println((i + 1) + " : " + "Product Name : "
					+ listElement.findElement(By.className("title")).findElement(By.tagName("a")).getText());

			System.out.println("Price : "
					+ listElement.findElement(By.className("price")).findElement(By.className("sell")).getText());

			System.out.println();
			Thread.sleep(1000);
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(f,
					new File("C:/Users/2266801/eclipse-workspace/ram/src/main/java/screenShot/screenshot-DropDown_Sort01.png"));

		}
		
	}
	@AfterClass
	public  void endTest() {
		report.endTest(test);
		report.flush();
	}

}
