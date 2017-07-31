/**
 * 
 */
package com.shutterfly.ltv.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Data class is to store total data related to all events, which is used to calculate Customer Life Time Value
 * @author bhanu
 * @since 07/27/2017
 */
public class Data {
	private HashMap<String, Customer> customers;
	private HashMap<String, SiteVisit> siteVisits;
	private HashMap<String, Image> images;
	private HashMap<String, Order> orders;
	HashMap<String,ArrayList<SiteVisit>> customerSiteVisists;
	HashMap<String, LinkedList<Order>> customerOrders;
	public int averageCustomerLifespan = 10;
	/**
	 * @return the customers
	 */
	public HashMap<String, Customer> getCustomers() {
		return customers;
	}
	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}
	/**
	 * @return the siteVisits
	 */
	public HashMap<String, SiteVisit> getSiteVisits() {
		return siteVisits;
	}
	/**
	 * @param siteVisits the siteVisits to set
	 */
	public void setSiteVisits(HashMap<String, SiteVisit> siteVisits) {
		this.siteVisits = siteVisits;
	}
	/**
	 * @return the images
	 */
	public HashMap<String, Image> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(HashMap<String, Image> images) {
		this.images = images;
	}
	/**
	 * @return the orders
	 */
	public HashMap<String, Order> getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}
	/**
	 * @return the averageCustomerLifespan
	 */
	public int getAverageCustomerLifespan() {
		return averageCustomerLifespan;
	}
	/**
	 * @param averageCustomerLifespan the averageCustomerLifespan to set
	 */
	public void setAverageCustomerLifespan(int averageCustomerLifespan) {
		this.averageCustomerLifespan = averageCustomerLifespan;
	}
	/**
	 * @return the customerSiteVisists
	 */
	public HashMap<String, ArrayList<SiteVisit>> getCustomerSiteVisists() {
		return customerSiteVisists;
	}
	/**
	 * @param customerSiteVisists the customerSiteVisists to set
	 */
	public void setCustomerSiteVisists(HashMap<String, ArrayList<SiteVisit>> customerSiteVisists) {
		this.customerSiteVisists = customerSiteVisists;
	}
	/**
	 * @return the customerOrders
	 */
	public HashMap<String, LinkedList<Order>> getCustomerOrders() {
		return customerOrders;
	}
	/**
	 * @param customerOrders the customerOrders to set
	 */
	public void setCustomerOrders(HashMap<String, LinkedList<Order>> customerOrders) {
		this.customerOrders = customerOrders;
	}
	
}
