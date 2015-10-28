package ActionImplem;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;

public class VerifyTextPresent extends Action  {

	@Override
	public void Do() {
		WebDriver driver = ConfigBuilder.Driver;
		String ideifier = "";
		String value = "";
		WebElement element = null;
		String text=ActionParam.get("value");
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
		if (element != null&&element.getText().equals(text)) {
			System.out.println("text" +text +"appears on "+value);
		}
		
	}

}
