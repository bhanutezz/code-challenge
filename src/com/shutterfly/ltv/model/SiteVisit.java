/**
 * 
 */
package com.shutterfly.ltv.model;

import java.util.HashMap;

/**
 * SiteVisit class is to persist Customer site visit information for life time value calculation and inventory storage
 * @author bhanu
 * @since 07/27/2017
 *
 */
public class SiteVisit extends Event{
	private String siteVisitId;
	private String type;
	private String verb;
	private String customerId;
	// Array of key value pairs
	private HashMap<String, String> tags;
	/**
	 * @return the siteVisitId
	 */
	public String getSiteVisitId() {
		return siteVisitId;
	}
	/**
	 * @param siteVisitId the siteVisitId to set
	 */
	public void setSiteVisitId(String siteVisitId) {
		this.siteVisitId = siteVisitId;
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
	 * @return the tags
	 */
	public HashMap<String, String> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(HashMap<String, String> tags) {
		this.tags = tags;
	}
}
