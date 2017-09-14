package us.mifeng.bubaexaminationsystem.view;

import java.util.ArrayList;
import java.util.List;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.adapter.ZhuXingTuAdapter;
import us.mifeng.bubaexaminationsystem.adapter.ZhuXingTu_QuanYuanAdapter;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.bean.ZhouXueyuan;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class ZhuXingTu_QuanYuan extends LinearLayout{
	

	private List<ZhouXueyuan> list;
	private Context context;
	private ListView lv;
	private double iattendence;
	public ZhuXingTu_QuanYuanAdapter adapter;
	public ZhuXingTu_QuanYuan(Context context,List<ZhouXueyuan> list) {
		super(context);
		this.context = context;
		this.list = list;
		creatBarChat(list, context);
	}
	
	
	public void creatBarChat(List<ZhouXueyuan> list2,Context context){
		View v = LayoutInflater.from(context).inflate(R.layout.zhuxingtuview,ZhuXingTu_QuanYuan.this, false);
		lv = (ListView) v.findViewById(R.id.lv);
		//排序后的集合
		List<ZhouXueyuan> newlist = paixu(list2);
		//得到出勤率最大的数字
		Log.e("fbw",list2.size()+"");
		if (newlist.size()==0) {
			ToShow.show(getContext(), "暂无数据");
		}else {
			String attendence = list2.get(list2.size()-1).getAttendance();
			iattendence = Double.parseDouble(attendence);
		}
		//将最大的view 的长度设置为320dp，并且转换为pxֵ
		int px = CommonUtils.dip2px(context, 320);
		adapter = new ZhuXingTu_QuanYuanAdapter(newlist, context, iattendence, px);
		lv.setAdapter(adapter);
		this.addView(v);
		
	}
	public List<ZhouXueyuan> paixu(List<ZhouXueyuan> list){
		for (int i = 0; i < list.size()-1; i++) {
			for (int j = i+1; j < list.size(); j++) {
				String chuqinlvi = list.get(i).getAttendance();
				String chuqinlvj = list.get(j).getAttendance();
				double inti = Double.parseDouble(chuqinlvi);
				double intj = Double.parseDouble(chuqinlvj);
				if (inti>intj) {
					ZhouXueyuan temp = list.get(i);
					list.add(i, list.get(j));
					list.remove(i+1);
					list.add(j,temp);
					list.remove(j+1);
				}
			}
		}
		
		
		return list;
		
		
		
	}
	

}
