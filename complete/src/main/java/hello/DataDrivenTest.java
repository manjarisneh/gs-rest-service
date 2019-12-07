package hello;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;

public class DataDrivenTest {

    Properties prop= new Properties();
    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis=new FileInputStream("//Users//vishwajeetrana//Documents//gs-rest-service//complete//src//main//java//hello//Prop.properties");
        prop.load(fis);
       // prop.get("HOST");

    }

    @Test
    public void addBooks()
    {
        RestAssured.baseURI="http://216.10.245.166";

        Response res=given().header("Content-Type","application/json").
                body(PayLoad.AddBook()).
                when().post("/Library/Addbook.php").then().assertThat().statusCode(200).and().log().all().contentType(ContentType.JSON).extract().response();

        String Str=res.asString();
        JsonPath jp=new JsonPath(Str);
        String S1=jp.get("ID");
        System.out.println("ID is ------------------++++++++++++++++++>>>>>>>>>>>>>> "+ S1);
    }
}
