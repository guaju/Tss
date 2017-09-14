package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;
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

public class ZhuXingTuAdapterWeiJi extends BaseAdapter{
	private List<ZhouBanji> list;
	
	private Context context;
	private double chuqinlv;
	private int MAXWIDTH;
	private int count;
	private ViewHolder viewHolder;
	
	public ZhuXingTuAdapterWeiJi(List<ZhouBanji> list, Context context,double chuqinlv,int MAXWIDTH) {
		super();
		this.list=new ArrayList<ZhouBanji>();
		this.list.addAll(list);
		this.context = context;
		this.chuqinlv = chuqinlv;
		this.MAXWIDTH = MAXWIDTH;
		for (ZhouBanji zhouBanji : list) {
			System.out.println(zhouBanji.getDisciplinerate());
		}
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
		if (convertView==null) {
			System.out.println("呵呵呵");
			convertView = View.inflate(context, R.layout.zhuxingtuiweijitem, null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		viewHolder=(ViewHolder) convertView.getTag();
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.viewtranslate);
		viewHolder.v.setAnimation(animation);
		viewHolder.classes.setText(list.get(position).getName());
		String current = list.get(position).getDisciplinerate().trim().replace("%", "");
		viewHolder.grade.setText(current+"%");
		//得到当前出勤率
		double dcurrent = Double.parseDouble(current);
		//当前出勤率按比例设置px值
		double rate=dcurrent/chuqinlv;
		double currentpx = rate*MAXWIDTH;
		LayoutParams parmas = viewHolder.v.getLayoutParams();
		parmas.width=(int) currentpx;
		viewHolder.v.setLayoutParams(parmas);
		return convertView;
	}

	static class ViewHolder{
		public View v;
		public TextView grade,classes;
		public ViewHolder(View v){
			this.v=v.findViewById(R.id.view);
			grade=(TextView) v.findViewById(R.id.grade);
			classes=(TextView) v.findViewById(R.id.classs);
		}
	}

}
