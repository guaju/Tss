package us.mifeng.bubaexaminationsystem.engine;

import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.bean.Update;

public class ParseJson {
	
	public static Update getUpdate(String str) throws JSONException{
		JSONObject jo = new JSONObject(str);
		JSONObject dataJo = jo.getJSONObject("data");
		String ver=dataJo.get("ver")+"";
		String url=dataJo.get("url")+"";
		String notes=dataJo.get("notes")+"";
		return new Update(ver, url, notes);
	}
	

}
