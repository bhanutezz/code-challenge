/**
 * 
 */
package com.shutterfly.ltv.model;

/**
 * @author bhanu
 * @since 07/27/2017
 *
 */
public class Image extends Event{
	private String imageId;
	private String verb;
	private String customerId;
	private String cameraMake;
	private String cameraModel;
	/**
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
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
	 * @return the cameraMake
	 */
	public String getCameraMake() {
		return cameraMake;
	}
	/**
	 * @param cameraMake the cameraMake to set
	 */
	public void setCameraMake(String cameraMake) {
		this.cameraMake = cameraMake;
	}
	/**
	 * @return the cameraModel
	 */
	public String getCameraModel() {
		return cameraModel;
	}
	/**
	 * @param cameraModel the cameraModel to set
	 */
	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

}
