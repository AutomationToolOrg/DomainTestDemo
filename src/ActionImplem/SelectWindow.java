package ActionImplem;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import WebdriverEncapsulation.ConfigBuilder;

public class SelectWindow extends Action {

	static int i = 0;
	static String beforeHandle;

	@Override
	public void Do() {
		WebDriver driver = ConfigBuilder.Driver;
		try {
			i++;
			
			Set<String> handles = driver.getWindowHandles();
			// String currentWindow=driver.getWindowHandle();;
			if (i % 2 != 0) {
				beforeHandle = driver.getWindowHandle();
			}

			for (String window : handles) {
				if (i % 2 != 0) {
					if ((!beforeHandle.equals(window)) && (i % 2 != 0)) {
						driver.switchTo().window(window);
					}
				} else if (i % 2 == 0) {
					driver.switchTo().window(beforeHandle);
				}
			}

		} catch (Exception e) {
			driver.switchTo().window(beforeHandle);
			System.out.println(e.getStackTrace());

		}

	}

}
