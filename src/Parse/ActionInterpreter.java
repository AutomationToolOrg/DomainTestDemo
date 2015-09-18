package Parse;

import java.util.Map;
import ActionImplem.Action;
import log.logger;

public class ActionInterpreter {
	public static void InterpreterAction(Map<String, String> actionParam) {

		String actionName = actionParam.get("Action").trim();
		//String ActionDescription = actionParam.get("Description");
		Class<?> actionClass;
		try {
			// Create action and execute
			String actionPackageString = "ActionImplem";
			actionClass = Class.forName(actionPackageString + "." + actionName);
			Action action = (Action) actionClass.newInstance();
			action.ActionParam = actionParam;
			try {
				action.Do();
				logger.StepLog(actionParam, true);
			} catch (Exception e) {
				logger.StepLog(actionParam, false);
			}
		} catch (ClassNotFoundException e) {
			logger.Message("Class " + actionName + " not found. It's code issue!");
		} catch (InstantiationException | IllegalAccessException e) {
			logger.Message("Class " + actionName + " instantiate fail or access permission deny. It's code issue!");
		}

	}
}
