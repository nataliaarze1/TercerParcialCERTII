package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import pages.YourInformationPage;
import utilitiies.DriverManager;

import java.util.List;
import java.util.Map;

public class YourInformationSteps {
    YourInformationPage yourInformationPage = new YourInformationPage(DriverManager.getDriver().driver);
    @And("I fill the checkout information with")
    public void fillInformation(DataTable data)
    {
        Map<String, String> row = data.asMaps(String.class, String.class).getFirst();

        String firstName = row.get("First Name");
        if (firstName != null && !firstName.isEmpty()) {
            yourInformationPage.setFirstNameTextBox(firstName);
        } else {

            yourInformationPage.setFirstNameTextBox("");
        }
        yourInformationPage.setLastNameTextBox(row.get("Last Name"));
        yourInformationPage.setZipCodeTextBox(row.get("Postal Code"));

    }

    @And("I click on continue button")
    public void clickOnContinueButton() throws InterruptedException {
        yourInformationPage.clickOnContinueButton();
    }

    @And("A message that says {string} should be displayed")
    public void verifyErrorMessage(String ObtainedMessage)
    {
        Assertions.assertTrue(yourInformationPage.verifyMessage(ObtainedMessage));
    }
}
