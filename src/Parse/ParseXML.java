package Parse;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.text.ChangedCharSetException;
import javax.swing.text.Document;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import log.HtmlLogger;
import log.Logmessage;


public class ParseXML {	

      
	public static Map<String, List<Map<String, String>>> parseXML(String path){
		Map<String, List<Map<String, String>>> _case_Action_Map = new LinkedHashMap<String, List<Map<String, String>>>();
		 SAXReader reader = new SAXReader();
		 org.dom4j.Document doc;
		 try {
			
			doc = reader.read(new File(path));
			Element root = doc.getRootElement();				
			List<Element> caseList = root.elements("Case");			 
			for(Element _case :caseList){				 
				 String caseName = _case.attributeValue("name");
				 List<Map<String, String>>  _ActionList = new LinkedList<Map<String, String>>();
				 for(Iterator i = _case.elementIterator(); i.hasNext();){			 
					 Map<String, String> map = new LinkedHashMap<String, String>();
					 Element element = (Element)i.next();
					 String actionName = element.getName();
					 String description = element.attributeValue("description");
					 map.put("Action", actionName);
					 if(description!=null){
						 map.put("Description", description);
					 }
					 for(Iterator j = element.elementIterator(); j.hasNext(); ){						 						 
						 Element actionParam = (Element)j.next();
						 String key = actionParam.getName();						 
						 String value = actionParam.getTextTrim();					 
						 map.put(key, value);						 						 
					 }					
					
					 _ActionList.add(map);	 
				}
				_case_Action_Map.put(caseName, _ActionList);
			}
		} catch (DocumentException e) {
			
			HtmlLogger.Message("DocumentException occurs during parse the case xml file " + path,true);
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
			Logmessage.log();
		}	 
     	
		 return _case_Action_Map;

  }
}
