package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en_scouse.An;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import utilitiies.DriverManager;

public class HomeSteps {
    HomePage homePage = new HomePage(DriverManager.getDriver().driver);

    @And("The home page should be displayed")
    public void verifyHomePageIsDisplayed(){
        Assertions.assertTrue(homePage.sauceDemoTitleIsDisplayed());
    }

    @And("I click on the filter Price (high to low)")
    public void clickOnFilterHighToLow()
    {
        Assertions.assertTrue(homePage.clickOnFilterHighToLow());
    }

    @And("I click on the filter Name Z to A")
    public void clickOnFilterManeAtoZ()
    {
        Assertions.assertTrue(homePage.clickOnTheFilterNameAtoZ());
    }

    @And("I add to the cart the product {string}")
    public void AddProductToCart(String productName)
    {
        homePage.addProductToCart(productName);
    }

    @And("I click on the cart button")
    public void clickOnTheCartButton()
    {
        homePage.clickOnCartButton();
    }

    @And("I click on the {string} icon")
    public void clickOnTheSocialMediaIcon(String icon)
    {
        homePage.clickOnSocialMediaIcon(icon);
    }

    @Then("Social Media {string} account must be opened")
    public void socialMediaAccountDisplayed(String socialMedia)
    {
        homePage.verifyTheSocialMediaPageDisplayed(socialMedia);
    }



}
