package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//execute this code only when place id is null
	    //write a code that will give you place id
		stepDefination sd = new stepDefination();
		if(stepDefination.placeid==null)
		{
		sd.add_place_payload_with("Shetty", "French", "Asian");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}

}
