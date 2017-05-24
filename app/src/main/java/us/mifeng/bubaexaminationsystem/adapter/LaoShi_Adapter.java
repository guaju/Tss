package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.BanJi_Student;
import us.mifeng.bubaexaminationsystem.bean.BanJi_Teacher;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LaoShi_Adapter extends BaseAdapter {
	private ArrayList<BanJi_Teacher> list_laoshi;
	private Context content;
	@Override
	public int getCount() {
		return list_laoshi.size();
	}
	public void setList(ArrayList<BanJi_Teacher> li, Context con){
		this.list_laoshi=li;
		this.content=con;
	}
	@Override
	public Object getItem(int arg0) {
		return null;
	}
	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View v = View.inflate(content,
				R.layout.grivlist_fujian2, null);
		TextView tv_2 = (TextView) v.findViewById(R.id.gv_fujian_tv2);
		TextView tv_1 = (TextView) v.findViewById(R.id.gv_fujian_tv1);
		ImageView iv_1 = (ImageView) v.findViewById(R.id.gv_fujian_iv);
		BanJi_Teacher banJi_LaoShi = list_laoshi.get(arg0);
		tv_1.setText(banJi_LaoShi.getZiwu());
		tv_2.setText(banJi_LaoShi.getName());
		iv_1.setImageResource(R.drawable.cricle2);
		return v;
	}

}
