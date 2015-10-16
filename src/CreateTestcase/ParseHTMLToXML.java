package CreateTestcase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.ParserException;

public class ParseHTMLToXML {

	public static void Parse(String HTMLFile) {
		// Create parser of HTML
		String html="";
		try {
			html = ReadHtmlFile("HTML/"+HTMLFile);
		} catch (Exception e) {
			System.out.println("Exception occurs on read html file"+HTMLFile);
			e.printStackTrace();
		}
	
		Parser parser = Parser.createParser(html, "UTF-8");
		// parse table in html
		NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
		TableTag table=null;
		try {
			table = (TableTag) parser
					.extractAllNodesThatMatch(tableFilter).elementAt(0);
		} catch (ParserException e) {
			System.out.println("Exception occurs on HTMLParser");
			e.printStackTrace();
		}
		TableRow[] rows = table.getRows();
		// Handle thead and get the case name
		String caseName = rows[0].getColumns()[0].getStringText();

		// create document for xml
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("CaseScript");
		Element Case = root.addElement("Case");
		Case.setAttributeValue("name", caseName);
		// Handle tbody and get action and param
		for (int i = 1; i < rows.length; i++) {
			TableRow tr = rows[i];
			TableColumn[] td = tr.getColumns();
			String action = td[0].getStringText();
			String target = td[1].getStringText();
			String value = td[2].getStringText();
			boolean AddSleep=false;
			// handle action tag
			action = Character.toUpperCase(action.charAt(0))
					+ action.substring(1);
			if (action.toLowerCase().indexOf("wait") != -1) {
				//ClickAndWait tranfer to Click
				action = action.substring(0, action.toLowerCase()
						.indexOf("and"));
				AddSleep=true;
			}
			//set the subnode of action node
			Element actionNode = Case.addElement(action);
			if (action.equals("Open")) {
				AddSleep=true;
				Element targetNode = actionNode.addElement("url");
				targetNode.setText(target);
			} else if(action.equals("SelectFrame")){
				Element targetNode = actionNode.addElement("name");
				targetNode.setText(target);				
			}else if(action.equals("SelectWindow")){
								
			}else {
				if(!value.equals("")&&action.equals("VerifyElementPresent")){			
					
					actionNode.addAttribute("description", value);
				}
				if(!value.equals("")&&action.equals("Click")){			
					
					actionNode.addAttribute("description", value);
				}
				// parse id, xpath,css 
				String tagName="";
				if(target.indexOf("id=")==0){
					tagName="id";
					target=target.substring(target.indexOf("=")+1);
				}else if(target.indexOf("css=")==0){
					tagName="css";	
					target=target.substring(target.indexOf("=")+1);
				}else{				
					tagName="xpath";
					if(target.indexOf("xpath")!=-1){
						target=target.substring(target.indexOf("=")+1);
					}					
				}
				Element targetNode = actionNode.addElement(tagName);
				targetNode.setText(target);
			}

			if (!value.equals("")&&!actionNode.getName().equals("VerifyElementPresent")&&!actionNode.getName().equals("Click")) {
				Element valueNode = actionNode.addElement("value");
				valueNode.setText(value);
			} 
			//set sleep node 
			if(AddSleep){			
				Element sleep=Case.addElement("Sleep");
				sleep.setText("");
			}
		}
		try {
			FileWriter writers = new FileWriter(new File("Case/"+caseName+".xml"));
			String caseFile=document.asXML();
			//replace all special chars		
			caseFile=caseFile.replaceAll("&quot;", "\"");
			caseFile=caseFile.replaceAll("&amp;", "&");
			caseFile=caseFile.replaceAll("&nbsp;", " ");
			caseFile=caseFile.replaceAll("&lt;", "<");
			caseFile=caseFile.replaceAll("&gt;", ">");
			
			writers.write(caseFile);
			writers.close();
		} catch (Exception e) {
			System.out.println("Exception occurs on create xml file "+ caseName+".xml");
			e.printStackTrace();
		}
		
		
	}


	public static String ReadHtmlFile(String path) throws Exception {
		File file = new File(path);
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));

			String szContent = "";
			String szTemp;
			while ((szTemp = bis.readLine()) != null) {
				szContent += szTemp + "\n";
			}
			bis.close();
			return szContent;
		} catch (Exception e) {
			return "Exception occurs during read html file\n"
					+ e.getStackTrace();
		}

	}

}
