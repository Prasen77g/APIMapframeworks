package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.googleMapplace;
import pojo.locationAPI;

public class TestDataBuild {
	
	public googleMapplace addPlacePayload(String name, String language, String address) {
		
		googleMapplace m = new googleMapplace();
		m.setAccuracy(50);
		m.setAddress(address);
		m.setLanguage(language);
		m.setName(name);
		m.setPhone_number("(+91) 983 893 3937");
		m.setWebsite("http://google.com");
		
		List<String> myLists = new ArrayList<String>();
		myLists.add("shoe park");
		myLists.add("shop");
		m.setTypes(myLists);
		
		locationAPI l = new locationAPI();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		m.setLocation(l);
		return m;
	}
	
	public String deletePlacePayload(String placeid)
	{
		
		return "{\"place_id\":\""+placeid+"\"}";
		
		
	}

}
