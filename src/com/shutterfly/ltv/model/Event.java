/**
 * 
 */
package com.shutterfly.ltv.model;

import java.util.Date;

/**
 * Event class is Super class for all event classes which holds common event informaiton
 * as well as useful in handling all events in generic ingestion methods and other life time value calculations
 * @author bhanu
 * @since 07/27/2017
 */
public class Event {
	private Date eventTime;
	private String eventId;
	private String type;
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
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventTime
	 */
	public Date getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

}
