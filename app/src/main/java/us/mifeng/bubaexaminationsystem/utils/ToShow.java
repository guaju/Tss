package us.mifeng.bubaexaminationsystem.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
public class ToShow {
	static String st=null;
	private static Toast to;
	public static void show(Context context,String str){
		if (to==null) {
			to = Toast.makeText(context,str, Toast.LENGTH_SHORT);
		}
		if(!TextUtils.isEmpty(str)){
			to.setText(str);
		}
		to.show();
	}

}
