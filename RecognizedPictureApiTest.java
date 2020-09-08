package ApiFunctionalityTests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.json.*;

public class RecognizedPictureApiTest {

	final String jsonMock = "{\r\n" + 
			"  \"responses\": [\r\n" + 
			"    {\r\n" + 
			"      \"landmarkAnnotations\": [\r\n" + 
			"        {\r\n" + 
			"          \"mid\": \"/m/014lft\",\r\n" + 
			"          \"description\": \"Saint Basil's Cathedral\",\r\n" + 
			"          \"score\": 0.7840959,\r\n" + 
			"          \"boundingPoly\": {\r\n" + 
			"            \"vertices\": [\r\n" + 
			"              {\r\n" + 
			"                \"x\": 812,\r\n" + 
			"                \"y\": 1058\r\n" + 
			"              },\r\n" + 
			"              {\r\n" + 
			"                \"x\": 2389,\r\n" + 
			"                \"y\": 1058\r\n" + 
			"              },\r\n" + 
			"              {\r\n" + 
			"                \"x\": 2389,\r\n" + 
			"                \"y\": 3052\r\n" + 
			"              },\r\n" + 
			"              {\r\n" + 
			"                \"x\": 812,\r\n" + 
			"                \"y\": 3052\r\n" + 
			"              }\r\n" + 
			"            ]\r\n" + 
			"          },\r\n" + 
			"          \"locations\": [\r\n" + 
			"            {\r\n" + 
			"              \"latLng\": {\r\n" + 
			"                \"latitude\": 55.752912,\r\n" + 
			"                \"longitude\": 37.622315883636475\r\n" + 
			"              }\r\n" + 
			"            }\r\n" + 
			"          ]\r\n" + 
			"        }\r\n" + 
			"      ]\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}";
	
	@Test
	public void isDataOfRecognizedPictuerValideTest() {
		JSONObject responseJSON = new JSONObject(jsonMock);
		
		double latitude = 0;
		try {
			latitude = ((JSONObject) ((JSONObject) ((JSONObject) responseJSON.getJSONArray("responses").get(0)).getJSONArray("landmarkAnnotations").get(0)).getJSONArray("locations").get(0)).getJSONObject("latLng").getDouble("latitude");
		}
		catch (Exception e) {
			fail("Invalid data from API");
		}
		
		double longitude = 0;
		try {
			longitude = ((JSONObject) ((JSONObject) ((JSONObject) responseJSON.getJSONArray("responses").get(0)).getJSONArray("landmarkAnnotations").get(0)).getJSONArray("locations").get(0)).getJSONObject("latLng").getDouble("longitude");
		}
		catch (Exception e) {
			fail("Invalid data from API");
		}
		
	}

}
