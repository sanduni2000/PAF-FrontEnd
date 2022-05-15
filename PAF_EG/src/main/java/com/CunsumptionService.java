package com;
//
//import javax.swing.text.Document;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import org.jsoup.parser.*;
//import org.jsoup.Jsoup;

//
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.protobuf.Parser;
//
import model.Cunsumption;
//import model.GET;
//import model.Path;
//import model.Produces;

// For REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;  


//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Cunsumption") 

public class CunsumptionService {
   
	

	
		
		Cunsumption consumpObj = new Cunsumption(); 
		@GET
		@Path("/readCunsumption") 
		@Produces(MediaType.TEXT_HTML) 
		public String readCunsumption() 
		 { 
		
		   return consumpObj.readCunsumption();
		 } 
		
		@POST
		@Path("/insertConsumption") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String insertconsumption
		(@FormParam("Dname") String Dname, 
		 @FormParam("Month") String Month,
		 @FormParam("Note") String Note) 
		
		//inserting validations
		{
			if(Dname.isEmpty()||Month.isEmpty()||Note.isEmpty())
			{
				 return "All fields must be filled out";
			}
		
			else if(Dname.length()<=4) {
				 return "Dname length must be 4 or more characters long";
			 }
			
			else if(Month.length()<=3) {
				 return "Month length must be 3 or more characters long";
			 }
			
			String output = consumpObj.insertconsumption(Dname,Month,Note);
			return output;
		}
		
		 
		@GET
		@Path("/searchCunsumption")
		@Produces(MediaType.TEXT_HTML)
		public String searchSchedules(String consumptionData) {
		Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser());
		String Dname = doc.select("Dname").text();
		return consumpObj.searchCunsumption(Dname);
		}


		
		@PUT
		@Path("/updateCunsumption") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateCunsumption(String consumptionData) 
		{ 
			
			
		//Convert the input string to a JSON object 
		 JsonObject ConObject1 = new JsonParser().parse(consumptionData).getAsJsonObject(); 
		 
		 
		 
		//Read the values from the JSON object
		 String ID =ConObject1.get("ID").getAsString(); 
		 String Dname = ConObject1.get("Dname").getAsString(); 
//		 String DZipCode = ConObject1.get("DZipCode").getAsString(); 
//		 String DUsedUnits = ConObject1.get("DUsedUnits").getAsString(); 
		 String Month = ConObject1.get("Month").getAsString();
		 String Note = ConObject1.get("Note").getAsString(); 
		 
		 String output = consumpObj.updateCunsumption(ID, Dname,Month, Note); 
		 return output; 
		}
		
		
		
		@DELETE
		@Path("/deleteConsumption") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteCunsumption(String consumptionData) 
		{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <ID>
		 String ID = doc.select("ID").text(); 
		 
		 String output = consumpObj.deleteCunsumption(ID); 
		return output; 
		}
	
		
		
//		//search
//		
//		 @GET
//			@Path("/searchCunsumption")
//			@Produces(MediaType.TEXT_HTML)
//			public String searchCunsumption(String consumptionData) {
//				Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser()); 
//				String ID = doc.select("ID").text(); 
//				
//				return consumpObj.searchCunsumption(ID);
//			}

		
}
