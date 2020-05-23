package com.ebay;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ebay.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {

	protected static AppiumDriver<MobileElement> driver;
	protected static Properties prop;
	protected static HashMap<String, String> strings = new HashMap<String, String>();
	TestUtils utils;

	InputStream stringsis;
	InputStream inputStream;

	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@Parameters({ "platformName", "platformVersion", "deviceName", "udid" })
	@BeforeTest
	public void beforeTest(String platformName, String platformVersion, String deviceName, String udid)
			throws Exception {

		try {
			prop = new Properties();
			String propFileName = "config.properties";
			String XMLFileName = "strings/strings.xml";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);

			stringsis = getClass().getClassLoader().getResourceAsStream(XMLFileName);
			utils = new TestUtils();
			strings = utils.parseStringXML(stringsis);

			DesiredCapabilities cap = new DesiredCapabilities();
			// set capabilities of phone
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(MobileCapabilityType.UDID, udid);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);

			// cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,
			// prop.getProperty("androidAutomationName"));
			URL appURL = getClass().getClassLoader().getResource(prop.getProperty("androidAppLocation"));
			cap.setCapability(MobileCapabilityType.APP, appURL);
			cap.setCapability("appPackage", prop.getProperty("androidAppPackage"));
			cap.setCapability("appActivity", prop.getProperty("androidAppActivity"));

			URL url = new URL(prop.getProperty("appiumURL"));
			driver = new AppiumDriver<MobileElement>(url, cap);
			System.out.println("Appliction Started....");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (stringsis != null) {
				stringsis.close();
			}
		}

	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clickMobileElement(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void sendKeys(MobileElement e, String text) {
		waitForVisibility(e);
		e.clear();
		e.sendKeys(text);
	}

	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public String getTxt(MobileElement e) {
		waitForVisibility(e);
		return e.getText();
	}

	public void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.height * anchorPercentage);
		int startPoint = (int) (size.width * startPercentage);
		int endPoint = (int) (size.width * endPercentage);

		new TouchAction((PerformsTouchActions) driver).press(point(startPoint, anchor))
				.waitAction(waitOptions(ofMillis(1000))).moveTo(point(endPoint, anchor)).release().perform();
	}

	public void verticalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
		Dimension size = driver.manage().window().getSize();
		System.out.println("Size is" + size + "Width is" + size.width + "Height is" + size.height);
		int anchor = (int) (size.width * anchorPercentage);
		System.out.println("Anchot is" + anchor);
		int startPoint = (int) (size.height * startPercentage);
		System.out.println("Start point is" + startPoint);
		int endPoint = (int) (size.height * endPercentage);
		System.out.println("End point is" + endPoint);

		new TouchAction((PerformsTouchActions) driver).press(point(anchor, startPoint))
				.waitAction(waitOptions(ofMillis(2000))).moveTo(point(anchor, endPoint)).release().perform();
	}

	public boolean isDisplay(final MobileElement e) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					if (e.isDisplayed()) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception exc) {
			return false;
		}
	}

	public void scrollToElementVertically(MobileElement e, double startPercentage, double endPercentage,
			double anchorPercentage) {
		for (int i = 0; i < 3; i++) {
			if (isDisplay(e)) {
				break;
			} else {
				verticalSwipeByPercentage(startPercentage, endPercentage, anchorPercentage);

			}
		}
	}

	public void closeApp() {
		((InteractsWithApps) driver).closeApp();
	}

	public void launchApp() {
		((InteractsWithApps) driver).launchApp();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
