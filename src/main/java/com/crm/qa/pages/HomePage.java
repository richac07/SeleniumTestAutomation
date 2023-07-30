package com.crm.qa.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class HomePage extends TestBase {

    @FindBy(xpath = "//span[@class='title']")
    @CacheLookup
    WebElement titleValue;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    WebElement cart;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    WebElement options;

    @FindBy(xpath = "//a[@id='about_sidebar_link']")
    WebElement about;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    WebElement cartValue;

    //Init page object

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    public String verifyHomePageTitle(){
        return driver.getTitle();

    }

    public AboutPage goToAbout() {

        options.click();
        about.click();
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        return new AboutPage();

    }

    public String addProductToCart(String prodName){
        driver.findElement(By.xpath("//button[@id='add-to-cart-"+prodName+"']")).click();
        String cartVal= cartValue.getText();
        driver.findElement(By.xpath("//button[@id='remove-"+prodName+"']")).click();
        return cartVal;
    }


    public CartPage gotoCart(){
        cart.click();
        return new CartPage();
    }


    //For the mouse hover
//        Actions actions = new Actions(driver);
//        actions.moveToElement(cart).build().perform();
}
