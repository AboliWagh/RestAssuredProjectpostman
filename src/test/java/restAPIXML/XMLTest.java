package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test
	public void test1() {
		RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get().then().log().body()
				.statusCode(200);
	}

	@Test
	public void test2() {
		Response response = RestAssured.given().baseUri("https://chercher.tech/sample/api/books.xml").when().get();

		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		// 1 Method --- to get all books title
		for (String i : books) {
			System.out.println(i);
		}
		// 2 Method -- to get all books title
		for (String b : books) {
			System.out.println(b.toString());
		}

		// 3 Method -- to get all books title
		for (int index = 0; index < books.size(); index++) {
			System.out.println("book is " + books.get(index).toString());

		}
		// System.out.println("All the books are " + books.toString());
		// System.out.println("First Book is " + books.get(0).toString());
		// System.out.println("First Book is " + books.get(i).toString());
		// System.out.println("Language of First book is " +
		// books.get(0).getAttribute("lang"));

		NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
		// System.out.println("All the prices are " + prices.toString());

		System.out.println("First price is " + prices.get(0).children().get("paperback"));
		System.out.println("another  price of First book  is " + prices.get(0).children().get("hardcover"));
		
	}

}
