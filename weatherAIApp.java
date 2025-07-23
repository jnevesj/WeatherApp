package weatherAIApp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.JSONObject;





public class weatherAIApp {
	
	private static final String weatherAPIKey = "89094b7c5d81f54c1267eb94f4603ca2";
	public static final String yellowCol = "\u001B[33m";
	public static final String resetCol = "\u001B[0m";
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		String userInput = "n";
		
		
		while(userInput.equalsIgnoreCase("n")) {
			System.out.print("Enter the name of a city: ");
			String city = scanner.nextLine();
			
			
		try {
			//URL
			String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "&appid=" + weatherAPIKey + "&units=metric";
			
			//connection
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			
			//response
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder json = new StringBuilder();
			String line;
			
			
			while((line = in.readLine()) != null) {
				json.append(line);
			}
			
			in.close();
			
			
			//-> to json
			
			JSONObject obj = new JSONObject(json.toString());
			double temp = obj.getJSONObject("main").getDouble("temp");
			String description = obj.getJSONArray("weather").getJSONObject(0).getString("description");
			
			
			//result
			System.out.println("Current Weather in: " + city + ":");
			System.out.println("Temperature: " + temp + "Cº");
			System.out.println("Description: " + description);
			
			
			
			//recommendations
			if(temp < 15 && temp > 0) {
			System.out.println(yellowCol +"Best to get warm"  + resetCol);
		} else if( temp > 15 && temp < 25 ) {
			System.out.println(yellowCol + "Best to get some sport in you!" + resetCol);
		}else if( temp > 25 && temp < 35) {
			System.out.println(yellowCol +"Best to go to the beach!"+ resetCol);
		}else if(temp > 35) {
			System.out.println(yellowCol +"Best to stay out of the sun!"+ resetCol);
		}else if(temp < 15) {
			System.out.println(yellowCol +"Stay inside!"+ resetCol);
		}
		
			
			
			
			
			
		} catch(Exception e){
			System.out.print("Error in: " + e.getMessage());;
		}
		
		
		
		
		
		System.out.println("Press 'n' to check the weather in another city or any other key to exit: ");
		userInput = scanner.nextLine();
		

	}
	
		System.out.println("Good bye! Stay safe");
		scanner.close();
	}		

}
