package us.mifeng.bubaexaminationsystem.view;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import us.mifeng.bubaexaminationsystem.activity.R;
import us.mifeng.bubaexaminationsystem.adapter.ZhuXingTuAdapter;
import us.mifeng.bubaexaminationsystem.adapter.ZhuXingTuAdapterWeiJi;
import us.mifeng.bubaexaminationsystem.bean.ZhouBanji;
import us.mifeng.bubaexaminationsystem.utils.CommonUtils;
import us.mifeng.bubaexaminationsystem.utils.ToShow;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;


public class ZhuXingTuWeiJi extends LinearLayout{
	private List<ZhouBanji> list;
	private Context context;
	private ListView lv;
	private double iattendence;
	public ZhuXingTuAdapterWeiJi adapter;
	public ZhuXingTuWeiJi(Context context,List<ZhouBanji> list) {
		super(context);
		this.list = list;
		this.context = context;
		creatBarChat(list, context);
	}
	
	public void creatBarChat(List<ZhouBanji> list2,Context context){
		paixu(list2);
		//20160820已经保证排序无问题
		View v = LayoutInflater.from(context).inflate(R.layout.zhuxingtuview,ZhuXingTuWeiJi.this, false);
		lv = (ListView) v.findViewById(R.id.lv);
		//排序后的集合
		
		//得到出勤率最大的数字
		if (list2.size()==0) {
			ToShow.show(getContext(), "暂无数据");
		}else{
			String trim = list2.get(0).getDisciplinerate().trim().replace("%", "");
			iattendence =Double.parseDouble(trim); 
		}
		//将最大的view 的长度设置为320dp，并且转换为pxֵ
		int px = CommonUtils.dip2px(context, 320);
		adapter = new ZhuXingTuAdapterWeiJi(list2, context, iattendence, px);
		lv.setAdapter(adapter);
		this.addView(v);
		
	}
	public void paixu(List<ZhouBanji> list){
		Collections.sort(list, new Comparator<ZhouBanji>() {

			@Override
			public int compare(ZhouBanji lhs, ZhouBanji rhs) {
				String lhsd = lhs.getDisciplinerate().trim().replace("%", "");
				String rhsd = rhs.getDisciplinerate().trim().replace("%", "");
				double lhsInteger = Double.parseDouble(lhsd);
				double rhsInteger = Double.parseDouble(rhsd);
				if (lhsInteger>rhsInteger) {
					return -1;
				}
				else {
					return 1;
				}
			}
		});
	}
	

	
	

}
