/**
 * 
 */
package com.shutterfly.ltv.util;

import org.json.simple.JSONObject;

import com.shutterfly.ltv.model.Customer;
import com.shutterfly.ltv.model.Image;
import com.shutterfly.ltv.model.Order;
import com.shutterfly.ltv.model.SiteVisit;

/**
 * EventToDataIngest interface defines methods to ingest all events data
 * @author bhanu
 * @since 07/27/2017
 */
public interface EventToDataIngest {
	public Customer addCustomerJSONObject(JSONObject customerJSON);
	public SiteVisit addSiteVisitJSONObject(JSONObject siteVisitJSON);
	public Image addImageJSONObject(JSONObject imageJSON);
	public Order addOrderJSONObject(JSONObject orderJSON);

}
