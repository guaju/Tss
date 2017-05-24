package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.bean.ZhouXueyuan;
import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class BaseExListView_Zhou extends BaseExpandableListAdapter {
	private Context conte;
	private ArrayList<ZhouXueyuan> mycollage;
	private ArrayList<ZhouBanji> myclass ;
	
	public BaseExListView_Zhou(Context conte, ArrayList<ZhouXueyuan> mycollage,
			 ArrayList<ZhouBanji> myclass) {
		super();
		this.conte = conte;
		this.mycollage = mycollage;
		this.myclass = myclass;
	}
	
	public int getGroupCount() {//大标题个数
		return mycollage.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {//小标题个数
		return myclass.size();
	}
	@Override
	public Object getGroup(int groupPosition) {
		return mycollage.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mycollage.get(groupPosition).getMybanji().get(childPosition);
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
		View v = View.inflate(conte, R.layout.zhou_title, null);
		TextView tv_collage = (TextView) v.findViewById(R.id.tv_collage_name);
		TextView tv_yingdao = (TextView) v.findViewById(R.id.tv_yingdao);
		TextView tv_shidao = (TextView) v.findViewById(R.id.tv_shidao);
		tv_collage.setText(mycollage.get(groupPosition).getXueyuan_name());
		tv_yingdao.setText(mycollage.get(groupPosition).getAttendance()+"%");
		tv_shidao.setText(mycollage.get(groupPosition).getDisciplinerate()+"%");
		if (mycollage.get(groupPosition).getXueyuan_name().equals("全校")) {
			tv_yingdao.setText(mycollage.get(groupPosition).getAttendance());
			tv_shidao.setText(mycollage.get(groupPosition).getDisciplinerate());
			v.setBackgroundColor(Color.parseColor("#B8B8B8"));
			setBlod(tv_collage); 
			setBlod(tv_yingdao); 
			setBlod(tv_shidao); 
		}
		
		return v;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View v = View.inflate(conte, R.layout.zhou_context, null);
		TextView tv_class_name = (TextView) v.findViewById(R.id.tv_class_name);
		TextView tv_class_banzhuren = (TextView) v.findViewById(R.id.tv_class_teachers);
		TextView tv_class_renkelaoshi = (TextView) v.findViewById(R.id.tv_subject_teacher);
		TextView tv_class_jishu = (TextView) v.findViewById(R.id.tv_class_jishu);
		TextView tv_class_chuqinlv = (TextView) v.findViewById(R.id.tv_class_chuqinlv);
		TextView tv_class_weijilv = (TextView) v.findViewById(R.id.tv_class_weijilv);
		ZhouBanji zhouBanji = myclass.get(childPosition);
		/*tv_class_name.setText(mycollage.get(groupPosition).getMybanji().get(childPosition).getName());
		tv_class_banzhuren.setText(mycollage.get(groupPosition).getMybanji().get(childPosition).getHeadheadteacher());
		tv_class_renkelaoshi.setText(mycollage.get(groupPosition).getMybanji().get(childPosition).getTeacher());
		tv_class_jishu.setText(mycollage.get(groupPosition).getMybanji().get(childPosition).getBase());
		tv_class_chuqinlv.setText(mycollage.get(groupPosition).getMybanji().get(childPosition).getAttendence());
		tv_class_weijilv.setText(mycollage.get(groupPosition).getMybanji().get(childPosition).getDisciplinerate());*/
		tv_class_banzhuren.setText(zhouBanji.getTeacher());
		tv_class_renkelaoshi.setText(zhouBanji.getHeadheadteacher());
		tv_class_name.setText(zhouBanji.getName());
		tv_class_jishu.setText(zhouBanji.getBase());
		tv_class_chuqinlv.setText(zhouBanji.getAttendence()+"%");
		tv_class_weijilv.setText(zhouBanji.getDisciplinerate()+"%");
		return v;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	private void setBlod(TextView tv_chuqinlv) {
		TextPaint tp = tv_chuqinlv.getPaint(); 
		tp.setFakeBoldText(true);
	}
}
