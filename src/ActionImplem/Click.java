package ActionImplem;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;

public class Click extends Action {

	@Override
	public void Do() throws Exception {
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
			// To make element will be clicked get the focus
			// if (ConfigBuilder.Broswer.equals("Firefox")) {
			// Actions actions = new Actions(driver);
			// Robot robot = null;
			// try {
			// robot = new Robot();
			// actions.contextClick(element).perform();
			// robot.keyPress(KeyEvent.VK_ESCAPE);
			// element.click();
			//
			// } catch (AWTException e) {
			//
			// e.printStackTrace();
			// }
			// }else{
			element = FindElement.GetElementById(driver, value);
			element.click();
			// }
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

	}

}
