package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.Classs;
import us.mifeng.bubaexaminationsystem.bean.CollageWeiJi;
import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class BaseExListView_Ri extends BaseExpandableListAdapter {
	private Context conte;
	private ArrayList<CollageWeiJi> myCollage;
	private ArrayList<Classs> myClass;
	public BaseExListView_Ri (Context context,ArrayList<CollageWeiJi> myCollage,
			ArrayList<Classs> myClass){
		this.conte=context;
		this.myClass = myClass;
		this.myCollage = myCollage;
	} 
	public void setList(ArrayList<CollageWeiJi> myCollage,ArrayList<Classs> myClass){
		this.myClass = myClass;
		this.myCollage = myCollage;
	}
	@Override
	public int getGroupCount() {//大标题个数
		return myCollage.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {//小标题个数
		return myClass.size();
	}
	@Override
	public Object getGroup(int groupPosition) {
		return myCollage.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return myCollage.get(groupPosition).getMyclass().get(childPosition);
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
		View v = View.inflate(conte, R.layout.ri_title, null);
		TextView tv_yingdao = (TextView) v.findViewById(R.id.tv_yingdao);
		TextView tv_weijilv = (TextView) v.findViewById(R.id.tv_weijilv);
		TextView tv_weiji = (TextView) v.findViewById(R.id.tv_weiji);
		TextView tv_shidao = (TextView) v.findViewById(R.id.tv_shidao);
		TextView tv_chuqinlv = (TextView) v.findViewById(R.id.tv_chuqinlv);
		TextView tv_collage_name = (TextView) v.findViewById(R.id.tv_collage_name);
		tv_chuqinlv.setText(myCollage.get(groupPosition).getchuqinlv());
		tv_shidao.setText(myCollage.get(groupPosition).getshidao());
		System.out.println(myCollage.get(groupPosition).getCollage_name()+"----");
		tv_collage_name.setText(myCollage.get(groupPosition).getCollage_name());
		tv_weiji.setText(myCollage.get(groupPosition).getweij());
		tv_weijilv.setText(myCollage.get(groupPosition).getweijilv());
		tv_yingdao.setText(myCollage.get(groupPosition).getyingdao());
		if (groupPosition==(myCollage.size()-1)&&myCollage.get(groupPosition).getid()==0) {
			
			v.setBackgroundColor(Color.parseColor("#B8B8B8"));
			setBlod(tv_chuqinlv); 
			setBlod(tv_shidao); 
			setBlod(tv_collage_name); 
			setBlod(tv_weijilv); 
			setBlod(tv_yingdao); 
			setBlod(tv_weiji); 
			
		}
		
		
		return v;
	}
	private void setBlod(TextView tv_chuqinlv) {
		TextPaint tp = tv_chuqinlv.getPaint(); 
		tp.setFakeBoldText(true);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		System.out.println("heihei");
		
		View v = View.inflate(conte, R.layout.ri_context, null);
		TextView tv_weijilv = (TextView) v.findViewById(R.id.tv_class_weijilv);
		TextView tv_yingdao = (TextView) v.findViewById(R.id.tv_class_yingdao);
		TextView tv_weiji = (TextView) v.findViewById(R.id.tv_class_weiji);
		TextView tv_shidao = (TextView) v.findViewById(R.id.tv_class_shidao);
		TextView tv_chuqinlv =  (TextView) v.findViewById(R.id.tv_class_chuqinlv);
		TextView tv_class_name = (TextView) v.findViewById(R.id.tv_class_name);
		tv_chuqinlv.setText(myClass.get(childPosition).getchuqinlv());
		tv_shidao.setText(myClass.get(childPosition).getshidao());
		tv_class_name.setText(myClass.get(childPosition).getClass_name());
		tv_weiji.setText(myClass.get(childPosition).getweiji());
		tv_weijilv.setText(myClass.get(childPosition).getweijilv());
		tv_yingdao.setText(myClass.get(childPosition).getyingdao());
		return v;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
