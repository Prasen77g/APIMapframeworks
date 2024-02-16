package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	String resp;
	static String placeid;
	JsonPath js;
	
	TestDataBuild data = new TestDataBuild();	
	Utils ul = new Utils();
	
	//given(): Prepares the request.
	//when(): Specifies the HTTP method and endpoint.
	//then(): Contains assertions to validate the response.
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String string, String string2, String string3) throws IOException {

		
		res =given().log().all()
				.spec(requestSpecification())
		.body(data.addPlacePayload(string,string2,string3));
		
		//System.out.println(res);
	    
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method){
	   //Constructor will be called with value of resource which i pass from enum class:-
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		//Response Spec Builder:- 
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
			 response =res.when().post(resourceAPI.getResource());
			else if(method.equalsIgnoreCase("GET"))
				 response =res.when().get(resourceAPI.getResource());
		/*response = res.when().post(resourceAPI.getResource())
				.then().log().all().spec(resspec)
				.extract().response();*/
		
		//response.prettyPrint();
		
		//JsonPath extractor = response.jsonPath();
		//extractor.get()
		
		resp = response.asString();
		
		System.out.println(resp);
		
		
	}
	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1){
		
		
		 assertEquals(response.getStatusCode(),200);
		
	   
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue){
		
		js = new JsonPath(resp);
		
		assertEquals(getJsonPath(response, KeyValue),ExpectedValue);
	}
	
	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException{
	    
		//prepare request spec:-
		placeid=getJsonPath(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",placeid);
		 user_calls_with_http_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expectedName);
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    
		res = given().spec(requestSpecification())
		.body(data.deletePlacePayload(placeid));
	}

}
