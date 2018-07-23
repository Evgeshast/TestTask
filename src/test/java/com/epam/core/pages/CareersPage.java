package com.epam.core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CareersPage {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@class=\"job-search__input job-search__input--js-autocomplete\"]")
    private WebElement keyWordsField;

    @FindBy(how = How.CLASS_NAME, using = "arrow")
    private WebElement locationsDropdown;

    @FindBy(how = How.XPATH, using = "//li[text() = 'cityToChoose']")
    private WebElement cityToChoose;

    @FindBy(how = How.CLASS_NAME, using = "selected-params")
    private WebElement filterByParams;

    @FindBy(how = How.CLASS_NAME, using = "job-search__submit")
    private WebElement searchButton;

    @FindBy(how = How.CLASS_NAME, using = "job-search__submit")
    private WebElement resultsDescription;

    public CareersPage(WebDriver webDriverInstance){
        this.driver = webDriverInstance;
        PageFactory.initElements(driver, this );
    }

    public  ArrayList <String> search(String career, String cityName, String filterName){
        keyWordsField.sendKeys(career);
        locationsDropdown.click();
        getCityFromDropdown(cityName).click();
        filterByParams.click();
        getFilterFromDropdown(filterName).click();
        searchButton.click();
        return getVacanciesDescription();
    }

    private WebElement getCityFromDropdown(String cityName){
        return driver.findElement(By.xpath("//li[text() = '" + cityName + "']"));
    }

    private WebElement getFilterFromDropdown(String filterName){
        return driver.findElement(By.xpath("//div/ul[2]/li[2]/label/span"));
    }

    private ArrayList <String> getVacanciesDescription(){
        ArrayList <String> vacanciesDescription = new ArrayList<String>();
        List<WebElement> listOfVacancies = driver.findElements(By.className("search-result__item-description"));
        for (WebElement vacancy : listOfVacancies) {
            vacanciesDescription.add(vacancy.getText());
        }
        return vacanciesDescription;
    }
}
