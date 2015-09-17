package ActionImplem;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;

import WebdriverEncapsulation.ConfigBuilder;

public class Sleep extends Action{

	@Override
	public void Do() {
		WebDriver driver=ConfigBuilder.Driver;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
