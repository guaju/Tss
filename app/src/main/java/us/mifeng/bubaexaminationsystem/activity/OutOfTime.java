package us.mifeng.bubaexaminationsystem.activity;

import org.json.JSONException;
import org.json.JSONObject;

import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.content.Context;

public abstract class OutOfTime {
	public void time(Context context,String str) throws JSONException{
		JSONObject bb= new JSONObject(str);
		String ss = (String) bb.get("status");
		if (!ss.equals("200")) {
			ToShow.show(context, "登录超时，请重新登录");
			toDo();
		} 
	}
	public abstract void toDo();
}