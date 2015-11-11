package ActionImplem;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;

import WebdriverEncapsulation.ConfigBuilder;
import log.HtmlLogger;
import log.Logmessage;

public class Sleep extends Action{

	@Override
	public void Do() {
		WebDriver driver=ConfigBuilder.Driver;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			HtmlLogger.Message("Interrupted Exception occurs on Sleep action",true);			
			Logmessage.StackInfo(e);
			HtmlLogger.isBreak = true;
			HtmlLogger.isCaseError = true;
		}
	}

}
