package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {
	
	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		Map<String, Object> MapObj = new HashMap<String, Object>();
		
		MapObj.put("name", "Alex");
		MapObj.put("Salary", "6500");

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(MapObj)
				.put("/26");

		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);

		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		System.out.println("Status Code is --- " + ResponseCode);

		JsonPath Jpath = response.jsonPath();
		Jpath.get("id");
		System.out.println("Emp id" + Jpath.get("id"));
	}

}
