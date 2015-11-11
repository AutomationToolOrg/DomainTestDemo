package log;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Parse.Test;
import WebdriverEncapsulation.ConfigBuilder;

public class Logmessage {
	public static void log(String src) {
		//take screenshot
		src=takeScreenshot(src);
		HtmlLogger.AddResult(HtmlLogger.casename, HtmlLogger._message, HtmlLogger.isBreak,src);
		HtmlLogger._message = "";
	}
	public static void log() {
		HtmlLogger.AddResult(HtmlLogger.casename, HtmlLogger._message, HtmlLogger.isBreak,"");
		HtmlLogger._message = "";
	}
public static String takeScreenshot(String name){
	WebDriver driver=ConfigBuilder.Driver;
	File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
	String outputFolder= Test.path+"\\ScreenShot";
	String imgName="";
	try {
		
		File file=new File(outputFolder);
		imgName=file+"\\"+name+".png";
		if (!file.exists())
		{
			file.mkdir();
		}			
		
		FileUtils.copyFile(srcFile, new File(imgName));
	} catch (IOException e) {
		
		HtmlLogger.Message("error occurs on take scrrenshot " + name);
		Logmessage.StackInfo(e);
		HtmlLogger.isBreak = true;
		HtmlLogger.isCaseError = false;
		HtmlLogger.GenerateReport();
	}	
	return imgName;
}
	public static void StackInfo(Exception e) {
		StackTraceElement[] stackElements = e.getStackTrace();
		if (e.getClass() != null) {
			HtmlLogger.Message(e.getClass().toString());
		}
		if (e.getMessage() != null) {
			HtmlLogger.Message(e.getMessage().toString());
		}
		if (stackElements != null) {
			HtmlLogger.Message("<blockquote>");
			for (int i = 0; i < stackElements.length; i++) {
				HtmlLogger.Message("at " + stackElements[i].getClassName() + "." + stackElements[i].getMethodName()
						+ "(" + stackElements[i].getFileName() + " : " + stackElements[i].getLineNumber() + ")");
			}

			HtmlLogger.Message("</blockquote>");
		}
	}
}
