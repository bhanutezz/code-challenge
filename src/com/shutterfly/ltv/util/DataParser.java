/**
 * 
 */
package com.shutterfly.ltv.util;

import org.json.simple.JSONObject;

import com.shutterfly.ltv.model.Data;

/**
 * DataParser interface is to define formData ingestEvent methods
 * @author bhanu
 * @since 07/27/2017
 */
public interface DataParser {
	public Data formData(String fileName);
	public Data ingestEvent(JSONObject singleEvent, Data data);
}
