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
public class SiteVisit extends Event{
	private String pageId;
	private String type;
	private String verb;
	private String customerId;
	private String tags;
	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}
	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
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
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
}