package ExtentReport;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReport {
	
	private static WebDriver driver=null;
	
	public static void main(String[] args) {
		
		String path=System.getProperty("user.dir") +"\\extentReport.html";
		ExtentSparkReporter htmlReport= new ExtentSparkReporter(path);
		htmlReport.config().setReportName("Web Automation Results");
		htmlReport.config().setDocumentTitle("Test Results");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(htmlReport);
		extent.setSystemInfo("Automation Tester", "Dibyansh Verma");
		
		//Create a toggle for the given test, adds all log in report
		ExtentTest test=extent.createTest("Google Search test Case");
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		
		test.log(Status.INFO, "Test Case Starting");
		driver.get("https://www.google.com/");
		test.pass("User navigate to the google search page");
		
		
		driver.findElement(By.name("q")).sendKeys("Automation");
		test.pass("Enter the Automation in text box");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnK"))).sendKeys(Keys.RETURN);
		test.pass("Click on the Enter button through Keyboard");
		
		driver.close();
		driver.quit();
		test.pass("Closed the browser");
		
		extent.flush();		
		test.log(Status.INFO,"Test Completed");
		
	}

}
