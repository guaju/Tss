package us.mifeng.bubaexaminationsystem.fragment;

import java.util.Calendar;

import us.mifeng.bubaexaminationsystem.activity.XueYuanHuiZong_Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class SetDateDialog extends DialogFragment implements OnDateSetListener {
	String str="";
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 final Calendar c = Calendar.getInstance();
         int year = c.get(Calendar.YEAR);
         int month = c.get(Calendar.MONTH);
         int day= c.get(Calendar.DAY_OF_MONTH);
         DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
		return dpd;
		
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		int mothOfYear1 = monthOfYear+1; 
		str=year +"-"+ mothOfYear1 +"-"+dayOfMonth;
		XueYuanHuiZong_Activity activity = (XueYuanHuiZong_Activity) getActivity();
		activity.date1.setText(str);

		

		activity.date2.setText("");

	}
 
  

}
