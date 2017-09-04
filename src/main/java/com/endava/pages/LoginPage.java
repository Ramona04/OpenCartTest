package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class LoginPage {
    @FindBy(xpath = ".//*[@id='content']/div/div[2]/div/h2")
    private WebElement pageTitle;

    @FindBy(id = "input-email")
    private WebElement userEmail;

    @FindBy(id = "input-password")
    private WebElement userPassword;

    @FindBy(xpath = "//*[@id='content']/div/div[2]/div/form/input")
    private WebElement loginButton;

    @FindBy(xpath = ".//*[@id='content']/h2")
    private List<WebElement> loggedUserOptions;

    @FindBy(css = ".fa.fa-home")
    private WebElement homeButton;

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void SendCredentials() {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("C:\\Users\\rciuciuc\\Desktop\\Homework and Materials\\Open_Cart_ Test\\OpenCartTest\\src\\main\\resources\\config.properties");
            properties.load(input);

            userEmail.sendKeys( properties.getProperty("userEmail"));
            userPassword.sendKeys(properties.getProperty("userPassword"));

        }catch (IOException ex){
            ex.printStackTrace();
        }

        loginButton.click();
    }

    public Boolean checkIfLoggedIn() {
        Boolean options = false;
        for(int i = 0; i < loggedUserOptions.size(); i++){
            if(loggedUserOptions.get(i).isDisplayed()){
                options = true;
            }
        }
        return options;
    }
    public LoggedInUserPage GoToAddressBookPage(){
        LoggedInUserPage addressBookPage = PageFactory.initElements(webDriver, LoggedInUserPage.class);
        addressBookPage.waitUntilCompleteLoad();
        return addressBookPage;
    }

    public ProductsPage GoToProductsPage(){
        homeButton.click();
        ProductsPage productsPage = PageFactory.initElements(webDriver, ProductsPage.class);
        return productsPage;
    }

    public void waitUntilCompleteLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }
}
