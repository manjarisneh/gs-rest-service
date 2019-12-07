package hello;

//import org.testng.IRetryAnalyzer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.xpath.XPath;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class TestMeXml {

    Properties prop=new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis=new FileInputStream("//Users//vishwajeetrana//Documents//gs-rest-service//complete//src//main//java//hello//Prop.properties");
        prop.load(fis);

        prop.get("HOST1");
    }

    @Test
	public void postData() throws IOException {
		String postData=GenerateStringFromResource(prop.getProperty("xpath"));
		RestAssured.baseURI=prop.getProperty("HOST1");
		Response res= given().queryParam("key","YOUR_API_KEY").
				body(postData).when().post(prop.getProperty("resource")).then().assertThat().contentType(ContentType.XML).extract().response();



//		String responseString=res.asString();
//
//		System.out.println("Response is:--------------->>>"+responseString);
//
//		JsonPath js=new JsonPath(responseString);
//		String CreatedAt=js.get("createdAt");
//		System.out.println("Response extracted:--------------->>>"+CreatedAt);
//
//		String ID=js.get("id");
//		System.out.println("ID is--------------------->>>>"+ID);
//
//
//		given().body("{\n" +
//                "    \"id\": \""+ID+"\",\n" +
//                "}").when().post("api/users/2").then().assertThat().statusCode(201).contentType(ContentType.JSON);
//
//        System.out.println("Response extracted:--------------->>>"+CreatedAt);
//        System.out.println("ID is--------------------->>>>"+ID);

	}



	public static String GenerateStringFromResource(String path) throws IOException {
    	System.out.println(new String(Files.readAllBytes(Paths.get(path))));
	 return new String(Files.readAllBytes(Paths.get(path)));
	}

}

