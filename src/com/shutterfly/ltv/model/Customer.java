package com.shutterfly.ltv.model;

import java.util.Date;
/**
 * @author bhanu
 * @since 07/27/2017
 *
 */
public class Customer extends Event{
	private String customer_id;	
	private String type;
	private String verb;
	private String lastName;
	private String adrCity;
	private String adrState;
	
	/**
	 * @param customer_id
	 * @param type
	 * @param verb
	 * @param lastName
	 * @param adrCity
	 * @param adrState
	 */
	public Customer(String customer_id, String type, String verb, String lastName, String adrCity, String adrState) {
		super();
		this.customer_id = customer_id;
		this.type = type;
		this.verb = verb;
		this.lastName = lastName;
		this.adrCity = adrCity;
		this.adrState = adrState;
	}
	/**
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}
	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
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
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the adrCity
	 */
	public String getAdrCity() {
		return adrCity;
	}
	/**
	 * @param adrCity the adrCity to set
	 */
	public void setAdrCity(String adrCity) {
		this.adrCity = adrCity;
	}
	/**
	 * @return the adrState
	 */
	public String getAdrState() {
		return adrState;
	}
	/**
	 * @param adrState the adrState to set
	 */
	public void setAdrState(String adrState) {
		this.adrState = adrState;
	}
	

}
