package us.mifeng.bubaexaminationsystem.app;

import us.mifeng.bubaexaminationsystem.activity.ClassInput_Activity;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View.OnKeyListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class PublicApplication extends Application {
	public static PublicApplication pApp;
	public static  RequestQueue requestQueue;	
	public static ProgressDialog progressDialog;
	@Override
	public void onCreate() {
		pApp = this;
	}
	public static RequestQueue getRequestQueue(Context context){
		if (requestQueue==null) {
			requestQueue = Volley.newRequestQueue(context);
		}
		return requestQueue;
	}
	public static ProgressDialog getProgressDialog(Context context) {
			progressDialog=CommonUtils.getProgressDialog(context);
			return progressDialog;
	}
	
}