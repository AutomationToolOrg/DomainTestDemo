package ActionImplem;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;
import log.HtmlLogger;
import log.Logmessage;

public class VerifyElementPresent extends Action {
	@Override
	public void Do() {
		WebDriver driver = ConfigBuilder.Driver;
		String ideifier = "";
		String value = "";
		WebElement element = null;

		for (Map.Entry<String, String> actionParam : ActionParam.entrySet()) {
			String key = actionParam.getKey();
			if (key == "id" || key == "css" || key == "xpath") {
				ideifier = key;
				break;
			}
		}
		value = ActionParam.get(ideifier);
		switch (ideifier) {
		case "id":
			element = FindElement.GetElementById(driver, value);
			break;
		case "class":
			element = FindElement.GetElementByClassName(driver, value);
			break;
		case "xpath":
			element = FindElement.GetElementByXpath(driver, value);

			break;
		case "css":
			element = FindElement.GetElementByCSS(driver, value);
			break;
		}
		
		if (element == null) {		
			HtmlLogger.Message(ActionParam.get("Description")+"----fail",true);				
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
		}
	}
	

}
