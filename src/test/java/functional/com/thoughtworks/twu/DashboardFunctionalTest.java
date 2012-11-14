package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class DashboardFunctionalTest {
    private WebDriver webDriver;

    @Before
    public void setUp() {

        webDriver = new FirefoxDriver();
        webDriver.get("http://localhost:9130/IOU/login");


        webDriver.findElement(By.id("email")).sendKeys("sajacobs@thoughtworks.com");
        webDriver.findElement(By.id("password")).sendKeys("1234");

        webDriver.findElement(By.id("loginForm")).submit();

    }

    @Test
    public void shouldRedirectToBillPageOnAddBillClick() {
        webDriver.get("http://localhost:9130/IOU/dashboard");
        WebElement addBillButton = webDriver.findElement(By.name("addBill"));
        addBillButton.click();
        assertThat(webDriver.getCurrentUrl(),is("http://localhost:9130/IOU/add-bill"));
    }

    @Test
    public void shouldListThePeopleWhoOweMe() throws Exception {
        webDriver.get("http://localhost:9130/IOU/dashboard");
        WebElement addBillButton = webDriver.findElement(By.name("addBill"));
        addBillButton.click();
        assertThat(webDriver.getCurrentUrl(),is("http://localhost:9130/IOU/add-bill"));

        webDriver.findElement(By.name("descriptionItem")).sendKeys("breakfast");
        webDriver.findElement(By.name("amountItem")).sendKeys("100");

        webDriver.findElement(By.name("people")).sendKeys("Renu");
        WebElement saveBillButton = webDriver.findElement(By.id("saveBillButton"));
        saveBillButton.click();

        assertThat(webDriver.getCurrentUrl(),is("http://localhost:9130/IOU/add-bill"));
        WebElement goToDashboardButton = webDriver.findElement(By.id("backToDashboardButton"));
        goToDashboardButton.click();
        assertThat(webDriver.getCurrentUrl(),is("http://localhost:9130/IOU/dashboard"));

       WebElement peopleWhoOwnMe = webDriver.findElement(By.id("peopleWhoOweMeList"));
        assertThat(peopleWhoOwnMe.findElement(By.id("friends")).getText(), is("Renu"));

    }

    @Test
    public void shouldRedirectToLoginPageAfterLogout() {
        webDriver.findElement(By.name("logoutButton")).click();
        assertThat(webDriver.getCurrentUrl(), containsString("http://localhost:9130/IOU/login"));
    }

    @After
    public void tearDown() {
       // webDriver.close();
    }

}
