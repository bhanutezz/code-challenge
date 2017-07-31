package com.shutterfly.ltv.model;

import java.util.Date;

/**
 * Customer model Class is to persist customer data for life time value calculation and for further inventory storage
 * @author bhanu
 * @since 07/27/2017
 *
 */
public class Customer extends Event{
	private String customerId;	
	private String type;
	private String verb;
	private String lastName;
	private String adrCity;
	private String adrState;
	private Date lastVisited;
	public Customer(){
		super();
	}
	/**
	 * @param customerId
	 * @param type
	 * @param verb
	 * @param lastName
	 * @param adrCity
	 * @param adrState
	 */
	public Customer(String customerId, String type, String verb, String lastName, String adrCity, String adrState) {
		super();
		this.customerId = customerId;
		this.type = type;
		this.verb = verb;
		this.lastName = lastName;
		this.adrCity = adrCity;
		this.adrState = adrState;
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
	 * @return the lastVisited
	 */
	public Date getLastVisited() {
		return lastVisited;
	}
	/**
	 * @param lastVisited the lastVisited to set
	 */
	public void setLastVisited(Date firstVisited) {
		this.lastVisited = firstVisited;
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
