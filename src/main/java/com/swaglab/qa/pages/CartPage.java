package com.swaglab.qa.pages;

import com.swaglab.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TestBase {

    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkoutBtn;

    public CartPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean verifyCheckoutBtn(){
        return checkoutBtn.isDisplayed();
    }
}
