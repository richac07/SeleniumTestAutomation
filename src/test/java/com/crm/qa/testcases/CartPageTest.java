package com.crm.qa.testcases;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CartPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    public CartPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage= new LoginPage();
        homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password") );
        cartPage=homePage.gotoCart();
    }

    @Test
    public void verifyCheckoutBtnTest(){
        Assert.assertTrue(cartPage.verifyCheckoutBtn(),"Checkout Button is missing");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
