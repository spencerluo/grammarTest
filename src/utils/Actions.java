package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Actions {

	private static WebDriver driver;
	
	public static WebDriver getDriver(){
		return driver;
	}
	
	public static void openBrowser(String browser) throws Exception{
		Locator.init();
		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "IE":
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			break;

		default:
			Log.error("no such Browser");
			throw new Exception("not such browser");
		}
		Log.info("open "+browser+" browser");
	}
	
	public static void getUrl(String url) throws Exception{
		try {
			driver.get(url);
			Log.info("navigate to ["+url+"]");
		} catch (Exception e) {
			Log.error("can't nvigate to ["+url+"]");
			throw new Exception("can't nvigate to ["+url+"]");
		}
	}
	
	public static WebElement getElement(String pageName, String objectName) throws Exception{
		WebElement element =null;
		try {
			element = driver.findElement(Locator.getLocator(pageName, objectName));
		} catch (Exception e) {
			Log.error("can't locate ["+objectName+"] in ["+pageName+"]");
			throw new Exception("can't locate ["+objectName+"] in ["+pageName+"]");
		}
		return element;
	}
	
	public static void click(String pageName, String objectName) throws Exception{
		try {
			getElement(pageName, objectName).click();
			Log.info("click ["+objectName+"] in ["+pageName+"]");
		} catch (Exception e) {
			Log.error("can't click ["+objectName+"] in ["+pageName+"]");
			throw new Exception("can't click ["+objectName+"] in ["+pageName+"]");
		}
	}
	
	public static void waitAndClick(String pageName, String objectName) throws Exception{
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(Locator.getLocator(pageName, objectName)));
		click(pageName, objectName);
	}
	
	public static void clear(String pageName, String objectName) throws Exception{
		try {
			getElement(pageName, objectName).clear();
			Log.info("clear ["+objectName+"] in ["+pageName+"]");
		} catch (Exception e) {
			Log.error("can't clear ["+objectName+"] in ["+pageName+"]");
			throw new Exception("can't clear ["+objectName+"] in ["+pageName+"]");
		}
	}
	
	public static void sendKeys(String pageName, String objectName, String data) throws Exception{
		try {
			getElement(pageName, objectName).sendKeys(data);;
			Log.info("send ["+data+"] to ["+objectName+"] in ["+pageName+"]");
		} catch (Exception e) {
			Log.error("can't send ["+data+"] to ["+objectName+"] in ["+pageName+"]");
			throw new Exception("can't send ["+data+"] to ["+objectName+"] in ["+pageName+"]");
		}
	}
	
	public static void quite(){
		driver.quit();
		Log.info("close the browser");
	}
	
	public static void switchToFrame(String pageName, String objectName) throws Exception{
		try {
			WebElement frame = getElement(pageName, objectName);
			driver.switchTo().frame(frame);
			Log.info("switch to the frame ["+objectName+"] in ["+pageName+"]");
		} catch (Exception e) {
			Log.error("can't switch to the frame ["+objectName+"] in ["+pageName+"]");
			throw new Exception("can't switch to the frame ["+objectName+"] in ["+pageName+"]");
		}
	}
	
	public static void switchToDefaultFrame(){
		try {
			driver.switchTo().defaultContent();
			Log.info("switch to default frame");
		} catch (Exception e) {
			Log.error("can't switch to default frame");
		}
	}
	
	public static String getPageSource(){
		return driver.getPageSource();
	}
	
	public static String getTitle(){
		return driver.getTitle();
	}
	
	public static void takeScreenShot(){
		TakesScreenshot tss=(TakesScreenshot)driver;
		File file = tss.getScreenshotAs(OutputType.FILE);
		String date = getDate();
		try {
			FileUtils.copyFile(file, new File("photos\\"+date+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getDate(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String s=sdf.format(date);
		return s;
	}
}
