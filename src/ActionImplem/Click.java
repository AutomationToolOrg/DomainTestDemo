package ActionImplem;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;




public class Click extends Action {

	@Override
	public void Do() {
		WebDriver driver=ConfigBuilder.Driver;
//		String ideifier=ActionParam.get("identifyby").trim();
//		String value=ActionParam.get("identifyvalue");
		
		WebElement element=null;
		switch(ideifier)
		{
		
			case "id":
				element=FindElement.GetElementById(driver, value);
				//To make element will be clicked get the focus
				Actions actions=new Actions(driver);
				
				Robot robot=null;
				
			try {
				robot = new Robot();
				actions.contextClick(element).perform();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				element.click();	
				
				
			} catch (AWTException e) {
				
				e.printStackTrace();
			}
			
			if(ActionParam.get("wait")!=null){
				String wait=ActionParam.get("wait");
				WebDriverWait waitTime = new WebDriverWait(driver, 5);
				element = waitTime.until(ExpectedConditions.elementToBeClickable(By
						.id(wait)));}
		
				break;
			case "class":
				element=FindElement.GetElementByClassName(driver, value);
				element.click();
				break;
				
			case "xpath":
				element=FindElement.GetElementByXpath(driver,value);
				element.click();
				break;
			case "css":
				
				break;
		}
		
		
	}

}
