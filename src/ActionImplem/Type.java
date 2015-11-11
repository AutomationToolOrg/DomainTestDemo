package ActionImplem;

import java.util.Map;

import org.dom4j.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;
import log.HtmlLogger;
import log.Logmessage;

public class Type extends Action {

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
		value = ActionParam.get(ideifier).trim();
		String content = ActionParam.get("value");
		try {
			switch (ideifier) {

			case "id":
				element = FindElement.GetElementById(driver, value);

				element.sendKeys(content);
				break;
			case "class":
				element = FindElement.GetElementByClassName(driver, value);
				element.sendKeys(content);
				break;
			case "xpath":
				element = FindElement.GetElementByXpath(driver, value);
				element.sendKeys(content);
				break;
			case "css":
				element = FindElement.GetElementByCSS(driver, value);
				element.sendKeys(content);
				break;
			}

		} catch (Exception e) {

			HtmlLogger.Message("Cannot send key on Element " + value,true);
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
		}
	}

}
