/**
 * 
 */
package com.shutterfly.ltv.util;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.shutterfly.ltv.main.CustomerLifetimeValueHelper;
import com.shutterfly.ltv.model.Data;
import com.shutterfly.ltv.model.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONArray;
import org.json.simple.JSONObject;
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
	
	/**
	 * This method is local for DataParserImpl class to segregate different event types JSON objects to
	 * ingest into final Data object
	 * @param singleEvent
	 * @param data
	 * @return data
	 */
	private Data ingestEvent(JSONObject singleEvent, Data data){
		String eventType = (String)singleEvent.get("type");
		EventToDataIngestImpl ingestEvent = null;
		CustomerLifetimeValueHelper ltvHelper = new CustomerLifetimeValueHelper();
		System.out.println(eventType);
		switch(eventType){
			case "CUSTOMER":
				ingestEvent = new EventToDataIngestImpl();
				Event customerEvent = ingestEvent.addCustomerJSONObject(singleEvent);
				data = ltvHelper.ingest(customerEvent, data);
				break;
			case "SITE_VISIT":
				ingestEvent = new EventToDataIngestImpl();
				Event siteVisitEvent = ingestEvent.addSiteVisitJSONObject(singleEvent);
				data = ltvHelper.ingest(siteVisitEvent, data);
				break;
			case "IMAGE":
				ingestEvent = new EventToDataIngestImpl();
				Event imageEvent = ingestEvent.addImageJSONObject(singleEvent);
				data = ltvHelper.ingest(imageEvent, data);
				break;
			case "ORDER":
				ingestEvent = new EventToDataIngestImpl();
				Event orderEvent = ingestEvent.addOrderJSONObject(singleEvent);
				data = ltvHelper.ingest(orderEvent, data);
				break;
			default:
				throw new IllegalArgumentException("Invalid event type" + eventType +"Expected CUSTOMER/ SITE_VISIT/ IMAGE/ ORDER type");
		}
		return data;
	}
	
}
