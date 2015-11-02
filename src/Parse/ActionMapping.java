package Parse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import WebdriverEncapsulation.ConfigBuilder;
import log.HtmlLogger;

public class ActionMapping {

	public static void Mapping(Map<String, List<Map<String, String>>> caseList) {
		Map<String, String> actionParam = new LinkedHashMap<String, String>();
		// int caseItemIndex=0;
		for (Map.Entry<String, List<Map<String, String>>> caseItem : caseList
				.entrySet()) {
			// caseItemIndex++;
			String caseName = caseItem.getKey().trim();
			// HtmlLogger.Message("============================================");
			// HtmlLogger.Message(caseName+ " is staring to run");
			List<Map<String, String>> actionList = caseItem.getValue();
			for (int i = 0, redo = 0; i < actionList.size(); i++) {
				Map<String, String> action = actionList.get(i);
				for (Map.Entry<String, String> actionItem : action.entrySet()) {
					if (actionItem.getKey() != null) {
						actionParam.put(actionItem.getKey().trim(), actionItem
								.getValue().trim());
					}
				}
				actionParam.put("Case", caseName);
				try {
					ActionInterpreter.InterpreterAction(actionParam);
					redo = 0;
				} catch (Exception e) {
					if (redo < 3) {
						i -= 2;
					}
				}
				actionParam.clear();
			}
			System.out.println("case " + caseName);
			ConfigBuilder.Driver.manage().deleteAllCookies();
			ConfigBuilder.Driver.quit();
			ConfigBuilder.Driver = ConfigBuilder.ChooseDriver();

		}
	}
}
