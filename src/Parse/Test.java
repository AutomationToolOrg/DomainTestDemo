package Parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import CreateTestcase.ParseHTMLToXML;
import WebdriverEncapsulation.ConfigBuilder;
import log.HtmlLogger;
import log.Logmessage;

public class Test {
	public static String path="";
	public static void main(String[] args) {
		// get project path
		// String jarFilePath =
		// Test.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		// String path = jarFilePath.substring(0, jarFilePath.lastIndexOf("/"));
		// path = path.substring(0, path.lastIndexOf("/"));
		// path = path.substring(path.indexOf("/")+1, path.lastIndexOf("/"));

		String path = "C:/java work/DomainTestDemo";
		Map<String, Object> configMap = Config(path);

		if (!HtmlLogger.isBreak) {
			// No error on Config function

			// get driver path
			ConfigBuilder.driverPath = (Map<String, String>) configMap.get("DriverPath");

			// get drivers
			List<Element> browsers = (List<Element>) configMap.get("Driver");
			for (Element browser : browsers) {
				// for each browser run all test case
				ConfigBuilder.Browser = browser.getText();
				ConfigBuilder.Driver = ConfigBuilder.ChooseDriver();

				// get caseName that will be run
				List<Element> caseNameList = (List<Element>) configMap.get("Case");

				for (Element caseName : caseNameList) {
					
					String caseFile = caseName.getText().trim();
					HtmlLogger.isBreak=false;
					HtmlLogger.casename=caseFile;
					Map<String, List<Map<String, String>>> caseMap = ParseXML.parseXML(path + "/Case/" + caseFile);
					if(!HtmlLogger.isBreak){				
						ActionMapping.Mapping(caseMap);					
					}				
				}
				HtmlLogger.GenerateReport();
				ConfigBuilder.Driver.quit();				
			}
		}
	}
	public static Map<String, Object> Config(String path) {
		Map<String, Object> configMap = new LinkedHashMap<String, Object>();

		// File file = new File(path + "/RunConfig.xml");

		File file = new File(path + "/Config/RunConfig.xml");

		try {
			SAXReader reader = new SAXReader();
			XMLWriter writer;
			Document doc = reader.read(file);

			// get root element
			Element root = doc.getRootElement();

			// get browsers which should be tested cover
			Element drivers = root.element("Drivers");
			List<Element> browsers = drivers.elements("Driver");
			configMap.put("Driver", browsers);

			// get driver path
			List<Element> driverPath = root.element("DriverPath").elements();
			Map<String, String> driverPathMap = new LinkedHashMap<String, String>();
			for (int i = 0; i < driverPath.size(); i++) {
				String key = driverPath.get(i).getName();
				String value = "";
				if (key == "Firefox") {
					value = driverPath.get(i).getText();
				} else {
					value = path + "/" + driverPath.get(i).getText();
				}

				driverPathMap.put(key, value);
			}
			configMap.put("DriverPath", driverPathMap);

			// get all case name that will be run
			List<Element> caseNameList = root.element("Case").elements("CaseName");
			configMap.put("Case", caseNameList);
		} catch (DocumentException e) {

			HtmlLogger.Message("DocumentException occurs during parse config file " + file.getName());
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = false;
			HtmlLogger.GenerateReport();
		}
		return configMap;
	}
}
