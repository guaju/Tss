package us.mifeng.bubaexaminationsystem.activity;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.adapter.YueHuiZongAdapter;
import us.mifeng.bubaexaminationsystem.bean.YueHuiZong_Class;
import us.mifeng.bubaexaminationsystem.bean.YueHuiZong_CollageWeiji;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class YueHuiZong_Activity extends FragmentActivity {

	ArrayList<YueHuiZong_CollageWeiji> list = new ArrayList<YueHuiZong_CollageWeiji>();
	YueHuiZong_CollageWeiji yueHuiZong_CollageWeiji = new YueHuiZong_CollageWeiji();
	ArrayList<YueHuiZong_Class> listc = new ArrayList<YueHuiZong_Class>();
	YueHuiZong_Class classs = new YueHuiZong_Class();
	private ExpandableListView lv;
    private TextView yue_tv;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuehuizong_activity);
        initView();
        classs.setBanZhuRen("董丽霞");
        classs.setChuQinLv("95.38%");
        classs.setClass_name("门店1503A");
        classs.setRenKeLaoShi("高稳");
        classs.setWeiJiLv("0%");
        classs.setJiShu("13");
        listc.add(classs);
        yueHuiZong_CollageWeiji.setCollage_name("工商");
        yueHuiZong_CollageWeiji.setMyclass(listc);
        yueHuiZong_CollageWeiji.setXueYuanChuQinLv("96.86%");
        yueHuiZong_CollageWeiji.setXueYuanWeiJiLv("0.65%");
        
        list.add(yueHuiZong_CollageWeiji);
        
        YueHuiZongAdapter myAdapter = new YueHuiZongAdapter(YueHuiZong_Activity.this, list, yueHuiZong_CollageWeiji, listc, classs);
        lv.setAdapter(myAdapter);
        
	}
	public void click(View v){
		finish();
	}
    private void initView() {
		yue_tv = (TextView) findViewById(R.id.yue_tv);
		lv = (ExpandableListView) findViewById(R.id.lv);
	}

	public void click(){
		//startActivity(new Intent(YueHuiZong_Activity.this,       .class));
    }
}
