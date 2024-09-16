package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.YourCartPage;
import utilitiies.DriverManager;

import java.util.List;

public class YourCartSteps {
    YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
    @And("I click on the checkout button")
    public void clickOnTheCheckoutButton()
    {
        yourCartPage.clickOnCheckoutButton();
    }
}
