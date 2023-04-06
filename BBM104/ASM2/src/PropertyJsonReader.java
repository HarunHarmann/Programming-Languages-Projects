import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;




public class PropertyJsonReader {
     
	 private ArrayList<String> lands = new ArrayList<>();
	 private ArrayList<String> railRoads = new ArrayList<>();
	 private ArrayList<String> companies = new ArrayList<>();
     public PropertyJsonReader(){
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("property.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");
             
            
             for(Object i:Land){
				 
				 //You can reach items by using statements below:
				 lands.add((String)((JSONObject)i).get("id"));
				 lands.add((String)((JSONObject)i).get("name"));
				 lands.add((String)((JSONObject)i).get("cost"));
				 //And you can add these items to any data structure (e.g. array, linkedlist etc.
				 
				 
				 
             }
             
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
				 //You can reach items by using statements below:
            	 railRoads.add((String)((JSONObject)i).get("id"));
            	 railRoads.add((String)((JSONObject)i).get("name"));
            	 railRoads.add((String)((JSONObject)i).get("cost"));
				//And you can add these items to any data structure (e.g. array, linkedlist etc.
             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){
				 //You can reach items by using statements below:
            	 companies.add((String)((JSONObject)i).get("id"));
				 companies.add((String)((JSONObject)i).get("name"));
				 companies.add((String)((JSONObject)i).get("cost"));
             }
             
             
             
         } catch (IOException e){
             e.printStackTrace();
         } catch (ParseException e){
             e.printStackTrace();
         }
     }
     
     public ArrayList<String> getLands(){
    	 return lands;
     }
     public ArrayList<String> getRailRoads(){
    	 return railRoads;
     }
     public ArrayList<String> getCompanies(){
    	 return companies;
     }

	
}