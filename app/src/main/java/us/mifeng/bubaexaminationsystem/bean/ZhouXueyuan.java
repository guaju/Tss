package us.mifeng.bubaexaminationsystem.bean;

import java.util.ArrayList;



public class ZhouXueyuan {
	
	public String disciplinerate,attendance,xueyuan_name,jiShu;
	public String getJiShu() {
		return jiShu;
	}
	public void setJiShu(String jiShu) {
		this.jiShu = jiShu;
	}

	public int id;
	public ArrayList<ZhouBanji> mybanji ;
	public String getXueyuan_name() {
		return xueyuan_name;
	}
	public void setXueyuan_name(String xueyuan_name) {
		this.xueyuan_name = xueyuan_name;
	}
	public String getDisciplinerate() {
		return disciplinerate;
	}
	public void setid(int id) {
		this.id = id;
	}
	public int getid() {
		return id;
	}
	public void setDisciplinerate(String disciplinerate) {
		this.disciplinerate = disciplinerate;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public ArrayList<ZhouBanji> getMybanji() {
		return mybanji;
	}
	public void setMybanji(ArrayList<ZhouBanji> mybanji) {
		this.mybanji = mybanji;
	}
	public ZhouXueyuan() {
		super();
	}
	
	public ZhouXueyuan(String disciplinerate, String attendance,ArrayList<ZhouBanji> mybanji){
		super();
		this.disciplinerate = disciplinerate;
		this.attendance = attendance;
		this.mybanji = mybanji;
	}
	@Override
	public String toString() {
		return "ZhouXueyuan [disciplinerate=" + disciplinerate
				+ ", attendance=" + attendance + ", xueyuan_name="
				+ xueyuan_name + ", jiShu=" + jiShu + ", id=" + id
				+ ", mybanji=" + mybanji + "]";
	}
	

}
