package ActionImplem;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Parse.Action;

public class Navigate extends Action{

	@Override
	public void Do() {
		String url=ActionParam.get("value");
		WebDriver driver=ConfigBuilder.Driver;
		driver.get(url);
	}

}
