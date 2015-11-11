package ActionImplem;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;
import log.HtmlLogger;
import log.Logmessage;

public class Click extends Action {

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
		try {
			switch (ideifier) {
			case "id":
				element = FindElement.GetElementById(driver, value);
				element.click();
				break;
			case "class":
				element = FindElement.GetElementByClassName(driver, value);
				element.click();
				break;
			case "xpath":
				element = FindElement.GetElementByXpath(driver, value);
				element.click();
				break;
			case "css":
				element = FindElement.GetElementByCSS(driver, value);
				element.click();
				break;
			}
		} catch (Exception e) {
			HtmlLogger.Message("Element " + value + " cannot be click");
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
		}

	}

}
