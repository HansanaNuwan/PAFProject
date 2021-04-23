package com;
import Model.Item;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")
	public class ItemService
	{
		 Item itemObj = new Item();
		 
		 //read data
		 
		 @GET
		 @Path("/")
		 @Produces(MediaType.TEXT_HTML)
		
		 public String readItems()
		  {
		  return itemObj.readItems();
		  }
		 
		 //insert data
		 
		 @POST
		 @Path("/")
		 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		 @Produces(MediaType.TEXT_PLAIN)
		 public String insertItem(@FormParam("ProductName") String ProductName,
		  @FormParam("ProductDesc") String ProductDesc,
		  @FormParam("ProductReg") String ProductReg,
		  @FormParam("ProductPrice") String ProductPrice,
		  @FormParam("InventorID") Integer InventorID)
		 {
		  String output = itemObj.insertItem(ProductName, ProductDesc, ProductReg, ProductPrice,InventorID);
		 return output;
		 }
		 
		 //update data
		 
		 @PUT
		 @Path("/")
		 @Consumes(MediaType.APPLICATION_JSON)
		 @Produces(MediaType.TEXT_PLAIN)
		 public String updateItem(String itemData)
		 {
		 //Convert the input string to a JSON object
		  JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		 //Read the values from the JSON object
		  int ProductID = itemObject.get("ProductID").getAsInt();
		  String ProductName = itemObject.get("ProductName").getAsString();
		  String ProductDesc = itemObject.get("ProductDesc").getAsString();
		  String ProductReg = itemObject.get("ProductReg").getAsString();
		  String ProductPrice = itemObject.get("ProductPrice").getAsString();
		  int InventorID = itemObject.get("InventorID").getAsInt();
		  String output = itemObj.updateItem( ProductID,ProductName, ProductDesc, ProductReg, ProductPrice,InventorID);
		 return output;
		 }

		 
		 //delete the data
		 
		 @DELETE
		 @Path("/")
		 @Consumes(MediaType.APPLICATION_XML)
		 @Produces(MediaType.TEXT_PLAIN)
		 public String deleteItem(String itemData)
		 {
		 //Convert the input string to an XML document
		  Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		 //Read the value from the element <itemID>
		  String ProductID = doc.select("ProductID").text();
		  String output = itemObj.deleteItem(ProductID);
		 return output;
		 }
		 

	}
