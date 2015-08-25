package ActionImplem;
import Parse.Action;
import org.dom4j.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import Parse.Action;

public class Type extends Action{

	@Override
	public void Do() {
		WebDriver driver=ConfigBuilder.Driver;
		String ideifier=ActionParam.get("identifyby").trim();
		String value=ActionParam.get("identifyvalue");
		switch(ideifier)
		{
		
			case "id":				
				WebElement element=FindElement.GetElementById(driver, value);
				String typeString=ActionParam.get("value");		
				element.sendKeys(typeString);
				break;
			case "class":
				break;
			case "xpath":
				break;
			
		}
		
		
	}

}
