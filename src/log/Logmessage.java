package log;

import WebdriverEncapsulation.ConfigBuilder;

public class Logmessage {
	public static void log(){
		
		ConfigBuilder.Driver.close();
		if (HtmlLogger._message == "") {
			HtmlLogger._isPass = true;
		} else {
			HtmlLogger._isPass = false;
		}
		HtmlLogger.AddResult(HtmlLogger.casename, HtmlLogger._message,
				HtmlLogger._isPass);
		HtmlLogger.GenerateReport();
		System.exit(0);
	}
	public static void StackInfo(Exception e){
		StackTraceElement[] stackElements=e.getStackTrace();
		HtmlLogger.Message(e.getClass().toString());
		HtmlLogger.Message(e.getMessage().toString());
		for(int i=0;i<stackElements.length;i++){
			HtmlLogger.Message("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"at "+stackElements[i].getClassName()+"."+stackElements[i].getMethodName()+"("+stackElements[i].getFileName()+" : "+stackElements[i].getLineNumber()+")");				
		} 
		
	}
	}
