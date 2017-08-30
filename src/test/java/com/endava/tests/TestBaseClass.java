package com.endava.tests;

import com.endava.pages.LoggedInUserPage;
import com.endava.pages.LoginPage;
import com.endava.pages.MainPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class TestBaseClass {
    protected static MainPage mainPage;
    protected static LoginPage loginPage;
    public static LoggedInUserPage loggedInUserPage;
    private static WebDriver webDriver;

    @BeforeClass
    public static void before() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/rciuciuc/AppData/Roaming/Skype/My Skype Received Files/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("http://192.168.164.15");
        mainPage = PageFactory.initElements(webDriver, MainPage.class);
    }

    @AfterClass
    public static void after() {
        //mainPage.Logout();
        //webDriver.close();
    }
}
