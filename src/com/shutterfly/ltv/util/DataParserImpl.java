/**
 * 
 */
package com.shutterfly.ltv.util;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.shutterfly.ltv.main.CustomerLifetimeValueHelper;
import com.shutterfly.ltv.model.Customer;
import com.shutterfly.ltv.model.Data;
import com.shutterfly.ltv.model.Image;
import com.shutterfly.ltv.model.Order;
import com.shutterfly.ltv.model.SiteVisit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
/**
 * @author bhanu
 *
 */
public class DataParserImpl {

	/**
	 * This will parse the data text file creates data object with all events
	 * @param fileName
	 * @return
	 */
	public Data formData(String fileName) {
		// TODO Auto-generated method stub
		System.out.println("Started formData method");
		JSONParser parseData = new JSONParser();
		JSONArray parsedData = null;
		Data data = null;
		try {
			parsedData = (JSONArray) parseData.parse(new FileReader(new File(fileName).getCanonicalPath()));
			System.out.println("entered");
			for(Object event: parsedData){
				JSONObject singleEvent = (JSONObject)event;
				data = ingestEvent(singleEvent, data);
				//System.out.println(singleEvent);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	private Data ingestEvent(JSONObject singleEvent, Data data){
		String eventType = (String)singleEvent.get("type");
		EventToDataIngest ingestEvent = null;
		CustomerLifetimeValueHelper ltvHelper = new CustomerLifetimeValueHelper();
		System.out.println(eventType);
		switch(eventType){
			case "CUSTOMER":
				ingestEvent = new EventToDataIngest();
				Customer customer = ingestEvent.addCustomerJSONObject(singleEvent);
				ltvHelper.ingest(customer, data);
				break;
			case "SITE_VISIT":
				ingestEvent = new EventToDataIngest();
				SiteVisit siteVisit = ingestEvent.addSiteVisitJSONObject(singleEvent);
				ltvHelper.ingest(siteVisit, data);
				break;
			case "IMAGE":
				ingestEvent = new EventToDataIngest();
				Image image = ingestEvent.addImageJSONObject(singleEvent);
				ltvHelper.ingest(image, data);
				break;
			case "ORDER":
				ingestEvent = new EventToDataIngest();
				Order order = ingestEvent.addOrderJSONObject(singleEvent);
				ltvHelper.ingest(order, data);
				break;
			default:
				throw new IllegalArgumentException("Invalid event type" + eventType +"Expected CUSTOMER/ SITE_VISIT/ IMAGE/ ORDER type");
		}
		return data;
	}
	
}
