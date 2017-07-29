/**
 * 
 */
package com.shutterfly.ltv.main;

import com.shutterfly.ltv.model.Data;
import com.shutterfly.ltv.model.Event;

/**
 * @author bhanu
 *
 */
public class CustomerLifetimeValueHelper {
	
/*	public void ingest(String event, Data data){
		// TODO JSON parse to check type of event and extract values to add or update the data		
	}*/
	/**
	 * This method ingest the event data into the appropriate event data in existing data
	 * @param event
	 * @param data
	 */
	public Data ingest(Event event, Data data){
		//TODO check for event type by extracting type value from event object
		// create data object here by null checking whether it has already created or not
		// if it has already creted, then use the data object. if not, create new data object
		String eventType = event.getType();
		
		return data;
	}	
	/**
	 * should return collection of top customers with lifeTimeValue as HashMap<Customer,double> object
	 * @param x
	 * @param data
	 * @return
	 */
	public Object topXSimpleLTVCustomers(int x, Data data){
		return null;
	}
	
	

}
