package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConnectionsFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }

    @Test
    public void shouldDirectToFriendsListPageAndAddAFriendToTheList() throws Exception {
        webDriver.get("http://localhost:9130/IOU/login");

        webDriver.findElement(By.id("email")).sendKeys("sajacobs@thoughtworks.com");
        webDriver.findElement(By.id("password")).sendKeys("1234");

        webDriver.findElement(By.id("loginForm")).submit();

        webDriver.findElement(By.id("friendsListButton")).click();
        String currentUrl = webDriver.getCurrentUrl();

        assertThat(currentUrl, is("http://localhost:9130/IOU/my-friends-list"));

        final String addedEmail = "abc@gmail.com";

        webDriver.findElement(By.id("newEmail")).sendKeys(addedEmail);
        webDriver.findElement(By.id("addToListButton")).click();



        WebElement friendsTable = webDriver.findElement(By.id("emailList"));
        assertThat(friendsTable.findElement(By.id("header")).getText(), is("Friends Added"));
        assertThat(friendsTable.findElement(By.id("baseRow")).getText(), is("abc@gmail.com"));


        webDriver.findElement(By.id("saveButton")).click();

        friendsTable = webDriver.findElement(By.id("emailList"));
        assertThat(friendsTable.getText().trim(), is("Friends Added"));


    }

    @After
    public void tearDown(){
        webDriver.close();
    }
}
