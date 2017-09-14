package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.YueHuiZong_CollageWeiji;
import us.mifeng.bubaexaminationsystem.bean.YueHuiZong_Class;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class YueHuiZongAdapter extends BaseExpandableListAdapter {

	Context context;
	ArrayList<YueHuiZong_CollageWeiji> list;
	YueHuiZong_CollageWeiji yueHuiZong_CollageWeiji;
	ArrayList<YueHuiZong_Class> listc;
	YueHuiZong_Class classs;
	
	public YueHuiZongAdapter(Context context,ArrayList<YueHuiZong_CollageWeiji> list,
			YueHuiZong_CollageWeiji yueHuiZong_CollageWeiji,ArrayList<YueHuiZong_Class> listc,YueHuiZong_Class classs){
		super();
		this.context = context;
		this.list = list;
		this.yueHuiZong_CollageWeiji = yueHuiZong_CollageWeiji;
		this.listc = listc;
		this.classs = classs;
		
	}
	
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return listc.size();
	}
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition);
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).getMyclass().get(childPosition);
	}
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View v = View.inflate(context,R.layout.yuehuizong_xueyuan, null);
		TextView tv_xueyuanchuqinlv = (TextView) v.findViewById(R.id.tv_xueyuanchuqinlv);
		TextView tv_xueyuanweijilv = (TextView) v.findViewById(R.id.tv_xueyuanweijilv);
		TextView tv_collage_name = (TextView) v.findViewById(R.id.tv_collage_name);
		
		tv_xueyuanchuqinlv.setText(list.get(groupPosition).getXueYuanChuQinLv());
		tv_xueyuanweijilv.setText(list.get(groupPosition).getXueYuanWeiJiLv());
		tv_collage_name.setText(list.get(groupPosition).getCollage_name());
		
		return v;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = View.inflate(context,R.layout.yuehuizong_banji,null);
		
		TextView tv_class_name = (TextView) v.findViewById(R.id.tv_class_name);
		TextView tv_banzhuren = (TextView) v.findViewById(R.id.tv_banzhuren);
		TextView tv_renkelaoshi = (TextView) v.findViewById(R.id.tv_renkelaoshi);
		TextView tv_jishu = (TextView) v.findViewById(R.id.tv_jishu);
		TextView tv_banjichuqinlv = (TextView) v.findViewById(R.id.tv_banjichuqinlv);
		TextView tv_banjiweijilv = (TextView) v.findViewById(R.id.tv_banjiweijilv);
		
		tv_class_name.setText(listc.get(groupPosition).getClass_name());
		tv_banzhuren.setText(listc.get(groupPosition).getBanZhuRen());
		tv_renkelaoshi.setText(listc.get(groupPosition).getRenKeLaoShi());
		tv_jishu.setText(listc.get(groupPosition).getJiShu());
		tv_banjichuqinlv.setText(listc.get(groupPosition).getChuQinLv());
		tv_banjiweijilv.setText(listc.get(groupPosition).getWeiJiLv());
		
		return v;   
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
