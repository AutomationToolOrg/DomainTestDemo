package Parse;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.dom4j.Element;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import WebdriverEncapsulation.ConfigBuilder;
import log.HtmlLogger;
import log.Logmessage;

public class ActionMapping {

	public static void Mapping(Map<String, List<Map<String, String>>> caseList) {
		Map<String, String> actionParam = new LinkedHashMap<String, String>();
		
		for (Map.Entry<String, List<Map<String, String>>> caseItem : caseList.entrySet()) {
			
			String caseName = caseItem.getKey().trim();
			HtmlLogger.casename=caseName;
			HtmlLogger.isBreak=false;
			List<Map<String, String>> actionList = caseItem.getValue();

			for (int i = 0; i < actionList.size(); i++) {
				if (!HtmlLogger.isBreak) {
					Map<String, String> action = actionList.get(i);
					for (Map.Entry<String, String> actionItem : action.entrySet()) {
						if (actionItem.getKey() != null) {
							String value = actionItem.getValue().trim();
							value = handleXMLChar(value);
							actionParam.put(actionItem.getKey().trim(), value);
						}
					}
					actionParam.put("Case", caseName);
					ActionInterpreter.InterpreterAction(actionParam);
					actionParam.clear();
				}else{
					//there has exception on InterpreterAction funtion
					break;
				}
			}
			//The action list of an action has been run finished
			Logmessage.log(caseName);
			ConfigBuilder.Driver.manage().deleteAllCookies();
			ConfigBuilder.Driver.quit();
			ConfigBuilder.Driver = ConfigBuilder.ChooseDriver();

		}
	}

	private static String handleXMLChar(String value) {
		value = value.replaceAll("&amp;", "&");
		value = value.replaceAll("&quot;", "\"");
		value = value.replaceAll("&nbsp;", " ");
		value = value.replaceAll("&lt;", "<");
		value = value.replaceAll("&gt;", ">");
		value = value.replaceAll("<br>;", "");
		return value;
	}
}
	
