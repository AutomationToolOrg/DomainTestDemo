package Parse;

import java.util.Map;
import ActionImplem.Action;
import log.HtmlLogger;
import log.Logmessage;

public class ActionInterpreter {
	public static void InterpreterAction(Map<String, String> actionParam) {

		String actionName = actionParam.get("Action").trim();
	
		Class<?> actionClass;
		try {
			// Create action and execute
			String actionPackageString = "ActionImplem";
			actionClass = Class.forName(actionPackageString + "." + actionName);
			Action action = (Action) actionClass.newInstance();
			action.ActionParam = actionParam;
					action.Do();		
		} catch (ClassNotFoundException e) {
			
			HtmlLogger.Message("Class " + actionName + " can not be found.",true);			
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
		} catch (InstantiationException | IllegalAccessException e) {
			HtmlLogger.Message("Class " + actionName + " instantiate fail or access permission deny.",true);			
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
		}

	}
}
