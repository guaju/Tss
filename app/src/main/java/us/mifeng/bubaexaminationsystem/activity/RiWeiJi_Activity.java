package us.mifeng.bubaexaminationsystem.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import us.mifeng.bubaexaminationsystem.adapter.CollageAdapter;
import us.mifeng.bubaexaminationsystem.bean.Classs;
import us.mifeng.bubaexaminationsystem.bean.CollageWeiJi;
import us.mifeng.bubaexaminationsystem.fragment.SetDateDialog;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

public class RiWeiJi_Activity extends FragmentActivity {
	
	ArrayList<CollageWeiJi> list = new ArrayList<CollageWeiJi>();
	CollageWeiJi collageWeiji = new CollageWeiJi();
	ArrayList<Classs> listc = new ArrayList<Classs>();
	Classs classs = new Classs();
	private ExpandableListView lv;
	public TextView tv_data;
	private String date;
	private GestureDetector gd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riweiji_activity);
        classs.setchuqinlv("100%");
        classs.setshidao("13");
        classs.setweiji("19");
        classs.setClass_name("门店1503A");
        classs.setyingdao("19");
        classs.setweijilv("0.00%");
        listc.add(classs);
        collageWeiji.setyingdao("29");
        collageWeiji.setCollage_name("工商");
        collageWeiji.setshidao("29");
        collageWeiji.setweijilv("0.00%");
        collageWeiji.setchuqinlv("100%");
        collageWeiji.setMyclass(listc);
        list.add(collageWeiji);
        gd = new GestureDetector(RiWeiJi_Activity.this, new MyGd());
        initView();
        data();
        CollageAdapter myAdapter = new CollageAdapter(RiWeiJi_Activity.this, list, collageWeiji, listc, classs);
        lv.setAdapter(myAdapter);
        lv.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
//				ToShow.show(RiWeiJi_Activity.this, list.get(groupPosition).getMyclass().get(childPosition).getClass_name());
				startActivity(new Intent(RiWeiJi_Activity.this,ClassOverall_Activity.class));
				return false;
			}
		});
    }
    public void click (View v){
    	finish();
    }
	private void initView() {
		lv = (ExpandableListView) findViewById(R.id.disciplinerrateinday);
		tv_data = (TextView) findViewById(R.id.tv_riqi);
		
	}
	private void data() {
		SimpleDateFormat  sDateFormat = new SimpleDateFormat("yyyy-MM-dd");       
        date = sDateFormat.format(new java.util.Date());  
        tv_data.setText(date);
	}
	public void choicedate(View v){
		SetDateDialog sdd = new SetDateDialog();
		sdd.show(getSupportFragmentManager(), "haha");
	}
	public class MyGd extends SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			float x1 = e1.getX();
			float x2 = e2.getX();
			if((x1-x2)>50){
				Log.e("", "滑动了");
				ToShow.show(RiWeiJi_Activity.this, "右滑动");
				startActivity(new Intent(RiWeiJi_Activity.this,YueHuiZong_Activity.class));
			}
			if((x2-x1)>50){
				Log.e("", "zuo动了");
				ToShow.show(RiWeiJi_Activity.this, "zuo滑动");
				startActivity(new Intent(RiWeiJi_Activity.this,ZhouActivity.class));
			}
			return true;
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gd.onTouchEvent(event);
	}
	
	


   
    
}
