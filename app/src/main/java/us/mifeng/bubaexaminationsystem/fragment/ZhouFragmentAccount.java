package us.mifeng.bubaexaminationsystem.fragment;


import us.mifeng.bubaexaminationsystem.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ZhouFragmentAccount extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.zhou_fragment_account, null);
		TextView tv_base=(TextView) v.findViewById(R.id.tv_account_base);
		TextView tv_account_attendence=(TextView) v.findViewById(R.id.tv_account_attendence);
		TextView tv_account_discipline=(TextView) v.findViewById(R.id.tv_account_discipline);
		return v;
		
	}

}
