/**
 * 
 */
package com.shutterfly.ltv.main;

import com.shutterfly.ltv.model.Event;
import com.shutterfly.ltv.util.Data;

/**
 * @author bhanu
 *
 */
public class CustomerLifetimeValueHelper {
	
	public void ingest(String event, Data data){
		// TODO JSON parse to check type of event and extract values to add or update the data		
	}
	/**
	 * This method ingest the event data into the appropriate event data in existing data
	 * @param event
	 * @param data
	 */
	public void ingest(Event event, Data data){
		//TODO check for event type by extracting type value from event object
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
