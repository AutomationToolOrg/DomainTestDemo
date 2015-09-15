package ActionImplem;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import WebdriverEncapsulation.ConfigBuilder;

public class selectWindow extends Action {
		static Set<String> beforeHandles;
		static int  i=0;
	 	
	  @Override
      public void Do() {	
		  i++;
		 WebDriver driver =ConfigBuilder.Driver;     
	     Set<String> handles= driver.getWindowHandles();
	     
	     String currentWindow;
	     if(i%2!=0){	    	 
	    	 beforeHandles=handles;   	    	
	     }

	     try{
	    	 Iterator<String> it = handles.iterator();
	    	 currentWindow=driver.getWindowHandle();
	    	 while(it.hasNext())
		     {
		    	 if (currentWindow != it.next()) {
		    		 
		    		 driver.switchTo().window(it.next());	  
		    	 }    	 	 
		     }
	    	 beforeHandles.remove(it.next());
	     }catch(Exception e){
	    	
	    	 Iterator<String> it = beforeHandles.iterator();
	    	 while(it.hasNext())
		     {		    		 
		    		 driver.switchTo().window(it.next());	  
 	 	 
		     }
	    	 
	     }
	     }    	 
	    	
}          
	    
	    
	    
	    

	


