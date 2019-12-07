package hello;

//import org.testng.IRetryAnalyzer;

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




public class TestMe {

    Properties prop=new Properties();
    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis=new FileInputStream("//Users//vishwajeetrana//Documents//gs-rest-service//complete//src//main//java//hello//Prop.properties");
        prop.load(fis);

        prop.get("HOST");
    }

    @Test
	public void postData()
	{
		RestAssured.baseURI=prop.getProperty("HOST1");
		Response res= given().body("{\n" +
				"\t\"name\": \"qweryottty\",\n" +
				"\t\"job\": \"leader\"\n" +
				"}").when().post("/api/users").then().assertThat().statusCode(201).contentType(ContentType.JSON).and().extract().response();

		String responseString=res.asString();

		System.out.println("Response is:--------------->>>"+responseString);

		JsonPath js=new JsonPath(responseString);
		String CreatedAt=js.get("createdAt");
		System.out.println("Response extracted:--------------->>>"+CreatedAt);

		String ID=js.get("id");
		System.out.println("ID is--------------------->>>>"+ID);


		given().body("{\n" +
                "    \"id\": \""+ID+"\",\n" +
                "}").when().post("api/users/2").then().assertThat().statusCode(201).contentType(ContentType.JSON);

        System.out.println("Response extracted:--------------->>>"+CreatedAt);
        System.out.println("ID is--------------------->>>>"+ID);

	}
	@Test
	public void extractJson()
	{
		RestAssured.baseURI=prop.getProperty("HOST1");
		Response res=given().when().get("api/users?page=2").then().assertThat().statusCode(200).log().all().
				and().contentType(ContentType.JSON).extract().response();

		String S1=res.asString();
		JsonPath jp=new JsonPath(S1);

		int count=jp.get("data.size()");
		System.out.println("Count is---------------------" +count);

		for(int i=0;i<count;i++)
		{
			HashMap<String,String> Str = jp.get("data["+i+"]");

			System.out.println(Str);
		}


	}



}

