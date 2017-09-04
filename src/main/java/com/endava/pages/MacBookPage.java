package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MacBookPage {

    @FindBy(xpath = ".//*[@id='product-product']/ul/li[2]/a")
    private WebElement selectedProductName;

    @FindBy(xpath = ".//*[@id='cart']/button")
    private WebElement goToCartButton;

    @FindBy(xpath = ".//*[@id='cart']/ul")
    private WebElement cartWindow;

    @FindBy(xpath = ".//*[@id='cart']/ul/li[1]/table/tbody/tr/td[3]")
    private WebElement cartWindowQuantity;

    @FindBy(xpath = ".//*[@id='top-links']/ul/li[4]")
    private WebElement viewCart;

    @FindBy(xpath = ".//*[@id='content']/form/div/table/tbody/tr/td[4]/div/input")
    private WebElement updatedQuantity;

    @FindBy(xpath = ".//*[@id='button-cart']")
    private WebElement addToCartButton;

    WebDriver webDriver;

    public MacBookPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String GetProductName(){
        return selectedProductName.getText();
    }

    public void AddProductToCart(){
        addToCartButton.click();
    }

    public String getFirstQuantity(){
        Integer cartWQ = 0;
        addToCartButton.click();
        goToCartButton.click();
        if(cartWindow.isDisplayed()){
            cartWQ = Integer.parseInt(cartWindowQuantity.getText().substring(2)) + 1;
        }
        return cartWQ.toString();
    }

    public String getFinalQuantity(){
        goToCartButton.click();
        viewCart.click();
        return updatedQuantity.getAttribute("value");
    }

}
