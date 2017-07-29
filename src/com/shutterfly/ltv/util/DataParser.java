/**
 * 
 */
package com.shutterfly.ltv.util;

import org.json.simple.JSONObject;

import com.shutterfly.ltv.model.Data;

/**
 * @author bhanu
 *
 */
public interface DataParser {
	public Data formData(String fileName);
	public Data ingestEvent(JSONObject singleEvent, Data data);
}
