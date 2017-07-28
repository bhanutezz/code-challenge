/**
 * 
 */
package com.shutterfly.ltv.main;

import com.shutterfly.ltv.model.Customer;
import com.shutterfly.ltv.model.Event;
import com.shutterfly.ltv.util.Data;
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
		String fileName = "/input/input.txt";
		// Data parser parsing the data from input file
		// creates Data object with all the events data
		DataParserImpl dataParserImpl = new DataParserImpl();
		Data data = dataParserImpl.formData(fileName);
		// TODO pass a sample single event to Data object to add or update it
		//create sample event object and pass it to ingest() method for test purpose
		Event event = CustomerLifetimeValue.createSampleEventObject();
		CustomerLifetimeValueHelper clvHelper = new CustomerLifetimeValueHelper();
		// This is to add or update the event in existing data
		clvHelper.ingest(event, data);
		// This is to find top customers with highest simple Life Time Value from the data  
		clvHelper.topXSimpleLTVCustomers(noOfTopLTVCustomers, data);
	}
	
	public static Event createSampleEventObject(){
		Event event = new Customer("96f55c7d8f42", "CUSTOMER", "NEW", "Smith", "Middletown", "AK");
		event.setEventTime("2017-01-06T12:46:46.384Z");
		return event;
	}

}
