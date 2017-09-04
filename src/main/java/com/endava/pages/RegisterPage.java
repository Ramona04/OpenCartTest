package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class RegisterPage {

    @FindBy(xpath = ".//*[@id='content']/h1")
    private WebElement pageTitle;

    @FindBy(id = "input-firstname")
    private WebElement firstName;

    @FindBy(id = "input-lastname")
    private WebElement lastName;

    @FindBy(id = "input-email")
    private WebElement emailAddress;

    @FindBy(id = "input-telephone")
    private WebElement telephone;

    @FindBy(id = "input-password")
    private WebElement password;

    @FindBy(id = "input-confirm")
    private WebElement confirmPassword;

    @FindBy(xpath = ".//*[@id='content']/form/fieldset[3]/div/div/label")
    private List<WebElement> subscribeToNewsletter;

    @FindBy(css = ".pull-right input[type = 'checkbox']")
    private WebElement privacyPolicyCheckBox;

    @FindBy(css = ".pull-right input[type = 'submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//*[@id='content']/div/div/a")
    private WebElement finalContinueButton;

    @FindBy(xpath = ".//*[@id='content']/ul[1]/li[1]/a")
    private WebElement editAccount;

    private WebDriver webDriver;

    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String CheckPageTitle() {
        return pageTitle.getText();
    }

    public void CompleteRegisterForm(String subscribeAns) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("C:\\Users\\rciuciuc\\Desktop\\Homework and Materials\\Open_Cart_ Test\\OpenCartTest\\src\\main\\resources\\config.properties");
            properties.load(input);

            firstName.sendKeys( properties.getProperty("firstName"));
            lastName.sendKeys(properties.getProperty("lastName"));
            emailAddress.sendKeys( properties.getProperty("emailAddress"));
            telephone.sendKeys( properties.getProperty("telephone"));
            password.sendKeys( properties.getProperty("password"));
            confirmPassword.sendKeys( properties.getProperty("password"));
        }catch (IOException ex){
            ex.printStackTrace();
        }

        for (int i = 0; i < subscribeToNewsletter.size(); i++) {
            if (subscribeToNewsletter.get(i).getText().equals(subscribeAns)) {
                subscribeToNewsletter.get(i).click();
            }
        }

        privacyPolicyCheckBox.click();
        continueButton.click();
        finalContinueButton.click();
        editAccount.click();
    }

    public String CheckUser() {
        String username = firstName.getAttribute("value") + " " + lastName.getAttribute("value");
        String mail = emailAddress.getAttribute("value");
        String phoneNo = telephone.getAttribute("value");
        return username + " " + mail + " " + phoneNo;
    }

    public void waitUntilCompleteLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }
}
