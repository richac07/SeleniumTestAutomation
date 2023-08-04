package com.swaglab.qa.testcases;

import com.swaglab.qa.base.TestBase;
import com.swaglab.qa.pages.AboutPage;
import com.swaglab.qa.pages.CartPage;
import com.swaglab.qa.pages.HomePage;
import com.swaglab.qa.pages.LoginPage;
import com.swaglab.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;
    AboutPage aboutPage;
    CartPage cartPage;

    String sheetName ="ProductName";

    public HomePageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test (priority = 1)
    public void verifyHomePageTitleTest(){
       String homePageTitle= homePage.verifyHomePageTitle();
        Assert.assertEquals(homePageTitle,"Swag Labs","Home Page Title Not matched");
    }

    @Test(priority = 2)
    public void verifyAboutPage() {
        aboutPage= homePage.goToAbout();

    }

    @Test(priority = 3, dependsOnMethods = "verifyAboutPage")

    public void verifyCartPage(){
        cartPage=homePage.gotoCart();
    }

    @DataProvider
    public Object[][] getTestData(){
        Object data[][]=TestUtil.getTestData(sheetName);
        return data;
    }

    @Test(priority = 4, dataProvider = "getTestData")
    public void verifyAddingNewProduct(String name){
        String cartVal=homePage.addProductToCart(name);
        Assert.assertEquals(cartVal,"1");
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
