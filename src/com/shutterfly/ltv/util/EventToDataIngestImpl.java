/**
 * 
 */
package com.shutterfly.ltv.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shutterfly.ltv.main.CustomerLifetimeValueHelper;
import com.shutterfly.ltv.model.Customer;
import com.shutterfly.ltv.model.Image;
import com.shutterfly.ltv.model.Order;
import com.shutterfly.ltv.model.SiteVisit;

/**
 * @author bhanu
 *
 */
public class EventToDataIngestImpl implements EventToDataIngest{
	public CustomerLifetimeValueHelper cltvHelper =null;
	
	public Customer addCustomerJSONObject(JSONObject customerJSON){
		Customer customer = new Customer();
		customer.setEventId((String)customerJSON.get("key"));
		customer.setCustomerId((String)customerJSON.get("key"));
		customer.setType((String)customerJSON.get("type"));
		customer.setVerb((String)customerJSON.get("verb"));
		try {
			customer.setEventTime(parseDate((String)customerJSON.get("event_time")));
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setLastName((String)customerJSON.get("last_name"));
		customer.setAdrCity((String)customerJSON.get("adr_city"));
		customer.setAdrState((String)customerJSON.get("adr_state"));
		customer.setLastVisited(customer.getEventTime());
		return customer;

	}
	public SiteVisit addSiteVisitJSONObject(JSONObject siteVisitJSON){
		SiteVisit siteVisit = new SiteVisit();
		siteVisit.setEventId((String)siteVisitJSON.get("key"));
		siteVisit.setSiteVisitId((String)siteVisitJSON.get("key"));
		siteVisit.setCustomerId((String)siteVisitJSON.get("customer_id"));
		siteVisit.setType((String)siteVisitJSON.get("type"));
		siteVisit.setVerb((String)siteVisitJSON.get("verb"));
		try {
			siteVisit.setEventTime(parseDate((String)siteVisitJSON.get("event_time")));
		} catch (JSONException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//JSONArray tagsArray =siteVisitJSON.getJSONArray("tags");
		// This block of code uses Jackson core and Jackson databind libraries to
		// retrieve Array of site-visit tags from SiteVisist event
		// Assuming that tags property may contain multiple tags with unknown key value pairs
		/*try{
			ObjectMapper mapper = new ObjectMapper(new JsonFactory());
			JsonNode root = mapper.readTree((String)siteVisitJSON.get("tags"));
			Iterator<Map.Entry<String, JsonNode>> iterator = root.fields();
			HashMap<String, String> siteTags = new HashMap<>();
			while (iterator.hasNext()) {
				Map.Entry<String,JsonNode> field = iterator.next();
				siteTags.put(field.getKey(),field.getValue().toString());
				}
			siteVisit.setTags(siteTags);
		}catch(JSONException | IOException e){
			e.printStackTrace();
		}*/
		JSONArray tagsJ =(JSONArray)siteVisitJSON.get("tags");
		HashMap<String, String> tags = new HashMap<>();
		Iterator<?> itr = tagsJ.iterator();
		while(itr.hasNext()){
			JSONObject jsonObj = (JSONObject) itr.next();
			Iterator<?> itrPair = jsonObj.entrySet().iterator();
			Entry entry = null;
			while (itrPair.hasNext()) {
				entry = (Entry) itrPair.next();
				tags.put((String) entry.getKey(), (String) entry.getValue());
			}
		}
		siteVisit.setTags(tags);
		return siteVisit;
	}
	public Image addImageJSONObject(JSONObject imageJSON){
		Image image =  new Image();
		image.setEventId((String)imageJSON.get("key"));
		image.setImageId((String)imageJSON.get("key"));
		image.setType((String)imageJSON.get("type"));
		image.setVerb((String)imageJSON.get("verb"));
		try {
			image.setEventTime(parseDate((String)imageJSON.get("event_time")));
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setCustomerId((String)imageJSON.get("customer_id"));
		image.setCameraMake((String)imageJSON.get("camera_make"));
		image.setCameraModel((String)imageJSON.get("camera_model"));
		return image;
		
	}
	public Order addOrderJSONObject(JSONObject orderJSON){
		Order order = new Order();
		order.setEventId((String)orderJSON.get("key"));
		order.setOrderId((String)orderJSON.get("key"));
		order.setType((String)orderJSON.get("type"));
		order.setVerb((String)orderJSON.get("verb"));
		//parsing event time into date format
		try {
			order.setEventTime(parseDate((String)orderJSON.get("event_time")));
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setCustomerId((String)orderJSON.get("customer_id"));
		// Assuming total amount contains order value and currency type, it handles leading and trailing spaces
		//String[] amountInfo = (String[])(orderJSON.getString("total_amount").trim()).split(" ", 1);
		String amount = (String)(orderJSON.get("total_amount"));
		String[] amountInfo = amount.trim().split(" ", 0);
		System.out.println(amountInfo[0]);
		//amountInfo[0] contains order amount and amountInfo[1] contains currency type
		order.setTotalAmount(Double.parseDouble(amountInfo[0]));
		return order;
		
	}
	public Date parseDate(String dateString) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		Date dateFormatted = dateFormat.parse(dateString);
		return dateFormatted;
	}
}
