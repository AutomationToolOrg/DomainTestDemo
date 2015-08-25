package ActionImplem;

import java.io.File;
import java.io.IOException;

import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;




import Parse.Action;
import WebdriverEncapsulation.ConfigBuilder;

public class screenshot extends Action{
	static int i=0;
	public screenshot(){
		
		i++;
	}
	@Override
	public void Do() {
		WebDriver driver=ConfigBuilder.Driver;
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String outputFolder=ActionParam.get("outputFolder").trim();
		
		try {
			
			File file=new File(outputFolder);
			file.mkdir();
			FileUtils.copyFile(srcFile, new File(file+"\\"+i+".png"));
		} catch (IOException e) {
			System.out.println("There occurs IOException during making Screenshot");
			System.out.println("e.printStackTrace()");
		}		
	}
	
}
