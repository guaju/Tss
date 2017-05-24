package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class Chart_PagerAdapter extends PagerAdapter {
	ArrayList<View> list;
	public Chart_PagerAdapter (ArrayList<View> li){
		this.list=li;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list.get(position));
		return list.get(position) ;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}

}
