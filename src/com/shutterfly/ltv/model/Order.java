/**
 * 
 */
package com.shutterfly.ltv.model;

import java.util.Date;

/**
 * @author bhanu
 * @since 07/27/2017
 *
 */
public class Order extends Event{
	private String order_id;
	private String type;
	private String verb;
	private String customerId;
	private double totalAmount;
	/**
	 * @return the order_id
	 */
	public String getOrder_id() {
		return order_id;
	}
	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the verb
	 */
	public String getVerb() {
		return verb;
	}
	/**
	 * @param verb the verb to set
	 */
	public void setVerb(String verb) {
		this.verb = verb;
	}
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
