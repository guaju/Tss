package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.activity.RiWeiJi_Activity;
import us.mifeng.bubaexaminationsystem.bean.Classs;
import us.mifeng.bubaexaminationsystem.bean.CollageWeiJi;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


public class CollageAdapter extends BaseExpandableListAdapter {
	Context context;
	ArrayList<CollageWeiJi> list;
	CollageWeiJi collageWeiji;
	ArrayList<Classs> listc;
	Classs classs ;
	
	
	public CollageAdapter(Context context, ArrayList<CollageWeiJi> list,
			CollageWeiJi collageWeiji, ArrayList<Classs> listc, Classs classs) {
		super();
		this.context = context;
		this.list = list;
		this.collageWeiji = collageWeiji;
		this.listc = listc;
		this.classs = classs;
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return listc.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition).getMyclass().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View v = View.inflate(context, R.layout.findcollage, null);
		TextView tv_yingdao = (TextView) v.findViewById(R.id.tv_yingdao);
		TextView tv_weijilv = (TextView) v.findViewById(R.id.tv_weijilv);
		TextView tv_weiji = (TextView) v.findViewById(R.id.tv_weiji);
		TextView tv_shidao = (TextView) v.findViewById(R.id.tv_shidao);
		TextView tv_chuqinlv = (TextView) v.findViewById(R.id.tv_chuqinlv);
		TextView tv_collage_name = (TextView) v.findViewById(R.id.tv_collage_name);
		tv_chuqinlv.setText(list.get(groupPosition).getchuqinlv());
		tv_shidao.setText(list.get(groupPosition).getshidao());
		tv_collage_name.setText(list.get(groupPosition).getCollage_name());
		tv_weiji.setText(list.get(groupPosition).getweij());
		tv_weijilv.setText(list.get(groupPosition).getweijilv());
		tv_yingdao.setText(list.get(groupPosition).getyingdao());
		return v;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View v = View.inflate(context, R.layout.attendanceinclass, null);
		TextView tv_weijilv = (TextView) v.findViewById(R.id.tv_class_weijilv);
		TextView tv_yingdao = (TextView) v.findViewById(R.id.tv_class_yingdao);
		TextView tv_weiji = (TextView) v.findViewById(R.id.tv_class_weiji);
		TextView tv_shidao = (TextView) v.findViewById(R.id.tv_class_shidao);
		TextView tv_chuqinlv =  (TextView) v.findViewById(R.id.tv_class_chuqinlv);
		TextView tv_class_name = (TextView) v.findViewById(R.id.tv_class_name);
		tv_chuqinlv.setText(listc.get(groupPosition).getchuqinlv());
		tv_shidao.setText(listc.get(groupPosition).getshidao());
		tv_class_name.setText(listc.get(groupPosition).getClass_name());
		tv_weiji.setText(listc.get(groupPosition).getweiji());
		tv_weijilv.setText(listc.get(groupPosition).getweijilv());
		tv_yingdao.setText(listc.get(groupPosition).getyingdao());
		
		
		return v;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
