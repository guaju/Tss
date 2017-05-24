package us.mifeng.bubaexaminationsystem.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import us.mifeng.bubaexaminationsystem.activity.ClassInput_Activity;
import us.mifeng.bubaexaminationsystem.activity.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CommonUtils {
	/*
	 *dp转换为px 
	 * 
	 * */
	
	public  static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
	public static String  formateDouble(double d){
		DecimalFormat df=new DecimalFormat(".##");
		return df.format(d);
	}
	public 	static ProgressDialog getProgressDialog(Context context,String str){
		ProgressDialog pd = new ProgressDialog(context);
		View progressView = LayoutInflater.from(context).inflate(R.layout.progressdialog, null);
		TextView tv=(TextView) progressView.findViewById(R.id.message);
		tv.setText(str);
		pd.show();
		pd.setContentView(progressView);
		return pd;	
	}
	public static	ProgressDialog getProgressDialog(Context context){
		ProgressDialog pd = new ProgressDialog(context);
		View progressView = LayoutInflater.from(context).inflate(R.layout.progressdialog, null);
		TextView tv=(TextView) progressView.findViewById(R.id.message);
		tv.setVisibility(View.GONE);
		pd.setContentView(progressView);
		return pd;	
	}
}
