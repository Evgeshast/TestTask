package tests;

import com.epam.core.drivers.Driver;
import com.epam.core.pages.CareersPage;
import com.epam.core.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SearchTest {

    private String TEST_URL = "https://careers.epam.by/";
    CareersPage careerPage;

    @BeforeClass
    public void prepare() {
        Driver.getWebDriverInstance().get(TEST_URL);
        Driver.getWebDriverInstance().manage().window().maximize();
        HomePage homePage = new HomePage(Driver.getWebDriverInstance());
        careerPage = homePage.goToCareersPage();
    }

    @Test
    public void searchTest() {
        ArrayList<String> result = careerPage.search("Java Developer","Минск", "Разработка");
        Assert.assertEquals(result.size(), 8);
        for(String vacancy : result){
                System.out.println(vacancy);
        }
    }

    @AfterClass
    public void afterClass() {
       Driver.closeDriver();
    }

}
