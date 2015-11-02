package ActionImplem;

import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import com.sun.jna.platform.mac.Carbon.EventHotKeyID.ByValue;

import Parse.ActionMapping;
import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;

public class Select extends Action {
	String ideifier = "";
	String value = "";
	WebElement webelem = null;

	org.openqa.selenium.support.ui.Select _selectaction = null;

	@Override
	public void Do() throws Exception {

		WebDriver driver = ConfigBuilder.Driver;
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
			webelem = FindElement.GetElementById(driver, value);

			break;
		case "class":
			webelem = FindElement.GetElementByClassName(driver, value);

			break;
		case "xpath":
			webelem = FindElement.GetElementByXpath(driver, value);

			break;
		case "css":
			webelem = FindElement.GetElementByCSS(driver, value);

			break;
		}
		_selectaction = new org.openqa.selenium.support.ui.Select(webelem);

		// Map<String, Object> map=new HashMap<String, Object>();
		// map.put("id", "OrgSize");
		// map.put("value", "2-4 people");
		// for(Entry<String, Object> entry:map.entrySet()){
		// if("value".equals(entry.getKey()))
		// System.out.println(entry.getValue());
		// }

		// ActionParam.get("value").startsWith(=)
		String valString = ActionParam.get("value");
		valString = valString.substring(valString.indexOf("=") + 1);

		_selectaction.selectByVisibleText(valString);

	}

}
