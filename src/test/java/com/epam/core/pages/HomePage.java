package com.epam.core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//a[@href='/careers']")
    private WebElement CareersButton;

    public HomePage(WebDriver webDriverInstance){
        this.driver = webDriverInstance;
        PageFactory.initElements(driver, this );
    }

    public CareersPage goToCareersPage(){
        CareersButton.click();
        return new CareersPage(driver);
    }

}
