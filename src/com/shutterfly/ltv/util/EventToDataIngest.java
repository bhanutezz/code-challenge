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

import org.json.JSONException;
import org.json.JSONObject;

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
public class EventToDataIngest {
	public CustomerLifetimeValueHelper cltvHelper =null;
	
	public Customer addCustomerJSONObject(JSONObject customerJSON){
		Customer customer = new Customer();
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
		return customer;

	}
	public SiteVisit addSiteVisitJSONObject(JSONObject siteVisitJSON){
		SiteVisit siteVisit = new SiteVisit();
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
		try{
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
		}
		return siteVisit;
	}
	public Image addImageJSONObject(JSONObject imageJSON){
		Image image =  new Image();
		image.setImageId((String)imageJSON.getString("key"));
		image.setType((String)imageJSON.getString("type"));
		image.setVerb((String)imageJSON.getString("verb"));
		try {
			image.setEventTime(parseDate((String)imageJSON.getString("event_time")));
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setCustomerId((String)imageJSON.getString("customer_id"));
		image.setCameraMake((String)imageJSON.getString("camera_make"));
		image.setCameraModel((String)imageJSON.getString("camera_model"));
		return image;
		
	}
	public Order addOrderJSONObject(JSONObject orderJSON){
		Order order = new Order();
		order.setOrderId((String)orderJSON.getString("key"));
		order.setType((String)orderJSON.getString("type"));
		order.setVerb((String)orderJSON.getString("verb"));
		//parsing event time into date format
		try {
			order.setEventTime(parseDate((String)orderJSON.getString("event_time")));
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setCustomerId((String)orderJSON.getString("customer_id"));
		// Assuming total amount contains order value and currency type, it handles leading and trailing spaces
		String[] amountInfo = (String[])(orderJSON.getString("total_amount").trim()).split(" ", 1);
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
