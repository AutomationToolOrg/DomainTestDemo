package log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class HtmlLogger {
	
	public static  StringBuilder sbHtml = new StringBuilder();
	public static  StringBuilder sbResults = new StringBuilder();
	public static  String logPath = "log";
	public static  String casename = "";
	public static  boolean _isPass = false;
	public static  String  _message = "";
    
//    public _logger() {
//    	 FormatHtml();   
//	}   

    private static void FormatHtml()
    {
        sbHtml.append("<html>");
        sbHtml.append("<head>");
        sbHtml.append("<title>");
        sbHtml.append("</title>");
        sbHtml.append("<style type=\"text/css\">.datalist{border:1px solid #429fff; font-family:Arial; border-collapse:collapse;} \n.datalist th{border:1px solid #429fff; background-color:#d2e8ff; font-weight:bold; text-align:center;}\n.datalist td{border:1px solid #429fff; text-align:left; padding:1px}\n.ret_Pass{color:Green}\n.ret_Fail{color:Red}</style>");
        sbHtml.append("</head>");

        sbHtml.append("<body>");
        sbHtml.append("<table cellpadding=1 cellspacing=0 width=100% align=left class=\"datalist\"> ");
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
        sbHtml.append("</body>");
        sbHtml.append("</html>");
    }   

    public static void AddResult(String casename, String errorMessage, Boolean bResult)
    {
        sbResults.append("<tr>");
        sbResults.append("<td>");
        sbResults.append(casename);
        sbResults.append("</td>");        
        if (bResult)
        {
            sbResults.append("<td " + "class=\"ret_Pass\">");
        }
        else
        {
            sbResults.append("<td " + "class=\"ret_Fail\">");
        }
        sbResults.append(bResult ? "Pass" : "Fail");
        sbResults.append("</td>");
        sbResults.append("<td>");
        sbResults.append(errorMessage);
        sbResults.append("</td>");
        sbResults.append("</tr>");
    }
    
    
    public static String Message(String message){		   
	   _message += message;
	   return _message;
   }			
    
    public static void GenerateReport()
    {
    	FormatHtml();
        String strResult = sbHtml.toString();
        String strText = strResult.replace("[Result]", sbResults.toString());
        File file=new File(logPath + "/TraceData" + new Date().toString().replace(":", "")+".html");			
		try {
			
			FileWriter fw = new FileWriter(file, true);
		    PrintWriter out = new PrintWriter(fw);
		    out.println(strText);
		    out.flush();
		    out.close();
		    fw.close();
			
		} catch (Exception e) {
			
			System.out.print("write file error!");
			e.printStackTrace();
		}
  }			
}     
    


