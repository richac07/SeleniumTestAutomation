package com.swaglab.qa.pages;

import com.swaglab.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Updated by Richa
public class LoginPage extends TestBase {

    //Page Factory - OR:
    @FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(xpath = "//*[@id='login-button']")
    WebElement loginButton;

    //Initialization of the page objects:
    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    //Actions:
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }

    public boolean validateLoginPageHeader(){
        return loginButton.isDisplayed();
    }

    public HomePage login(String un, String pwd){
        username.sendKeys(un);
        password.sendKeys(pwd);
        loginButton.click();
        return new HomePage();
    }
}
