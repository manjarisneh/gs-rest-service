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

public class CreateJira {

@Test
public void JiraApi()
{
    RestAssured.baseURI="http://localhost:8080";

    Response res=given().header("Content-Type","application/json").
            header("Cookie","JSESSIONID=" + TestJira.JiraApi()+ "").
            body("{\n" +
                    "    \"fields\": {\n" +
                    "       \"project\":\n" +
                    "       {\n" +
                    "          \"key\": \"RES\"\n" +
                    "       },\n" +
                    "       \"summary\": \"MID of the spawning payment generated id blank \",\n" +
                    "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                    "       \"issuetype\": {\n" +
                    "          \"name\": \"Bug\"\n" +
                    "       }\n" +
                    "   }\n" +
                    "}").when().post("rest/api/2/issue").then().statusCode(201).extract().response();

    String S1=res.asString();
    System.out.println("Response in JSON is------------------------>>>>>>>>>>"+ S1);
    JsonPath jp=new JsonPath(S1);

    String id=jp.get("id");
    System.out.println("ID is----------------"+ id);


}

@AfterTest
public void commentOnJira()
{
    RestAssured.baseURI="http://localhost:8080";

    given().header("Content-Type","application/json").
            header("Cookie","JSESSIONID=" + TestJira.JiraApi()+ "").
            body("{\n" +
                    "   \"update\": {\n" +
                    "      \"comment\": [\n" +
                    "         {\n" +
                    "            \"add\": {\n" +
                    "               \"body\": \"Please provide the screenshots & the payment details logs\"\n" +
                    "            }\n" +
                    "         }\n" +
                    "      ]\n" +
                    "   }\n" +
                    "}").when().put("rest/api/2/issue/10103").then().statusCode(204).extract().response();

}

}


