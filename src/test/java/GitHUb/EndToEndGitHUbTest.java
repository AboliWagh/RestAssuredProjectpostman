package GitHUb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndGitHUbTest {
	
	@Test
	public void test1() {
		
		RestAssured.baseURI = "https://api.github.com/users/Aboliwagh/repos";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.get();
		
		String Responsebody = response.getBody().asString();
		System.out.println(Responsebody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		System.out.println("Status Code is --- " + ResponseCode);
		
	}
	@Test
	public void test2() throws IOException {
		RestAssured.baseURI = "https://api.github.com/user/repos";
		byte[] dataBytes = Files.readAllBytes(Paths.get("Githubdata.json"));
		
		RequestSpecification request = RestAssured.given();
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.auth()
				.oauth2("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")
				.body(dataBytes)
				.post();

		String ResponseBody = response.getBody().asString();
		System.out.println(ResponseBody);

		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);
		System.out.println("Status Code is --- " + ResponseCode);
	}
}
