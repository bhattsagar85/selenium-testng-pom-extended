package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    protected final WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(how = How.XPATH, using = "//a[@class='logout']")
    private WebElement logoutLink;
    @FindBy(how = How.XPATH, using = "//a[@class='account']/span")
    private WebElement profileNameLabel;

    public HomePage doLogout(){
        logoutLink.click();
        return this;
    }

    public String getLoggedInUsername() {
        return profileNameLabel.getText();
    }

}
