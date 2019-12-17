package hello;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.groovy.json.internal.Chr;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.xpath.XPath;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class Oauth2 {

    @Test
    public void accesscode() throws InterruptedException {


        System.setProperty("webdriver.chrome.driver","//Users//vishwajeetrana//Documents//webdriver");
        WebDriver driver=new ChromeDriver() ;

        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
        driver.findElement(By.cssSelector("[type='email']")).sendKeys("dfsf");
        driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[type='password']")).sendKeys("vxcvd");
        driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        String url=driver.getCurrentUrl();
        System.out.println(url);
        String partialcode=url.split("code=")[1];
        String code=partialcode.split("&scope")[0];
        System.out.println(code);

        //----------------------------------

        String tokenResponse = given().queryParams("code", code).
                queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
                queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
                queryParams("grant_type", "authorization_code").
                when().log().all().
                post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(tokenResponse);
        String accessToken = js.getString("access_token");


        //--------------------------------------
        String Response = given().queryParam("access_token", "accessToken").
                when().
                get("https://rahulshettyacademy.com/getCourse.php").asString();


    }
}
