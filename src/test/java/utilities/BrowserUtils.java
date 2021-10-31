package utilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class BrowserUtils {

	private static final BrowserUtils browserUtils = new BrowserUtils();
	public WebDriver webDriver;

	public Logger log = Logger.getLogger("BrowserUtils");
	
	public void initialize(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
	
	public void selectDropDownOption(WebElement element, int index){
		waitForVisibility(element,10);
		log.info("Waiting for dropdown visibility");
		Select select = new Select(element);
		select.selectByIndex(index);

	}

	public void selectDropDownOptionByValue(WebElement element, String option){
		waitForVisibility(element,10);
		log.info("Waiting for dropdown visibility");
		Select select = new Select(element);
		select.selectByVisibleText(option);

	}

	public void clearAndFillText(WebElement element, String fillText){
		waitForVisibility(element,10);
		log.info("Waiting for text field visibility");
		element.clear();
		element.sendKeys(fillText);
		log.info("Text Entered as "+ fillText);

	}

	public void javaScriptClick(WebElement element, int timeToWaitInSec){
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeToWaitInSec);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", element);
	}
    public void click(WebElement element){
		waitForVisibility(element,10);
		hover(element);
		element.click();
	}

	public String getText(WebElement element){
		waitForVisibility(element,10);
		return  element.getText().trim();
	}

    public void waitForVisibility(WebElement element, int timeToWaitInSec){
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeToWaitInSec);
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElements(By diameters, int timeToWaitInSec){
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeToWaitInSec);
		webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(diameters,0));
	}

	public  void scrollIntoView(WebElement element){
		waitForVisibility(element,10);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void waitForPageTitle(String title, int timeToWaitInSec){
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeToWaitInSec);
		webDriverWait.until(ExpectedConditions.titleContains(title));
	}

	public void hover(WebElement element){
		Actions actions = new Actions(webDriver);
		actions.moveToElement(element).perform();
	}

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(webDriver, 60);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	public String getScreenShotPath(String nameOfScreenShot) {

		String path = System.getProperty("user.dir") + "\\screenshots\\";
		File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			path = path + nameOfScreenShot + LocalDateTime.now().toString() + ".png";
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public void waitFor(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return driver class instance
	 */
	public static BrowserUtils getInstance() {
		return browserUtils;
	}

	public Object clone()
			throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	

    public WebDriver getDriver() {
        return webDriver;
    }


}
