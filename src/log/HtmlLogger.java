package log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class HtmlLogger {

	public static StringBuilder sbHtml = new StringBuilder();
	public static StringBuilder sbResults = new StringBuilder();
	public static String logPath = "log";
	public static String casename = "";
	public static String _message = "";
	public static boolean isBreak = false;
	public static boolean isCaseError = false;

	private static void FormatHtml() {
		sbHtml.append("<html>");
		sbHtml.append("<head>");
		sbHtml.append("<title>");
		sbHtml.append("</title>");
		sbHtml.append(
				"<style type=\"text/css\">.datalist{border:1px solid #429fff; font-family:Arial; border-collapse:collapse;} \n.datalist th{border:1px solid #429fff; background-color:#d2e8ff; font-weight:bold; text-align:center;}\n.datalist td{border:1px solid #429fff; text-align:left; padding:1px}\n.ret_Pass{color:Green}\n.ret_Fail{color:Red}\ntextarea {-moz-box-shadow:1px 1px 0 #E7E7E7;-moz-box-sizing:border-box;border-color:#CCCCCC #999999 #999999 #CCCCCC;border-style:solid;border-width:1px;font-family:arial,sans-serif;font-size:13px;height:160px;margin:10px auto;outline-color:-moz-use-text-color;outline-style:none;outline-width:medium;padding:2px;width:100%;}</style>");
		sbHtml.append("</head>");

		sbHtml.append("<body>");
		sbHtml.append("<table cellpadding=1 cellspacing=0 width=100% align=left class=\"datalist\"  ");
		sbHtml.append(" [TableDis] >");
		sbHtml.append("<thead>");
		sbHtml.append("<tr><th width=200>Case name</th><th width=80>Result</th><th>Error info</th></tr>");
		sbHtml.append("</thead>");
		sbHtml.append("<tbody>");
		sbHtml.append("<h2 align = \"center\">");
		sbHtml.append("Test Suite Report");
		sbHtml.append("</h2>");
		sbHtml.append("[Result]");
		sbHtml.append("");
		sbHtml.append("</tbody>");
		sbHtml.append("</table>");

		sbHtml.append("<textarea ");
		sbHtml.append("[TextAreaDis] >");
		sbHtml.append("[OtherError]");
		sbHtml.append("</textarea>");

		sbHtml.append("</body>");
		sbHtml.append("</html>");
	}

	public static void AddResult(String casename, String errorMessage, Boolean bResult,String src) {
		//bResult is the value of "isbreak"
			sbResults.append("<tr>");
			sbResults.append("<td>");
			sbResults.append(casename);
			sbResults.append("</td>");
			if (!bResult) {
				sbResults.append("<td " + "class=\"ret_Pass\">");
			} else {
				sbResults.append("<td " + "class=\"ret_Fail\">");
			}
			sbResults.append(bResult ? "Fail" : "True");
			sbResults.append("</td>");
			sbResults.append("<td>");
			sbResults.append(errorMessage);
			if(src!=""){
			sbResults.append("<img src=\"");
			sbResults.append(src);
			sbResults.append("\"/>");
			}
			sbResults.append("</td>");
			sbResults.append("</tr>");		
	}

	public static void Message(String message) {
		_message += message + "<br>";
		
	}
	public static void Message(String message,Boolean color) {
		_message += "<p class=\"ret_Fail\">"+message + "</p>";
	
	}
	//generate report for each browser
	public static void GenerateReport() {
		FormatHtml();
		String strResult = sbHtml.toString();
		if (!isCaseError) {
			// other error occurs, like config file
			strResult = strResult.replace("[TableDis]", "style=\"display:none\"");
			strResult = strResult.replace("[TextAreaDis]", "");
			strResult = strResult.replace("[Result]", "");
			//relace all html char to text so that can display on textarea well
			_message=_message.replaceAll("<br>", "\n");
			_message=_message.replaceAll("<blockquote>", "");
			_message=_message.replaceAll("</blockquote>", "");
			strResult = strResult.replace("[OtherError]", _message);	
			System.out.println(strResult);
		} else {
			// error on case
			strResult = strResult.replace("[TableDis]", "");
			strResult = strResult.replace("[TextAreaDis]", "style=\"display:none\"");
			strResult = strResult.replace("[Result]", sbResults.toString());
			strResult = strResult.replace("[OtherError]", "");
		}
		//clear message
		_message="";
		casename="";

		File file = new File(logPath + "/TraceData" + new Date().toString().replace(":", "") + ".html");
		try {

			FileWriter fw = new FileWriter(file, true);
			PrintWriter out = new PrintWriter(fw);
			out.println(strResult);
			out.flush();
			out.close();
			fw.close();

		} catch (Exception e) {

			System.out.print("write file error!");
			e.printStackTrace();
		}
	}
}
