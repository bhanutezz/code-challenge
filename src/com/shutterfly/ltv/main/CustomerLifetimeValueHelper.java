package com.shutterfly.ltv.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import com.shutterfly.ltv.model.Customer;
import com.shutterfly.ltv.model.Data;
import com.shutterfly.ltv.model.Event;
import com.shutterfly.ltv.model.Image;
import com.shutterfly.ltv.model.Order;
import com.shutterfly.ltv.model.SiteVisit;

/**
 * @author bhanu
 *
 */
public class CustomerLifetimeValueHelper {
	public static int topXCustomers =0;
	
/*	public void ingest(String event, Data data){
		// TODO JSON parse to check type of event and extract values to add or update the data		
	}*/
	/**
	 * This ingest(Event event, Data data) method is generic method for overall application when event is passed as an object
	 * rather than JSON object, passed event data will be added to existing data or new data to appropriate event type collection
	 * @param event
	 * @param data
	 */
	public Data ingest(Event event, Data data){
		//TODO check for event type by extracting type value from event object
		// create data object here by null checking whether it has already created or not
		// if it has already created, then use the data object. if not, create new data object
		String eventType = event.getType();
		switch(eventType){
		case "CUSTOMER":
			data = ingestCustomer((Customer)event, data);
			break;
		case "SITE_VISIT":
			data = ingestSiteVisit((SiteVisit)event, data);
			break;
		case "IMAGE":
			data = ingestImage((Image)event, data);
			break;
		case "ORDER":
			data = ingestOrder((Order)event, data);
			break;
		default:
			throw new IllegalArgumentException("Invalid event type" + eventType +"Expected CUSTOMER/ SITE_VISIT/ IMAGE/ ORDER type");
		}
		return data;
	}	
	/**
	 * should return collection of top customers with lifeTimeValue as HashMap<Customer,double> object
	 * @param x
	 * @param data
	 * @return
	 */
	public void topXSimpleLTVCustomers(int x, Data data){
		HashMap<String, Customer> customers = new HashMap<>();
		topXCustomers =x;
		//HashMap<String,ArrayList<SiteVisit>> customerSiteVisists = new HashMap<>();
		//HashMap<String, LinkedList<Order>> customerOrders = new HashMap<>();
		Map<Customer, Double> topCust = new HashMap<>();
		
			//int totalSite
			if(data.getCustomers()!=null){
				customers = data.getCustomers();
				Iterator<?> itr = customers.entrySet().iterator();
				while(itr.hasNext()){
					ArrayList<SiteVisit> custOrders =new ArrayList<>();
					double totalAmount = 0;
					double lifeTimeValue =0;
					Map.Entry customerIdPair = (Map.Entry)itr.next();
					Customer cust = (Customer)customerIdPair.getValue();
					int numWeeksOfCust = Weeks.weeksBetween(new DateTime(cust.getEventTime()), new DateTime(cust.getLastVisited())).getWeeks();
					totalAmount = totalAmount(data.getCustomerOrders().get(cust.getCustomerId()));
					if(numWeeksOfCust==0){
						numWeeksOfCust=1;
					}
					lifeTimeValue = 52*(totalAmount/numWeeksOfCust)*data.getAverageCustomerLifespan();
					topCust.put(cust, lifeTimeValue);	
				}
				topCust = sortByComparator(topCust);
				outPutFile(topCust);
			}
		return ;
	}
	private double totalAmount(LinkedList<Order> list){
		double totalAmount =0;
		Iterator<Order> itr = list.iterator();
		while(itr.hasNext()){
			Order order = itr.next();
			totalAmount += order.getTotalAmount();
		}
		return totalAmount;
	}
	private void outPutFile(Map<Customer, Double> topCust){
		try {
			FileWriter fw = new FileWriter(new File("output/output.txt").getCanonicalPath());
			String newLine = System.getProperty("line.separator");
			Iterator<?> itr = topCust.entrySet().iterator();
			int temp =topXCustomers;
			while(itr.hasNext()&&temp!=0){
				Map.Entry customerPair = (Map.Entry)itr.next();
				Customer customer = (Customer)customerPair.getKey();
				double custLifeTimeValue = (double)customerPair.getValue();
				fw.write(customer.getCustomerId() +"\t"+customer.getLastName()+"\t"+customer.getAdrCity()+"\t"+customer.getAdrState()+"\t"+custLifeTimeValue+newLine);
				temp--;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	private static Map<Customer, Double> sortByComparator(Map<Customer, Double> unsortMap){
		List<Entry<Customer, Double>> list = new LinkedList<Entry<Customer, Double>>(unsortMap.entrySet());
		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<Customer, Double>>(){
			public int compare(Entry<Customer, Double> c1, Entry<Customer, Double> c2){
					return c1.getValue().compareTo(c2.getValue());
				}});
		// Maintaining insertion order with the help of LinkedLis
		Map<Customer, Double> sortedMap = new LinkedHashMap<Customer, Double>();
				for (Entry<Customer, Double> entry : list){
					sortedMap.put(entry.getKey(), entry.getValue());
					}
		return sortedMap;
    }


	private Data ingestCustomer(Customer event, Data data){
		if(data!=null){
			HashMap<String, Customer> customers = data.getCustomers();
			if(customers.containsKey(event.getEventId())){
				Customer oldCustomer = customers.get(event.getEventId());
				// To maintain old event time of the customer
				if(oldCustomer.getEventTime().compareTo(event.getEventTime())==1){
					event.setEventTime(oldCustomer.getEventTime());
				}
				customers.put(event.getEventId(),event);
			}else{
				customers.put(event.getEventId(),event);
			}
			data.setCustomers(customers);
			return data;
		}else{
			Data newData = new Data();
			HashMap<String, Customer> newCustomer = new HashMap<>();
			newCustomer.put(event.getEventId(),event);
			newData.setCustomers(newCustomer);
			HashMap<String,ArrayList<SiteVisit>> customerSiteVisists = new HashMap<String, ArrayList<SiteVisit>>();
			customerSiteVisists.put(event.getEventId(), new ArrayList<SiteVisit>());
			HashMap<String, LinkedList<Order>> customerOrders = new HashMap<>();
			customerOrders.put(event.getEventId(), new LinkedList<Order>());
			newData.setCustomerSiteVisists(customerSiteVisists);
			newData.setCustomerOrders(customerOrders);
			return newData;
		}
	}
	private Data ingestSiteVisit(SiteVisit event, Data data){
		HashMap<String,ArrayList<SiteVisit>> customerSiteVisists = new HashMap<>();
		HashMap<String, SiteVisit> siteVisits = new HashMap<>();
		ArrayList<SiteVisit> aList = new ArrayList<>();
		if(data!=null){
			if(data.getSiteVisits()!=null){
				data.getSiteVisits().put(event.getEventId(),event);
				//siteVisits.put(event.getEventId(),event);
			}else{
				siteVisits.put(event.getEventId(),event);
				data.setSiteVisits(siteVisits);
			}
			//data.setSiteVisits(siteVisits);
			customerSiteVisists = data.getCustomerSiteVisists();
			if(customerSiteVisists!=null&&customerSiteVisists.get(event.getCustomerId())!=null&&customerSiteVisists.get(event.getCustomerId()).contains(event)){
				System.out.println("SiteVisit event already existing with same event id: " +event.getEventId());
			}else if(customerSiteVisists!=null&&customerSiteVisists.get(event.getCustomerId())==null){
				aList.add(event);
				//customerSiteVisists.get(event.getCustomerId()).add(event);
				customerSiteVisists.put(event.getCustomerId(), aList);
				data = updateCustomerLastVisitedProperty(data, event.getCustomerId(), event.getEventTime());
			}else{
				customerSiteVisists.get(event.getCustomerId()).add(event);
				data = updateCustomerLastVisitedProperty(data, event.getCustomerId(), event.getEventTime());
			}
			data.setCustomerSiteVisists(customerSiteVisists);
			return data;
		}else{
			Data newData = new Data();
			// For inventory update
			siteVisits.put(event.getEventId(),event);
			newData.setSiteVisits(siteVisits);
			// For LTV calculation
			aList.add(event);
			customerSiteVisists.put(event.getCustomerId(),aList);
			newData = updateCustomerLastVisitedProperty(newData, event.getCustomerId(),event.getEventTime());
			newData.setCustomerSiteVisists(customerSiteVisists);
			return newData;
		}
	}
	private Data ingestImage(Image event, Data data){
		HashMap<String, Image> images = new HashMap<>();
		if(data!=null){
			if(data.getImages()==null){
				data.setImages(images);
			}
			//HashMap<String, Image> images = data.getImages();
			images.put(event.getEventId(),event);
			data.setImages(images);
			return data;
		}else{
			Data newData = new Data();
			//HashMap<String, Image> images = newData.getImages();
			images.put(event.getEventId(),event);
			newData.setImages(images);
			return newData;
		}
	}
	private Data ingestOrder(Order event, Data data){
		// customerOrders to map all orders to each customer
		HashMap<String, LinkedList<Order>> customerOrders = new HashMap<>();
		HashMap<String, Order> orders = new HashMap<>();
		// list of all orders of a customer
		LinkedList<Order> customerOrdersList = new LinkedList<>();
		if(data!=null){
			// for inventory storage
			if(data.getOrders()==null){
				orders.put(event.getEventId(),(Order)event);
				data.setOrders(orders);
			}else{
				data.getOrders().put(event.getEventId(),(Order)event);
			}
			// for ltv calculation
			customerOrders = data.getCustomerOrders();
			if(customerOrders.get(event.getCustomerId())!=null&&customerOrders.get(event.getCustomerId()).contains(event) && event.getType().equalsIgnoreCase("UPDATE")){
				customerOrders.get(event.getCustomerId()).add(customerOrders.get(event.getCustomerId()).indexOf(event),event);
			}else if(customerOrders.get(event.getCustomerId())!=null && customerOrders.get(event.getCustomerId()).contains(event) && event.getType().equalsIgnoreCase("NEW")){
				customerOrders.get(event.getCustomerId()).add(event);
			}else{
				customerOrdersList.add(event);
				customerOrders.put(event.getCustomerId(), customerOrdersList);
			}
			data.setCustomerOrders(customerOrders);
			return data;
		}else{
			Data newData = new Data();
			// For inventory
			String eventId =event.getEventId();
			if(newData.getOrders()==null){
				orders.put(eventId,(Order)event);
				newData.setOrders(orders);
			}
			// Data formation for LTV calculation 
			customerOrdersList.add(event);
			customerOrders.put(event.getCustomerId(), customerOrdersList);
			newData.setCustomerOrders(customerOrders);
			return newData;
		}
	}
	
	private Data updateCustomerLastVisitedProperty(Data data, String customerId, Date siteVisitedTime){
		HashMap<String, Customer> customers = new HashMap<>();
		if(data.getCustomers()!=null){
			customers = data.getCustomers();
			Iterator<?> itr = customers.entrySet().iterator();
			while(itr.hasNext()){
				@SuppressWarnings("rawtypes")
				Map.Entry customerIdPair = (Map.Entry)itr.next();
				if(((String)customerIdPair.getKey()).equals(customerId)){
					Customer customer = (Customer) customerIdPair.getValue();
					if(customer.getLastVisited()==null || customer.getEventTime().compareTo(siteVisitedTime)==-1){
						customer.setLastVisited(siteVisitedTime);
					}else{
						customer.setLastVisited(customer.getEventTime());
					}
					customers.put(customerId, customer);
					data.setCustomers(customers);
					return data;
				}
			}
		}
		
		return data;
	}

}
