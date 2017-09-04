package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    @FindBy(xpath = ".//*[@id='top-links']/ul/li[2]")
    private WebElement myAccount;

    @FindBy(css = ".dropdown a[title='My Account']")
    private WebElement myAccountDropDown;

    @FindBy(css = ".list-inline .dropdown .dropdown-menu li a")
    private List<WebElement> myAccountOptions;

    @FindBy(xpath = ".//*[@id='column-right']/div/a[13]")
    private WebElement logoutButton;

    private WebDriver webDriver;

    public MainPage(WebDriver driver) {
        this.webDriver = driver;
    }

    public RegisterPage Register(String accountOption) {
        myAccount.click();
        if (myAccountDropDown.isDisplayed()) {
            for (int i = 0; i < myAccountOptions.size(); i++) {
                if (myAccountOptions.get(i).getText().equals("Register")) {
                    myAccountOptions.get(i).click();
                }
            }
        }
        RegisterPage registerPage = PageFactory.initElements(webDriver, RegisterPage.class);
        registerPage.waitUntilCompleteLoad();
        return registerPage;
    }

    public LoginPage Login(String accountOption) {
        myAccount.click();
        if (myAccountDropDown.isDisplayed()) {
            for (int i = 0; i < myAccountOptions.size(); i++) {
                if (myAccountOptions.get(i).getText().equals("Login")) {
                    myAccountOptions.get(i).click();
                }
            }
        }
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.waitUntilCompleteLoad();
        return loginPage;
    }

    public void Logout() {
        myAccount.click();
        for (int i = 0; i < myAccountOptions.size(); i++) {
            if (myAccountOptions.get(i).getText().equals("Logout")) {
                myAccountOptions.get(i).click();
            }
        }
    }

}
