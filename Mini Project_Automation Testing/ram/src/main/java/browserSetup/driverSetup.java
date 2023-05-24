package browserSetup;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
////import org.testng.annotations.Test;
//import org.apache.commons.io.*;

//import com.relevantcodes.extentreports.ExtentReports;
//
//import com.relevantcodes.extentreports.ExtentTest;

public class driverSetup {
	public static WebDriver driver;
	

	public static String url = "https://www.bookswagon.com/";
	public static String browsertype;

	@BeforeClass
	public static WebDriver driverInstantiate(String browser) throws InterruptedException, IOException {
	

		browsertype = browser;
		if (browsertype.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\2266801\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\2266801\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Thread.sleep(2000);
		driver.get(url);

		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(f,
				new File("C:/Users/2266801/eclipse-workspace/ram/src/main/java/screenShot/screenshot-Home_Page01.png"));

		return driver;

	}

	

//	public static void driverClose() {
//		// TODO Auto-generated method stub
//		driverSetup.driver.close();
//
//	}

}
