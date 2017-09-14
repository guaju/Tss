package us.mifeng.bubaexaminationsystem.bean;

public class Classs {
	String class_name,banzhuren,renkelaoshi,jiaoxuejindu,weijirenming,weijiqingkuang,
	yingdao,weijilv,weiji,shidao,chuqinlv,time,id,date1,date2;
	
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getBanzhuren() {
		return banzhuren;
	}
	public void setBanzhuren(String banzhuren) {
		this.banzhuren = banzhuren;
	}
	public String getRenkelaoshi() {
		return renkelaoshi;
	}
	public void setRenkelaoshi(String renkelaoshi) {
		this.renkelaoshi = renkelaoshi;
	}
	public String getJiaoxuejindu() {
		return jiaoxuejindu;
	}
	public void setJiaoxuejindu(String jiaoxuejindu) {
		this.jiaoxuejindu = jiaoxuejindu;
	}
	public String getWeijirenming() {
		return weijirenming;
	}
	public void setWeijirenming(String weijirenming) {
		this.weijirenming = weijirenming;
	}
	public String getWeijiqingkuang() {
		return weijiqingkuang;
	}
	public void setWeijiqingkuang(String weijiqingkuang) {
		this.weijiqingkuang = weijiqingkuang;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String collage_name){
		this.class_name = collage_name;
	}

	public String getyingdao() {
		return yingdao;
	}

	public void setyingdao(String yingdao) {
		this.yingdao = yingdao;
	}

	public String getweijilv() {
		return weijilv;
	}

	public void setweijilv(String weijilv) {
		this.weijilv = weijilv;
	}

	public String getweiji() {
		return weiji;
	}

	public void setweiji(String weiji) {
		this.weiji = weiji;
	}

	public String getshidao() {
		return shidao;
	}

	public Classs(String collage_name, String yingdao,
			String weijilv, String weiji,
			String shidao, String chuqinlv,String bzr,String rkls,
			String jxjd,String wjrm,String wjqk,String date1, String date2) {
		super();
		this.class_name = class_name;
		this.yingdao = yingdao;
		this.weijilv = weijilv;
		this.weiji = weiji;
		this.shidao = shidao;
		this.chuqinlv = chuqinlv;
		this.date1 = date1;
		this.date2 = date2;
		this.banzhuren=bzr;
		renkelaoshi=rkls;
		jiaoxuejindu=jxjd;
		weijirenming=wjrm;
		weijiqingkuang=wjqk;
	}

	public Classs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setshidao(String shidao) {
		this.shidao = shidao;
	}

	public String getchuqinlv() {
		return chuqinlv;
	}

	public void setchuqinlv(String chuqinlv) {
		this.chuqinlv = chuqinlv;
	}
}
