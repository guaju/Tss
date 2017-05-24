package us.mifeng.bubaexaminationsystem.activity;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.bean.ZhouXueyuan;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ZhouActivity extends FragmentActivity{

    private ExpandableListView elv;
    private ArrayList<ZhouXueyuan> List=new ArrayList<ZhouXueyuan>();
    private ArrayList<ZhouBanji> aList=new ArrayList<ZhouBanji>();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhou);
        //找ID
        initView();
        //添加数据
        iniData();
        
        MyAdapter myAdapter = new MyAdapter();
        elv.setAdapter(myAdapter);
        
        
    }
	private void iniData() {
		ZhouBanji banji = new ZhouBanji();
		ZhouXueyuan xueyuan = new ZhouXueyuan();
		banji.setAttendence("98.00%");
		banji.setBase("15");
		banji.setDisciplinerate("0.05%");
		banji.setName("门店1503A");
		banji.setTeacher("高稳");
		banji.setHeadheadteacher("董丽霞");
		aList.add(banji);
		xueyuan.setAttendance("96.50%");
		xueyuan.setDisciplinerate("1.25%");
		xueyuan.setMybanji(aList);
		xueyuan.setXueyuan_name("工商");
		
		List.add(xueyuan);
		
		
		
	}
	public void click(View v){
		finish();
	}
	//找ID
	private void initView() {
		elv=(ExpandableListView) findViewById(R.id.elv);
	}
	
	class MyAdapter extends BaseExpandableListAdapter{

		@Override
		public int getGroupCount() {
			return List.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return aList.size();
		
		}

		@Override
		public Object getGroup(int groupPosition) {
			return List.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return List.get(groupPosition).getMybanji().get(childPosition);
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
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
		
		

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View v = View.inflate(ZhouActivity.this, R.layout.zhou_item_xueyuan, null);
			TextView tv_attendence=(TextView) v.findViewById(R.id.tv_attendence);
			TextView tv_collage_name=(TextView) v.findViewById(R.id.tv_collage_name);
			TextView tv_discipline=(TextView) v.findViewById(R.id.tv_discipline);
			ZhouXueyuan xueyuan = List.get(groupPosition);
			tv_attendence.setText(xueyuan.getAttendance());
			tv_collage_name.setText(xueyuan.getXueyuan_name());
			tv_discipline.setText(xueyuan.getDisciplinerate());
			
			
			
			return v;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			View v = View.inflate(ZhouActivity.this, R.layout.zhou_item_banji, null);
			TextView tv_banji_attendence=(TextView) v.findViewById(R.id.tv_banji_attendence);
			TextView tv_banji_base=(TextView) v.findViewById(R.id.tv_banji_base);
			TextView tv_banji_disciplinerate=(TextView) v.findViewById(R.id.tv_banji_disciplinerate);
			TextView tv_banji_headteacher=(TextView) v.findViewById(R.id.tv_banji_headteacher);
			TextView tv_banji_name=(TextView) v.findViewById(R.id.tv_banji_name);
			TextView tv_banji_teacher=(TextView) v.findViewById(R.id.tv_banji_teacher);
			
			ZhouBanji banji = aList.get(childPosition);
			tv_banji_attendence.setText(banji.getAttendence());
			tv_banji_base.setText(banji.getBase());
			tv_banji_disciplinerate.setText(banji.getDisciplinerate());
			tv_banji_headteacher.setText(banji.getHeadheadteacher());
			tv_banji_name.setText(banji.getName());
			tv_banji_teacher.setText(banji.getTeacher());
			
			return v;
		}

		
	}
	


  
}
