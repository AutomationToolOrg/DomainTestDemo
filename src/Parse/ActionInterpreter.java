package Parse;

import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import ActionImplem.Action;

public class ActionInterpreter{
	public static void InterpreterAction(Map<String, String> actionParam){
		
		String actionName=actionParam.get("Action").trim();	
		Class actionClass;
		try {
				
			//Create action and execute
			String actionPackageString="ActionImplem";				
			actionClass = Class.forName(actionPackageString+"."+actionName);
			Action action=(Action)actionClass.newInstance();
			action.ActionParam=actionParam;
			action.Do();
		} catch (ClassNotFoundException e) {
			System.out.println("Class " +actionName+" not found");

		}
		catch (InstantiationException | IllegalAccessException e) {
			System.out.println("InstantiationException or IllegalAccessException occurs");

			
		}
		
	} 
}
