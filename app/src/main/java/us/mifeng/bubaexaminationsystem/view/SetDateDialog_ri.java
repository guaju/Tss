package us.mifeng.bubaexaminationsystem.view;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class SetDateDialog_ri extends DialogFragment implements OnDateSetListener {
	private String str;
	private int a,b,c;
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
		str=year +"--"+ (monthOfYear+1) +"--"+dayOfMonth;
//		Fragment_ri.tv_riqi.setText(str);
		
	}
}
