package stepdefs;

import com.opencsv.CSVWriter;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.TiresPage;
import utilities.Asserts;
import utilities.BrowserUtils;
import utilities.BsPojo;
import utilities.ReadPropertyFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TiresPageStepDefs {

    public Logger log = Logger.getLogger("TirePageStepDefs");
    TiresPage tiresPage = new TiresPage();
    Properties properties = ReadPropertyFile.getInstance().getProperties();
    BrowserUtils browserUtils;
    WebDriver webDriver;
    List<WebElement> wheelDiameters, wheelTireInches;
    private BsPojo pojo = BsPojo.getInstance();
    List<String[]> csvData = new ArrayList<String[]>();
    String csvInputData;


    public TiresPageStepDefs() {
        browserUtils = BrowserUtils.getInstance();
        webDriver = browserUtils.getDriver();
    }

    @When("^User captures Tire Size,Tire Count, URL for every diameter$")
    public void userCapturesTireSizeTireCountURLForEveryDiameter() {
        csvData.add(properties.getProperty("csvHeaders").split(","));
        if (wheelDiameters.size() > 0) {
            //Iterating to each diameter
            for (int d=0; d<1; d++) {
                String diameterText = wheelDiameters.get(d).getText().trim();
                browserUtils.click(wheelDiameters.get(d));
                browserUtils.waitForPageTitle("Inch Tires",30);
                log.info("##############clicked on " + diameterText + " diameter URL:->+"+webDriver.getCurrentUrl()+"+######");
                Asserts.expectToBeTrue(webDriver.getTitle().contains("Inch Tires"), "Failed to navigate " + diameterText + " tires");
                browserUtils.waitForElements(tiresPage.inchTires,30);
                wheelTireInches = webDriver.findElements(tiresPage.inchTires);
                List<String> tireInchesText = wheelTireInches.stream().map(elt -> elt.getText().trim()).filter(it -> it.endsWith(diameterText.replace("\"",""))).collect(toList());
                Asserts.expectToBeTrue(wheelTireInches.size()==tireInchesText.size(),"Listed in-correct size of diameters"+tireInchesText);
                    for(int t=0; t<wheelTireInches.size();t++){
                        String tireTechText = wheelTireInches.get(t).getText().trim();
                       // browserUtils.javaScriptClick(wheelTireInches.get(t),30);
                        webDriver.get(wheelTireInches.get(t).getAttribute("href"));
                        browserUtils.waitForPageTitle("Tire Catalog",30);
                        Asserts.expectToBeTrue(webDriver.getTitle().contains("Tire Catalog"), "Failed to navigate " + tireTechText + " tire");
                        browserUtils.waitForVisibility(tiresPage.tireProfile,30);
                        String tireCount = tiresPage.tireCount.size() == 0 ? "0" :tiresPage.tireCount.get(0).getText().trim().split(" ")[0];
                        log.info("##############clicked on " + tireTechText + "Tire Count -->"+ tireCount+" Tire URL:->+"+webDriver.getCurrentUrl()+"+######");
                        csvInputData=tireTechText+","+tireCount+","+webDriver.getCurrentUrl();
                        csvData.add(csvInputData.split(","));
                        csvInputData="";
                        ((JavascriptExecutor)webDriver).executeScript("window.history.go(-1)");
                        browserUtils.waitForElements(tiresPage.inchTires,30);
                        wheelTireInches = webDriver.findElements(tiresPage.inchTires);
                    }

                    webDriver.get(pojo.getUrl());
                    browserUtils.waitForElements(tiresPage.diameters,30);
                    wheelDiameters = webDriver.findElements(tiresPage.diameters);
            }

        } else {
            log.info("No of Wheel Diameters found");
        }


    }

    @And("^User writes captured data into CSV file$")
    public void userWritesCapturedDataIntoCSVFile() {


          File file = new File("Results_" + LocalDateTime.now().toString().replace(":","") + ".csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter

            CSVWriter writer = new CSVWriter(outputfile);

            if(csvData.size() >0)
                 writer.writeAll(csvData);
            else
                writer.writeNext(new String[]{"No Diameters fond"});

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @When("^User captures available wheel size diameters$")
    public void userCapturesAvailableWheelSizeDiameters() {
        //find no of diameters
        wheelDiameters = webDriver.findElements(tiresPage.diameters);
        log.info("Available wheel diameters size " + wheelDiameters.size());
    }

    @Given("^User navigates to \"([^\"]*)\"$")
    public void userNavigatesTo(String url) throws Throwable {
        webDriver.navigate().to(url);
        pojo.setUrl(url);
        browserUtils.waitForElements(tiresPage.diameters, 0);
        Asserts.expectToBeTrue(webDriver.getTitle().contains("Shop Tire Sizes"), "Failed to navigate Tires Page");
        browserUtils.waitForVisibility(tiresPage.closeBanner,30);
        browserUtils.click(tiresPage.closeBanner);
    }
}
