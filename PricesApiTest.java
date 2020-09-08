package ApiFunctionalityTests;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PricesApiTest {
final int ticketPrice = 6200;
	
	@Test
	public void testAutoCompleteApiFunctionality() {
		
//		Тест проверяет функционирование стороннего API и сравнивает 
//		IATA-код, возвращаемый из API с эталонным кодом
		
		StringBuffer response = new StringBuffer();
		final String city = "Saratov";
		final String referenceCode = "RTW";
		final String referenceCountryName = "Russia";
		try {
			
			
			URL obj = new URL("https://autocomplete.travelpayouts.com/places2?locale=en&types[]=city&term="+city);
	        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

	        //add reuqest header
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("User-Agent", "Mozilla/5.0" );
	        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	        connection.setRequestProperty("Content-Type", "application/json");

	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;

	        while ((inputLine = bufferedReader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        bufferedReader.close();
		}
		catch (Exception e) {
			fail("Failed to connect to the Autocomplete API");
		}
		
		JSONArray responseJSON = new JSONArray(response.toString());
        String code = responseJSON.getJSONObject(0).getString("code");
        String countryName = responseJSON.getJSONObject(0).getString("country_name");
        
        assertEquals(referenceCode, code);
        assertEquals(referenceCountryName, countryName); 
	}

	@Test
	public void testCheapestTicketsApiFunctionality() {
		
//		Тест проверяет функционирование стороннего API и сравнивает цену 
//		за билет, полученную из API, с эталонной ценой
		final String code = "MOW";
		final int referenceTicketPrice = 6590;
		
		String jsonMock = "{\"success\":true,\"data\":{\"MOW\":{\"0\":{\"price\":2998,\"airline\":\"DP\",\"flight_number\":206,\"departure_at\":\"2020-09-10T22:20:00Z\",\"return_at\":\"2020-09-15T12:15:00Z\",\"expires_at\":\"2020-09-10T19:20:00Z\"},\"1\":{\"price\":6590,\"airline\":\"U6\",\"flight_number\":53,\"departure_at\":\"2020-09-10T06:00:00Z\",\"return_at\":\"2020-09-15T17:15:00Z\",\"expires_at\":\"2020-09-10T03:00:00Z\"}}},\"error\":null,\"currency\":\"rub\"}";
		JSONObject responseJSON = new JSONObject(jsonMock);
        
		int ticketPrice = 0;

        try{
            ticketPrice = responseJSON.getJSONObject("data").getJSONObject(code).getJSONObject("1").getInt("price");
        }
        catch (Exception e){
        	fail("Failed to use the CheapestActivity API");
        }
     
        assertEquals(referenceTicketPrice, ticketPrice);
	}
}
