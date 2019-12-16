package hello;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
    public void accesscode() {


        System.setProperty("webdriver.chrome.driver","//Users//vishwajeetrana//Documents//webdriver");

        //----------------------------------

        String tokenResponse = given().queryParams("code", "").
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
