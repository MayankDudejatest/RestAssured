 package StepDefination;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class stepDefination {
	
	RequestSpecification res;
	ResponseSpecification respec;
	RequestSpecification req;
	Response response;
	
	@Given("AddPlace API")
	public void add_place_api() {
	    RestAssured.baseURI = "https://rahulshettyacademy.com";
	    AddPlace a = new AddPlace();
	    a.setAccuracy(30);
	    a.setAddress("123");
	    a.setLanguage("eng");
	    a.setName("Mayank");
	    a.setPhone_number("1234");
	    List<String>  l = new ArrayList<String>();
	    l.add("first");
	    l.add("second");
	    a.setTypes(l);
	   
	    Location l1 = new Location(); 
	    l1.setLat(-38.383494);
	    l1.setLng(33.427362);
	    a.setLocation(l1);
	    a.setWebsite("www.test.com");
	   
	     req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	     respec = new ResponseSpecBuilder().expectStatusCode(200).build();
	     res = given().spec(req).body(a);
	    						
	}
	@When("user calls {string} with post API")
	public void user_calls_with_post_api(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   response = res.when().post("maps/api/place/add/json")
	    .then().spec(respec).extract().response();
	}
	@Then("APIclass throws message")
	public void ap_iclass_throws_message() {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(response.getStatusCode(),200); 
	   System.out.println(response.getStatusCode());
	}
	@Then("{string} is {string}")
	public void is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	   String r4 = response.asString();
	   System.out.println(r4);
	 
	   JsonPath js = new JsonPath(r4);
	   
	 
	   assertEquals(js.get(key), value);
	}
	


}
