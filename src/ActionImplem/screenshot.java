package ActionImplem;

import java.io.File;
import java.io.IOException;


import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;

import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

import WebdriverEncapsulation.ConfigBuilder;
import net.sourceforge.htmlunit.corejs.javascript.ast.NewExpression;
import WebdriverEncapsulation.ConfigBuilder;

public class screenshot extends Action{
	
	@Override
	public void Do() {
		
		String imagename = ActionParam.get("Case") + new Data().toString();
		WebDriver driver=ConfigBuilder.Driver; 
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String outputFolder= "ScreenShot";
		
		try {
			
			File file=new File(outputFolder);
			if (!file.exists())
			{
				file.mkdir();
			}			
			FileUtils.copyFile(srcFile, new File(file+"\\"+imagename+".png"));
		} catch (IOException e) {
			System.out.println("There occurs IOException during making Screenshot");
			System.out.println("e.printStackTrace()");
		}		
	}
	
}
