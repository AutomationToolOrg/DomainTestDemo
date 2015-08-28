package ActionImplem;

import java.util.Map;

import org.dom4j.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import WebdriverEncapsulation.ConfigBuilder;
import WebdriverEncapsulation.FindElement;





public class Type extends Action{

	@Override
	public void Do() {
		WebDriver driver=ConfigBuilder.Driver;
		String ideifier="";
		String value="";
		WebElement element=null;
		for(Map.Entry<String,String> actionParam :ActionParam.entrySet()){
			String key=actionParam.getKey();
			if(key=="id"||key=="css"||key=="xpath"){
				ideifier=key;
				break;
			}			
		}
		value=ActionParam.get(ideifier).trim();
		String content=ActionParam.get("value");
		switch(ideifier)
		{
		
			case "id":				
				element=FindElement.GetElementById(driver, value);	
				
				element.sendKeys(content);
				break;
			case "class":
				break;
			case "xpath":
				element=FindElement.GetElementByXpath(driver, value);					
				element.sendKeys(content);
				break;
			case "css":
				element=FindElement.GetElementByCSS(driver, value);					
				element.sendKeys(value);
			break;
		}
		
		
	}

}
