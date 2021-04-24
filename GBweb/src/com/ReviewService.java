package com;
import model.Review; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Review") 
public class ReviewService 
{ 
	 Review itemObj = new Review(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		 return itemObj.readItems();
	 } 
	
	

	 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("projectCode") String projectCode, 
	 @FormParam("review") String review, 
	 @FormParam("decision") String decision) 
	
	{ 
	 String output = itemObj.insertItem(projectCode, review, decision); 
	return output; 
	}

	

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String reviewID = itemObject.get("reviewID").getAsString(); 
	 String projectCode = itemObject.get("projectCode").getAsString(); 
	 String review = itemObject.get("review").getAsString(); 
	 String decision = itemObject.get("decision").getAsString(); 
	 String output = itemObj.updateItem(reviewID, projectCode, review, decision); 
	return output; 
	}
	
	 
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <reviewID>
	 String itemID = doc.select("reviewID").text(); 
	 String output = itemObj.deleteItem(itemID); 
	return output; 
	}

	
	
}

