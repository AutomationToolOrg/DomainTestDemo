package WebdriverEncapsulation;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;

public final class ConfigBuilder {
	public static String Browser = "";
	public static WebDriver Driver = null;
	public static Map<String, String> driverPath = null;

	public static WebDriver ChooseDriver() {
		WebDriver driver = null;
		switch (Browser) {
		case "IE":
			String IEServer = driverPath.get(Browser);
			System.setProperty("webdriver.ie.driver", IEServer);
			// System.setProperty("webdriver.ie.driver",
			// "Drivers/IEDriverServer_win32_2.42.0/IEDriverServer.exe");
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability("ignoreProtectedModeSettings", true);
			driver = new InternetExplorerDriver(dc);

			// driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;

		case "Firefox":
			// System.setProperty("webdriver.firefox.bin","D:\\my
			// folder\\ff\\firefox.exe");
			// FirefoxProfile profile = new FirefoxProfile();
			// profile.setPreference("network.proxy.type", 1);
			// driver = new FirefoxDriver(profile);
			// driver.manage().window().maximize();
			String FFServer = driverPath.get(Browser);
			System.setProperty("webdriver.firefox.bin", FFServer);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.proxy.type", 1);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "Chrome":
			String ChromeServer = driverPath.get(Browser);
			System.setProperty("webdriver.chrome.driver", ChromeServer);
			driver = new ChromeDriver();
			// driver.manage().window().maximize();
		case "Android":
			SelendroidLauncher selendroidServer = null;
			SelendroidConfiguration config = new SelendroidConfiguration();
			selendroidServer = new SelendroidLauncher(config);
			selendroidServer.launchSelendroid();
			DesiredCapabilities caps = SelendroidCapabilities.android();
			try {
				driver = new SelendroidDriver(caps);
			} catch (Exception e) {
				selendroidServer.stopSelendroid();
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

		return driver;
	}

}
