package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class LoggedInUserPage {
    @FindBy(xpath = "//*[@id='content']/ul[1]/li[3]/a")
    private WebElement addressBook;

    @FindBy(xpath = "//*[@id='content']/div[1]/table/tbody/tr/td[1]")
    private WebElement addressBookEntries;

    private WebDriver webDriver;

    public LoggedInUserPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void AccessAddressBook() {
        addressBook.click();
    }

    public String CheckInsertedAddress() {
        return addressBookEntries.getText();
    }

    public void waitUntilCompleteLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOf(addressBook));
    }
}
