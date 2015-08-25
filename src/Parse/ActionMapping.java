package Parse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class ActionMapping {

	
	public static void  Mapping(Map<String, List<Map<String,String>>> caseList){
		Map<String,String> actionParam=new LinkedHashMap<String,String>();
		for(Map.Entry<String, List<Map<String,String>>> caseItem:caseList.entrySet()){
			String caseName=caseItem.getKey().trim();
			List<Map<String,String>> actionList=caseItem.getValue();
			for(int i=0;i<actionList.size();i++){
				Map<String,String> action=actionList.get(i);
				for(Map.Entry<String,String> actionItem :action.entrySet()){
					actionParam.put(actionItem.getKey().trim(), actionItem.getValue().trim());			
				}		
				ActionInterpreter.InterpreterAction(actionParam);
			}
	}
}
}
