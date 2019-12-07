package hello;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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



public class TestJira {


    Properties prop=new Properties();
//    @BeforeTest
//    public void getData() throws IOException {
//
//        FileInputStream fis=new FileInputStream("//Users//vishwajeetrana//Documents//gs-rest-service//complete//src//main//java//hello//Prop.properties");
//        prop.load(fis);
//
//       // prop.get("HOST");
//    }

    @Test
    public  static String JiraApi()
    {

     RestAssured.baseURI="http://localhost:8080";
        Response res=  given().header("Content-Type","application/json").
        body("{ \"username\": \"manjarisneh\", \"password\": \"sneh1234#\" }").
        when().
        post("rest/auth/1/session").then().
        statusCode(200).extract().response();

        String rString=res.asString();

        JsonPath js=new JsonPath(rString);
        String session=js.get("session.value");

        System.out.println("session is----------------" + session);

        return session;
    }
}
