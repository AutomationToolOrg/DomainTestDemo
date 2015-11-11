package ActionImplem;

import org.openqa.selenium.WebDriver;
import WebdriverEncapsulation.ConfigBuilder;

public class Open extends Action {
	@Override
	public void Do()  {
		WebDriver driver = ConfigBuilder.Driver;
		String url = ActionParam.get("url");
		driver.get(url);
	}
}
