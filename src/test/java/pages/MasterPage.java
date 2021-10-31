package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;

public abstract class MasterPage {

	public WebDriver webDriver;
	public BrowserUtils browserUtils;
	
	 public MasterPage() {
	        browserUtils = BrowserUtils.getInstance();
	        webDriver = browserUtils.getDriver();
	        PageFactory.initElements(webDriver, this);
	    }

}
