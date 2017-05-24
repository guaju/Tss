package us.mifeng.bubaexaminationsystem.adapter;

import java.util.List;

import us.mifeng.bubaexaminationsystem.activity.ClassOverall_Activity;
import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.Weiji;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAp extends BaseAdapter {
	List<Weiji> mList;
	Context context;
	public MyAp(Context context,List<Weiji> lists){
		mList=lists;
		this.context=context;
	}
	

	public MyAp(List<Weiji> list) {
		mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
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
		View v = View.inflate(context,
				R.layout.myitem_lv, null);
		LinearLayout ll = (LinearLayout) v.findViewById(R.id.ll);
		ll.setBackgroundColor(Color.parseColor("#ffffff"));
		TextView wjrm = (TextView) v.findViewById(R.id.tv_weijirenming);
		TextView wjqk = (TextView) v.findViewById(R.id.tv_wjqk);
		String name = mList.get(position).getName();
		String qingkuang = mList.get(position).getQingkuang();
		if ("违纪情况：旷课".equals(qingkuang)) {//DC143C
			ll.setBackgroundColor(Color.parseColor("#FF4500"));
		}
//		else if ("缺勤情况：请假".equals(qingkuang)||"缺勤情况：休学".equals(qingkuang)) {
//			ll.setBackgroundColor(Color.parseColor("#E3FFE3"));
//		}
		else if ("缺勤情况：请假".equals(qingkuang)) {
			ll.setBackgroundColor(Color.parseColor("#E3FFE3"));
		}
		else if ("缺勤情况：休学".equals(qingkuang)) {
			ll.setBackgroundColor(Color.parseColor("#CAFF70"));
		}
		else if ("缺勤情况：停课".equals(qingkuang)) {
			name=name.replace("缺勤人名", "姓名");
			qingkuang="状态：停课";
			ll.setBackgroundColor(Color.parseColor("#aaaaaa"));
		}
		else if ("缺勤情况：三方请假".equals(qingkuang)) {
			name=name.replace("缺勤人名", "姓名");
			qingkuang="状态：三方请假";
			ll.setBackgroundColor(Color.parseColor("#79CDCD"));
		}
		else {
			//这是之前的紫色
//			ll.setBackgroundColor(Color.parseColor("#DFDFFF"));
			ll.setBackgroundColor(Color.parseColor("#FF8686"));
		}
		wjrm.setText(name);
		wjqk.setText(qingkuang);

		return v;
	}

}
