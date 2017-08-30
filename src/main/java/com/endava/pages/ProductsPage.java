package com.endava.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class ProductsPage {
    @FindBy(css = ".caption h4 a")
    private List<WebElement> productList;

    private WebDriver webDriver;

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void SelectProduct(){
        Random random = new Random();
        productList.get(random.nextInt(3 - 0 + 1) + 0).click();
//        if(selectedProductName.getText().equals("MacBook")){
//            System.out.println("MacBook");
//        }else if(selectedProductName.getText().equals("iPhone")){
//            System.out.println("iPhone");
//        }else if(selectedProductName.getText().equals("Apple Cinema 30\"")){
//            System.out.println("Apple Cinema 30\"");
//        }else{
//            System.out.println("Canon EOS 5D");
//        }
    }

    public iPhonePage GoToiPhonePage(){
        productList.get(1).click();
        iPhonePage iphonePage = PageFactory.initElements(webDriver, iPhonePage.class);
        return iphonePage;
    }

    public MacBookPage GoToMacBookPage(){
        productList.get(0).click();
        MacBookPage macBookPage = PageFactory.initElements(webDriver, MacBookPage.class);
        return macBookPage;
    }


    public void waitUntilCompleteLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
    }
}
