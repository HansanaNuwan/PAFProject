package main.java.com;

import java.awt.PageAttributes.MediaType;

import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit.Parser;
//For REST Service
import javax.ws.rs.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;

//For JSON
import com.google.gson.*;

import main.java.model.Payment; 
@Path("/Payments")
public class PaymentService {

	Payment paymentObj = new Payment(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPayments() 
	 { 
	 return paymentObj.readpayments(); 
	 }
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPayment(@FormParam("paymentType") String paymentType, 
	 @FormParam("paymentAmount") String paymentAmount 
	
	{ 
	 String output = paymentObj.insertPayment(paymentType, paymentAmount); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String paymentID = paymentObject.get("paymentID").getAsString(); 
	 String paymentType = paymentObject.get("paymentType").getAsString(); 
	 String paymentAmount = paymentObject.get("paymentAmount").getAsString(); 
	 String output = paymentObj.updatePayment(paymentID, paymentType, paymentAmount); 
	 return output; 
	}
    
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String paymentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String paymentID = doc.select("paymentID").text(); 
	 String output = paymentObj.deletePayment(paymentID); 
	return output; 
	}


	
}
