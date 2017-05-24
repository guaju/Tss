package us.mifeng.bubaexaminationsystem.bean;

import java.util.ArrayList;

public class CollageWeiJi {
	String collage_name,yingdao,weijilv,weij,shidao,chuqinlv;
	int id;
	ArrayList<Classs> myclass ;
	public CollageWeiJi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollageWeiJi(String collage_name, String yingdao,
			String weijilv, String weij,
			String shidao, String chuqinlv, ArrayList<Classs> myclass,int id) {
		super();
		this.collage_name = collage_name;
		this.yingdao = yingdao;
		this.weijilv = weijilv;
		this.weij = weij;
		this.shidao = shidao;
		this.chuqinlv = chuqinlv;
		this.myclass = myclass;
		this.id = id ;
	}
	public String getCollage_name() {
		return collage_name;
	}
	public void setCollage_name(String collage_name) {
		this.collage_name = collage_name;
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
	public String getweij() {
		return weij;
	}
	public void setweij(String weij) {
		this.weij = weij;
	}
	public String getshidao() {
		return shidao;
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
	public ArrayList<Classs> getMyclass() {
		return myclass;
	}
	public void setMyclass(ArrayList<Classs> myclass) {
		this.myclass = myclass;
	}
	public void setid(int id){
		this.id = id;
	}
	public int getid(){
		return id;
		
	}
	

	
}
