package us.mifeng.bubaexaminationsystem.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class XueYuanHuiZong_ViewPager extends FragmentPagerAdapter {
	private ArrayList<Fragment> list;

	public XueYuanHuiZong_ViewPager(FragmentManager fm) {
		super(fm);
	}
	public void setList(ArrayList<Fragment> li){
		list=new ArrayList<Fragment>();
		this.list=li;
	}
	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

}
