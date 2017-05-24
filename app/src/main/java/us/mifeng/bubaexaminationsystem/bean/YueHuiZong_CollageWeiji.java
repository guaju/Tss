package us.mifeng.bubaexaminationsystem.bean;

import java.util.ArrayList;

public class YueHuiZong_CollageWeiji {
	
	String collage_name,xueyuanchuqinlv,xueyuanweijilv;
	ArrayList<YueHuiZong_Class> myclass;
	public YueHuiZong_CollageWeiji() {
		super();
		// TODO Auto-generated constructor stub
	}
	public YueHuiZong_CollageWeiji(String collage_name, 
			String xueyuanchuqinlv, String xueyuanweijilv, ArrayList<YueHuiZong_Class> myclass) {
		super();
		this.collage_name = collage_name;
		this.xueyuanchuqinlv = xueyuanchuqinlv;
		this.xueyuanweijilv = xueyuanweijilv;
		this.myclass = myclass;
	}
	public String getCollage_name() {
		return collage_name;
	}
	public void setCollage_name(String collage_name) {
		this.collage_name = collage_name;
	}
	
	public String getXueYuanChuQinLv() {
		return xueyuanchuqinlv;
	}
	public void setXueYuanChuQinLv(String xueyuanchuqinlv) {
		this.xueyuanchuqinlv = xueyuanchuqinlv;
	}
	
	public String getXueYuanWeiJiLv() {
		return xueyuanweijilv;
	}
	public void setXueYuanWeiJiLv(String xueyuanweijilv) {
		this.xueyuanweijilv = xueyuanweijilv;
	}
	
	public ArrayList<YueHuiZong_Class> getMyclass() {
		return myclass;
	}
	public void setMyclass(ArrayList<YueHuiZong_Class> myclass) {
		this.myclass = myclass;
	}
}
