/**
 * 
 */
package com.shutterfly.ltv.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.shutterfly.ltv.model.Customer;
import com.shutterfly.ltv.model.Data;
import com.shutterfly.ltv.model.Event;
import com.shutterfly.ltv.util.DataParserImpl;

/**
 * @author bhanu
 * @since 07/27/2017
 *
 */
public class CustomerLifetimeValue {
	public static int noOfTopLTVCustomers = 3;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "input/input.txt";
		// Data parser parsing the data from input file
		// creates Data object with all the events data
		DataParserImpl dataParserImpl = new DataParserImpl();
		Data data = dataParserImpl.formData(fileName);
		// TODO pass a sample single event to Data object to add or update it
		//create sample event object and pass it to ingest() method for test purpose
		Event event = createSampleEventObject();
		CustomerLifetimeValueHelper clvHelper = new CustomerLifetimeValueHelper();
		// This is to add or update the event in existing data
		data = clvHelper.ingest(event, data);
		System.out.println("Ingestion Completed");
		countEvents(data);
		// This is to find top customers with highest simple Life Time Value from the data  
		clvHelper.topXSimpleLTVCustomers(noOfTopLTVCustomers, data);
	}
	
	public static Event createSampleEventObject(){
		// Sample Customer event
		Customer cust = new Customer("96f55c7d8f42", "CUSTOMER", "NEW", "Smith", "Middletown", "AK");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");	
		Event event = null;
		try {
			cust.setLastVisited(dateFormat.parse("2017-01-06T12:46:46.384Z"));
			event = cust;
			event.setEventId("96f55c7d8f42");
			event.setEventTime(dateFormat.parse("2017-01-06T12:46:46.384Z"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return event;
	}
	
	
	public static void countEvents(Data data){
		System.out.println("Data Count");
		System.out.println("Customers: "+data.getCustomers().size());
		System.out.println("SiteVisits: "+data.getSiteVisits().size());
		System.out.println("Orders: "+data.getOrders().size());
		System.out.println("Images: "+data.getImages().size());
		System.out.println("CustomerSiteVisits: "+data.getCustomerSiteVisists().size());
		System.out.println("CustomerOrders: "+data.getCustomerOrders().size());
		HashMap<String, Customer> map = null;
		map = data.getCustomers();
		Customer customer = map.get("96f55c7d8f42");
		System.out.println(customer.getEventTime().toString());
		System.out.println(customer.getLastVisited().toString());
	}

}
