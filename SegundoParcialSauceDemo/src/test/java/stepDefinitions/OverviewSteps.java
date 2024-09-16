package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import pages.OverviewPage;
import utilitiies.DriverManager;

import java.util.List;

public class OverviewSteps {
    OverviewPage overviewPage = new OverviewPage(DriverManager.getDriver().driver);
    @And("The checkout summary page should display the correct details for")
    public void summarizeItems(DataTable items)
    {
        List<String> data = items.transpose().asList(String.class);
        Assertions.assertTrue(overviewPage.checkSummarize(data.getFirst()));
    }
}
