package log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Date;


public class logger {
	
	
	
	private final static String logfoder = "log";	
	
	public static void Message(String message){	
		   
		    Date date = new Date();
		    File file=new File(logfoder + "/" +"TraceData.txt");			
			try {
				
				FileWriter fw = new FileWriter(file, true);
			    PrintWriter out = new PrintWriter(fw);
			    out.println(message);
			    out.flush();
			    out.close();
			    fw.close();
				
			} catch (Exception e) {
				
				System.out.print("write file error!");
				e.printStackTrace();
			}
	}			
			
	public static void ErrorMessage(String path, String message){		
		
	    File file=new File(path);
		if (!file.exists())
		 {
			file.mkdir();
		 }		
					
		}
    public static void SuccessfulMessage(String path, String message){		
		
	    File file=new File(path);
		if (!file.exists())
		 {
			file.mkdir();
		 }		
					
		}
}



