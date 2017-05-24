package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.bean.BanJi_Student;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Student_Adapter extends BaseAdapter {
	private ArrayList<BanJi_Student> list_student;
	private Context content;
	@Override
	public int getCount() {
		return list_student.size();
	}
	public void setList(ArrayList<BanJi_Student> li, Context con){
		this.list_student=li;
		this.content=con;
	}
	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View v = View.inflate(content,
				R.layout.grivlist_fujian, null);
		TextView tv_2 = (TextView) v.findViewById(R.id.gv_fujian_tv2);
		TextView tv_1 = (TextView) v.findViewById(R.id.gv_fujian_tv1);
		ImageView iv_1 = (ImageView) v.findViewById(R.id.gv_fujian_iv);
		ImageView id_iv_status = (ImageView) v.findViewById(R.id.id_iv_status);
		id_iv_status.setVisibility(View.INVISIBLE);
		
		BanJi_Student banJi_student = list_student.get(arg0);
		
		Log.e("~~~~~~~~", banJi_student.getZhuangtai());
		
		if ((banJi_student.getDisciplinarycategory()!=0)&&banJi_student.getDisciplinarycategory()<12&&0<banJi_student.getDisciplinarycategory()||banJi_student.getDisciplinarycategory()==99) {
			id_iv_status.setBackgroundResource(R.drawable.status_weiji);
			id_iv_status.setVisibility(View.VISIBLE);
		}else if("3".equals(banJi_student.getZhuangtai())){
			id_iv_status.setBackgroundResource(R.drawable.status_qingjia);
			id_iv_status.setVisibility(View.VISIBLE);
		}
		else if("9".equals(banJi_student.getZhuangtai())){
			id_iv_status.setBackgroundResource(R.drawable.status_xiuxue);
			id_iv_status.setVisibility(View.VISIBLE);
		}
		else if("1000".equals(banJi_student.getZhuangtai())){
			id_iv_status.setBackgroundResource(R.drawable.status_weiji);
			id_iv_status.setVisibility(View.VISIBLE);
		}
		else if("4".equals(banJi_student.getZhuangtai())){
			id_iv_status.setBackgroundResource(R.drawable.status_tingke);
			id_iv_status.setVisibility(View.VISIBLE);
		}
		else if("5".equals(banJi_student.getZhuangtai())){
			id_iv_status.setBackgroundResource(R.drawable.sanfang);
			id_iv_status.setVisibility(View.VISIBLE);
		}
		else if("7".equals(banJi_student.getZhuangtai())){
			id_iv_status.setBackgroundResource(R.drawable.jiuye1);
			id_iv_status.setVisibility(View.VISIBLE);
		}
		
		tv_1.setText(banJi_student.getName());
		tv_2.setText(banJi_student.getSex());
		iv_1.setImageResource(R.drawable.cricle);
		return v;
	}

}
