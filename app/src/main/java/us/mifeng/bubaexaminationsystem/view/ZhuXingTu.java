package us.mifeng.bubaexaminationsystem.view;

import java.util.List;



import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.adapter.ZhuXingTuAdapter;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.bean.ZhouXueyuan;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;




import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class ZhuXingTu extends FrameLayout{
	private List<ZhouBanji> list;
	private Context context;
	private ListView lv;
	private double iattendence;
	public ZhuXingTuAdapter adapter;
	public ZhuXingTu(Context context,List<ZhouBanji> list) {
		super(context);
		this.list = list;
		this.context = context;
		creatBarChat(list, context);
	}
	public ZhuXingTu(Context context) {
		super(context);
		this.context = context;
		TextView textView = new TextView(context);
		textView.setText("暂无记录");
		textView.setGravity(Gravity.CENTER);
		this.addView(textView);
		}
	
	public void creatBarChat(List<ZhouBanji> list2,Context context){
		View v = LayoutInflater.from(context).inflate(R.layout.zhuxingtuview,ZhuXingTu.this, false);
		lv = (ListView) v.findViewById(R.id.lv);
		//排序后的集合
		paixu(list2);
		//得到出勤率最大的数字
		Log.e("fbw",list2.size()+"");
		if (list2.size()==0) {
			ToShow.show(getContext(), "暂无数据");
		}else{
			String attendence = list2.get(list2.size()-1).getAttendence();
			iattendence = Double.parseDouble(attendence);
		}
		//将最大的view 的长度设置为320dp，并且转换为pxֵ
		int px = CommonUtils.dip2px(context, 320);
		adapter = new ZhuXingTuAdapter(list2, context, iattendence, px);
		lv.setAdapter(adapter);
		this.addView(v);
		
	}
	public void paixu(List<ZhouBanji> list){
		for (int i = 0; i < list.size()-1; i++) {
			for (int j = i+1; j < list.size(); j++) {
				String chuqinlvi = list.get(i).getAttendence().trim().replace("%", "");
				String chuqinlvj = list.get(j).getAttendence().trim().replace("%", "");
				double inti = Double.parseDouble(chuqinlvi);
				double intj = Double.parseDouble(chuqinlvj);
				if (inti>intj) {
					ZhouBanji temp = list.get(i);
					list.add(i, list.get(j));
					list.remove(i+1);
					list.add(j,temp);
					list.remove(j+1);
				}
			}
		}
		Log.e("fbw", "是否排序");
		for (ZhouBanji zhouBanji : list) {
			Log.e("fbw", zhouBanji.attendence);
		}
	}
	

	
	

}
