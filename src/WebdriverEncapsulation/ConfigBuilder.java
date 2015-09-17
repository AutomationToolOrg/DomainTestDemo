package WebdriverEncapsulation;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public final class ConfigBuilder {
	public static String Broswer = GetBrowser();
	public static WebDriver Driver = ChooseDriver();
	
	private static WebDriver ChooseDriver() {
		WebDriver driver = null;

		switch (Broswer) {
		case "IE":
			System.setProperty("webdriver.ie.driver",
					"Drivers/IEDriverServer_x64_2.42.0/IEDriverServer.exe");
		
			driver = new InternetExplorerDriver();
			break;

		case "Firefox":
			System.setProperty("webdriver.firefox.bin","D:\\my folder\\ff\\firefox.exe");    
			FirefoxProfile profile = new FirefoxProfile();
	        profile.setPreference("network.proxy.type", 1);
			driver = new FirefoxDriver(profile);
			driver.manage().window().maximize();
//			System.setProperty("webdriver.firefox.bin","D:\\my folder\\ff\\firefox.exe");    
//			driver = new FirefoxDriver();
			break;

		case "Chrome":
			System.setProperty("webdriver.chrome.driver",
					"Drivers/ChromeDriver_Win32/chromedriver.exe");
			
			driver = new ChromeDriver();
		
		default:
			break;
		}

		return driver;
	}
	private static String GetBrowser(){
		String  browserType = ""; 		
		SAXReader saxReader = new SAXReader();	    
		Document document;
		//test code
		//String file = Path.class.getResource("/Drivers/IEDriverServer_win32_2.42.0/IEDriverServer.exe").getPath();
		
		try {			
			//document = saxReader.read(new File(classLoader.getResource("Config/RunConfig.xml").getFile()));
			document = saxReader.read(new File("Config/RunConfig.xml"));
			Element root = document.getRootElement();
			Element driverElement = root.element("Driver");
			browserType = driverElement.getText();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	}
			
		return browserType;
	}
}
