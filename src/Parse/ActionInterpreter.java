package Parse;

import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import ActionImplem.Action;
import log.logger;

public class ActionInterpreter{
	public static void InterpreterAction(Map<String, String> actionParam){
		
		String actionName=actionParam.get("Action").trim();	
		String ActionDescription = actionParam.get("Description");
		Class actionClass;
		try {
				
			//Create action and execute
			String actionPackageString="ActionImplem";				
			actionClass = Class.forName(actionPackageString+"."+actionName);
			Action action=(Action)actionClass.newInstance();
			action.ActionParam=actionParam;			
			logger.Message(ActionDescription + " is staring");
			action.Do();
			logger.Message(ActionDescription + " is finished");
		} catch (ClassNotFoundException e) {
			logger.Message("Class " +actionName+" not found");
			System.out.println("Class " +actionName+" not found");

		}
		catch (InstantiationException | IllegalAccessException e) {
			System.out.println("InstantiationException or IllegalAccessException occurs");
			
		}
		
	} 
}
