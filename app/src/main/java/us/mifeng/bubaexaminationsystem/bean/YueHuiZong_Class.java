package us.mifeng.bubaexaminationsystem.bean;

public class YueHuiZong_Class {
	String class_name,banzhuren,renkelaoshi,jishu,chuqinlv,weijilv;
	
	public String getClass_name(){
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
	public String getBanZhuRen(){
		return banzhuren;
	}
	public void setBanZhuRen(String banzhuren){
		this.banzhuren=banzhuren;
	}
	
	public String getRenKeLaoShi() {
		return renkelaoshi;
	}
	public void setRenKeLaoShi(String renkelaoshi){
		this.renkelaoshi=renkelaoshi;
	}
	
	public String getChuQinLv() {
		return chuqinlv;
	}
	public void setChuQinLv(String chuqinlv){
		this.chuqinlv=chuqinlv;
	}
	
	public String getWeiJiLv() {
		return weijilv;
	}
	public void setWeiJiLv(String weijilv){
		this.weijilv=weijilv;
	}
	
	public String getJiShu() {
		return jishu;
	}
	public void setJiShu(String jishu){
		this.jishu=jishu;
	}
	public YueHuiZong_Class(){
		super();
		// TODO Auto-generated constructor stub
	}
	
	public YueHuiZong_Class(String collage_name, String banzhuren,
			String renkelaoshi, String chuqinlv,
			String weijilv){
		super();
		this.class_name = class_name;
		this.banzhuren = banzhuren;
		this.renkelaoshi = renkelaoshi;
		this.chuqinlv = chuqinlv;
		this.weijilv = weijilv;
	}
}
