package us.mifeng.bubaexaminationsystem.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;


import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;

public class ZhuXingTuAdapter extends BaseAdapter{
	private List<ZhouBanji> list;
	private Context context;
	private double chuqinlv;
	private int MAXWIDTH;
	
	public ZhuXingTuAdapter(List<ZhouBanji> list, Context context,double chuqinlv,int MAXWIDTH) {
		super();
		this.list = list;
		this.context = context;
		this.chuqinlv = chuqinlv;
		this.MAXWIDTH = MAXWIDTH;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = View.inflate(context, R.layout.zhuxingtuiweijitem, null);
		View view = v.findViewById(R.id.view);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.viewtranslate);
		view.setAnimation(animation);
		TextView classs = (TextView) v.findViewById(R.id.classs);
		TextView grade = (TextView) v.findViewById(R.id.grade);
		classs.setText(list.get(position).getName());
		//得到当前出勤率
		
		String current = list.get(position).getAttendence().trim().replace("%", "");
		grade.setText(list.get(position).getAttendence().trim()+"%");
		double dcurrent = Double.parseDouble(current);
		//当前出勤率按比例设置px值
		double rate=dcurrent/chuqinlv;
		double currentpx = rate*MAXWIDTH;
		LayoutParams parmas = view.getLayoutParams();
		parmas.width=(int) currentpx;
		view.setLayoutParams(parmas);
		return v;
	
	}

	

}
