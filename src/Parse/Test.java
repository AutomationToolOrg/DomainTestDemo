package Parse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import CreateTestcase.ParseHTMLToXML;

public class Test {

	public static void main(String[] args) {
		//ParseHTMLToXML.Parse("seleniumIDE.html");
		Map<String, List<Map<String, String>>>  caseMap=ParseXML.parseXML("Case/DomainTest.xml");
		ActionMapping.Mapping(caseMap);

	}

}
