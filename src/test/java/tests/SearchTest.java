package tests;

import com.epam.core.drivers.Driver;
import com.epam.core.pages.CareersPage;
import com.epam.core.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchTest {

    private static final String TEST_URL = "https://careers.epam.by/";

    private CareersPage careerPage;

    @BeforeClass
    public void beforeClass() {
        Driver.getWebDriverInstance().get(TEST_URL);
        Driver.getWebDriverInstance().manage().window().maximize();
        HomePage homePage = new HomePage(Driver.getWebDriverInstance());
        careerPage = homePage.goToCareersPage();
    }

    @Test(dataProvider = "searchDataProvider")
    public void searchTest(String career, String city, String filter) {
        ArrayList<String> result = careerPage.search(career, city, filter);
        Assert.assertEquals(result.size(), 8);
        for(String vacancy : result){
                System.out.println(vacancy);
        }
    }

    @AfterClass
    public void afterClass() {
       Driver.closeDriver();
    }

    @DataProvider(name = "searchDataProvider")
    public  Object[][] dataProvider(){
        return new Object[][] { new Object[] {"Java Developer", "Минск", "Разработка"}};
    }
}
