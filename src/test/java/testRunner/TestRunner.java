package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features= {
        			".//Features/LaunchCabPage.feature", ".//Features/FillCabData.feature", 
        			".//Features/SelectSUV.feature", ".//Features/LaunchGiftCard.feature",
        			".//Features/GiftCardInvalidEmail.feature", ".//Features/LaunchHotelPage.feature"
        		   },
        glue="stepDefinitions",
        plugin= {
        		"pretty", "html:reportsCucumber/myreport.html", 
        		"json:reportsCucumber/myreport.json"
        		},    //Mandatory to capture failures
        dryRun=false,
        monochrome=true,
        tags = "@Sanity"	//Scenarios tagged with @sanity,
      )
public class TestRunner 
{

}

