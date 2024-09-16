package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class HomePage {

    WebDriver driver;
    @FindBy(className = "app_logo")
    WebElement sauceDemoTitle;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productsTitle;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartIcon;

    @FindBy(className = "product_sort_container")
    WebElement filter;

    @FindBy(css = "li.social_twitter")
    WebElement TwitterIcon;

    @FindBy(css = "li.social_facebook")
    WebElement FacebookIcon;

    @FindBy(css = "li.social_linkedin")
    WebElement LinkedInIcon;

    @FindBy(css = "a[href='https://x.com/saucelabs']")
    WebElement linkTwitter;

    @FindBy(css = "a[href='https://www.facebook.com/saucelabs']")
    WebElement linkFacebook;

    @FindBy(css = "a[href='https://www.linkedin.com/company/sauce-labs/']")
    WebElement linkLinkedin;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean sauceDemoTitleIsDisplayed() {
        return sauceDemoTitle.isDisplayed();
    }

    public boolean isProductInHomePage(String productName){
        for(WebElement element : productsTitle){
            if(element.getText().equalsIgnoreCase(productName)){
                return true;
            }
        }
        return false;
    }

    public void addProductToCart(String productName){
        // add-to-cart-sauce-labs-bolt-t-shirt
        // sauce labs bolt t-shirt
        String productId = "add-to-cart-"+productName.replace(" ", "-").toLowerCase();
        WebElement productAddToCartButton = driver.findElement(By.id(productId));
        productAddToCartButton.click();
    }

    public void removeProductFromCart(String productName){
        // remove-sauce-labs-bolt-t-shirt
        // sauce labs bolt t-shirt
        String productId = "remove-"+productName.replace(" ", "-").toLowerCase();
        WebElement removeProductButton = driver.findElement(By.id(productId));
        removeProductButton.click();
    }

    public void clickOnCartButton(){
        cartIcon.click();
    }

    public boolean clickOnFilterHighToLow()
    {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            wait.until(ExpectedConditions.elementToBeClickable(filter));


            Select selectObject = new Select(filter);
            selectObject.selectByVisibleText("Price (high to low)");

            List<WebElement> productsList = driver.findElements(By.className("inventory_item_name"));
            List<WebElement> pricesList = driver.findElements(By.className("inventory_item_price"));

            List<Double> actualPricesOrder = new ArrayList<>();


            for (WebElement priceElement : pricesList) {
                String priceText = priceElement.getText().replace("$", "");
                actualPricesOrder.add(Double.parseDouble(priceText));
            }

            List<Double> sortedPricesDescending = new ArrayList<>(actualPricesOrder);
            sortedPricesDescending.sort(Comparator.reverseOrder());

            return actualPricesOrder.equals(sortedPricesDescending);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean clickOnTheFilterNameAtoZ()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(filter));

        Select selectObject = new Select(filter);
        selectObject.selectByVisibleText("Name (Z to A)");

        List<WebElement> productsList = driver.findElements(By.className("inventory_item_name"));

        List<String> actualProductOrder = new ArrayList<>();

        for (WebElement productElement : productsList) {
            actualProductOrder.add(productElement.getText());
        }

        List<String> sortedProductsAscending = new ArrayList<>(actualProductOrder);
        sortedProductsAscending.sort(Comparator.reverseOrder());

        return actualProductOrder.equals(sortedProductsAscending);
    }

    public void clickOnSocialMediaIcon(String socialMedia)
    {
        switch (socialMedia) {
            case "Twitter":
                TwitterIcon.click();

                break;
            case "Facebook":
                FacebookIcon.click();
                break;
            case "LinkedIn":
                LinkedInIcon.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid social media: " + socialMedia);
        }

    }

    public boolean verifyTheSocialMediaPageDisplayed(String socialMedia)
    {
        if (linkFacebook.isDisplayed())
            return true;
        else if(linkTwitter.isDisplayed())
            return true;
        else return linkLinkedin.isDisplayed();
    }
}
