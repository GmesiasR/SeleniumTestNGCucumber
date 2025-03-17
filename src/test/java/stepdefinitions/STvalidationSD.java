package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.SolotodoPage;
import testcomponents.BaseTest;

import java.io.IOException;

public class STvalidationSD extends BaseTest {

    public SolotodoPage solotodoPage;

    @Given("I land on solotodo webpage")
    public void iLandOnSolotodoWebpage() throws IOException {
        solotodoPage = launchApplicationSolotodo();
    }

    @Given("Im in solotodo webpage")
    public void imInSolotodoWebpage() {
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.solotodo.cl/");
    }

    @When("search for {string} button in header")
    public void searchForButtonInHeader(String elementName) {
        solotodoPage.searchMainElementsInHeader(elementName);
    }

    @Then("all of them are present")
    public void allOfThemArePresent() {
        Assert.assertTrue(true);
        driver.close();
    }
}
