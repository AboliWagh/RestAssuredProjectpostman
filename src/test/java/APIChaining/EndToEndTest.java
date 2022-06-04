package APIChaining;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	Response response;
	String BaseURI = "http://localhost:3000/employees";

	@Test
	public void test1() {
		// GET Method
		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);

		// POST Method
		response = PostMethod("Madhu", "5200");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath Jpath = response.jsonPath();
		int EmpId = Jpath.get("id");
		System.out.println("Emp id" + EmpId);

		// PUT Method
		response = PutMethod(EmpId, "Tishya", "3300");
		Assert.assertEquals(response.getStatusCode(), 200);
		Jpath = response.jsonPath();
		Assert.assertEquals(Jpath.get("name"), "Tishya");
		
		//DELETE Method 
	
		response = DeleteMethod(EmpId);
		Assert.assertEquals(response.getStatusCode(), 200);

		// NEW GET Method
		response = GetMethod(EmpId);
		Assert.assertEquals(response.getStatusCode(), 404);
		
	}

	//// All the Methods for API Chaining ////

	public Response GetMethodAll() {

		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();

		Response response = request.get();
		// String Responsebody = response.getBody().asString();
		// System.out.println(Responsebody);
		return response;

	}

	public Response PostMethod(String Name, String Salary) {

		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("Salary", Salary);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.post("/create");

		// Assert.assertEquals(response.getBody().asString(),"Radha2");

		return response;
	}

	public Response PutMethod(int EmpId, String Name, String Salary) {

		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();
		jobj.put("name", Name);
		jobj.put("Salary", Salary);

		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.put("/" + EmpId);

		return response;
	}

	public Response DeleteMethod(int EmpId) {
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();

		Response response = request.delete("/" + EmpId);
		
		return response;
		
	}
	
	public Response GetMethod(int EmpId) {

		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();

		Response response = request.get("/" + EmpId);
		
		return response;
}
}
