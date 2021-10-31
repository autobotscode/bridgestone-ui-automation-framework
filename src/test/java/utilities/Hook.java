package utilities;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Properties;

public class Hook {

    private WebDriver webDriver;

    private BrowserUtils browserUtils = BrowserUtils.getInstance();

	private BsPojo pojo = BsPojo.getInstance();

    private String LOG4J_FILE = System.getProperty("user.dir") + File.separator + "log4j.properties";

    private Logger log = Logger.getLogger("Hook");

    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_PATH = "./src/test/resources/browser_drivers/chromedriver.exe";

    Properties properties = ReadPropertyFile.getInstance().getProperties();

    @Before
    public void launchBrowser(Scenario scenario) {
        log.info("Starting running scenario:::" + scenario.getName());
        PropertyConfigurator.configure(LOG4J_FILE);


        String browser = System.getProperty("browser") == null ? properties.getProperty("browser") : System.getProperty("browser");
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty(CHROME_DRIVER, CHROME_DRIVER_PATH);
                webDriver = new ChromeDriver();
                break;
            case "firefox":
				//set firefox
                break;
            default:
				log.info("no browser matches found");
        }

        webDriver.manage().window().maximize();
        browserUtils.initialize(webDriver);
        log.info("Browser Launched-->"+ browser);
		pojo.setBrowser(browser);


    }

    @After
    public void destoryDriver(Scenario scenario) {
        if (browserUtils.getDriver()!=null) {
            webDriver.close();
            log.info("Browser closed");
        }
    }

}
