package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TiresPage extends MasterPage {

    public TiresPage() {
        super();
    }


    public  By diameters = By.xpath("//div[@class='button']//a[contains(@href,'inch')]");

    public  By inchTires = By.xpath("//ul[@class='tires-by-diameter__links']//a");

    public By tireMatches = By.xpath("//div[contains(text(),'Tire Results')]");

    @FindBy(css=".tsr-profile__vehicle")
    public WebElement tireProfile;

    @FindBy(xpath = "//div[contains(text(),'Tire Results')]")
    public List<WebElement> tireCount;

    @FindBy(css =".cc-close__btn")
    public  WebElement closeBanner;



}
