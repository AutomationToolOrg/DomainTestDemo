package ActionImplem;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import WebdriverEncapsulation.ConfigBuilder;



public class Open extends Action{

	@Override
	public void Do() {
		
		WebDriver driver=ConfigBuilder.Driver;
		String url=ActionParam.get("url");
		driver.get(url);
	}

}
