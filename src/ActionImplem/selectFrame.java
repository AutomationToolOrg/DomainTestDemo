package ActionImplem;
import org.openqa.selenium.WebDriver;

import WebdriverEncapsulation.ConfigBuilder;

public class selectFrame extends Action {	
	
	@Override
	public void Do() {
		
		WebDriver driver=ConfigBuilder.Driver;		
		String iframe = ActionParam.get("name");
		driver.switchTo().frame(iframe);
	}
}
