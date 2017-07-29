/**
 * 
 */
package com.shutterfly.ltv.model;

import java.util.HashMap;

/**
 * @author bhanu
 *
 */
public class Data {
	HashMap<String, Customer> customers;
	HashMap<String, SiteVisit> siteVisits;
	HashMap<String, Image> iamges;
	HashMap<String, Order> orders;
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
	 * @return the iamges
	 */
	public HashMap<String, Image> getIamges() {
		return iamges;
	}
	/**
	 * @param iamges the iamges to set
	 */
	public void setIamges(HashMap<String, Image> iamges) {
		this.iamges = iamges;
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
	
}
