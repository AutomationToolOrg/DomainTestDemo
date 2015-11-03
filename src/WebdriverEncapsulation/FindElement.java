package WebdriverEncapsulation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import log.logger;

public class FindElement {
	public static final long WaitForAppearSeconds = 100;

	public static WebElement GetElementById(WebDriver Driver, String Id) {
		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(Driver, WaitForAppearSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.id(Id)));

		} catch (Exception e) {
			logger.Message("Element can not be found, Pls check your input Id - "
					+ Id);
			logger.Message(e.getMessage());
			System.out
					.println("Element can not be found, Pls check your input Id - "
							+ Id);
			System.out.println(e.getMessage());
		}

		return element;
	}

	public static WebElement GetElementByClassName(WebDriver Driver,
			String ClassName) {
		WebElement element = null;

		try {

			WebDriverWait wait = new WebDriverWait(Driver, WaitForAppearSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.className(ClassName)));

		} catch (Exception e) {
			
			logger.Message("Element can not be found, Pls check your input Class Name - "
					+ ClassName);
			logger.Message(e.getMessage());
			System.out
					.println("Element can not be found, Pls check your input Class Name - "
							+ ClassName);
			System.out.println(e.getMessage());
		}

		return element;
	}

	public static WebElement GetElementByXpath(WebDriver Driver, String Xpath) {
		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(Driver, WaitForAppearSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(Xpath)));
		} catch (Exception e) {
			
			logger.Message("Element can not be found, Pls check your input Xpath - "
					+ Xpath);
			logger.Message(e.getMessage());
			System.out
					.println("Element can not be found, Pls check your input Xpath - "
							+ Xpath);
			System.out.println(e.getMessage());
		}

		return element;
	}
	public static WebElement GetElementByCSS(WebDriver Driver, String css) {
		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(Driver, WaitForAppearSeconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.cssSelector(css)));
		} catch (Exception e) {
			
			logger.Message("Element can not be found, Pls check your input css selector - "
					+ css);
			logger.Message(e.getMessage());
			System.out
					.println("Element can not be found, Pls check your input css selector - "
							+ css);
			System.out.println(e.getMessage());
		}

		return element;
	}
}
