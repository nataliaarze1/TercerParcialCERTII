package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage {
    WebDriver driver;
    @FindBy(id = "item_2_title_link")
    WebElement item;

    public OverviewPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean checkSummarize(String ExpectedItem)
    {
        return ExpectedItem.equals(item.getText());
    }
}
