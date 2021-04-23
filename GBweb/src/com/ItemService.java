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
		 public String insertItem(@FormParam("ProductName") String ProductName,
		  @FormParam("ProductDesc") String ProductDesc,
		  @FormParam("ProductReg") String ProductReg,
		  @FormParam("ProductPrice") String ProductPrice,
		  @FormParam("InventorID") Integer InventorID)
		 {
		  String output = itemObj.insertItem(ProductName, ProductDesc, ProductReg, ProductPrice,InventorID);
		 return output;
		 }

	}
