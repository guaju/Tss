package us.mifeng.bubaexaminationsystem.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class KeShi_BanJI implements Parcelable{
	private String  class_teacher,teacher,jiaoxuejindu,weijiqingkuang,lsqk,wjrm,
	shiDao,yingDao,chuQinLv,weiJiLv,weiJiRenShu,qingKuang,class_id,time,queqin,tingke;
	private int keShi;
	private ArrayList<Weiji> weijiLists;
	public ArrayList<Weiji> getWeijiLists() {
		return weijiLists;
	}

	public void setWeijiLists(ArrayList<Weiji> weijiLists) {
		this.weijiLists = weijiLists;
	}

	public String getLsqk() {
		return lsqk;
	}

	public String getTingke() {
		return tingke;
	}

	public void setTingke(String tingke) {
		this.tingke = tingke;
	}

	public String getQueqin() {
		return queqin;
	}

	public void setQueqin(String queqin) {
		this.queqin = queqin;
	}

	public String getWjrm() {
		return wjrm;
	}

	public void setWjrm(String wjrm) {
		this.wjrm = wjrm;
	}

	public void setLsqk(String lsqk) {
		this.lsqk = lsqk;
	}

	public String getClass_teacher() {
		return class_teacher;
	}

	public void setClass_teacher(String class_teacher) {
		this.class_teacher = class_teacher;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getJiaoxuejindu() {
		return jiaoxuejindu;
	}

	public void setJiaoxuejindu(String jiaoxuejindu) {
		this.jiaoxuejindu = jiaoxuejindu;
	}

	public String getWeijiqingkuang() {
		return weijiqingkuang;
	}

	public void setWeijiqingkuang(String weijiqingkuang) {
		this.weijiqingkuang = weijiqingkuang;
	}

	public String getShiDao() {
		return shiDao;
	}

	public void setShiDao(String shiDao) {
		this.shiDao = shiDao;
	}

	public String getYingDao() {
		return yingDao;
	}

	public void setYingDao(String yingDao) {
		this.yingDao = yingDao;
	}

	public String getChuQinLv() {
		return chuQinLv;
	}

	public void setChuQinLv(String chuQinLv) {
		this.chuQinLv = chuQinLv;
	}

	public String getWeiJiLv() {
		return weiJiLv;
	}

	public void setWeiJiLv(String weiJiLv) {
		this.weiJiLv = weiJiLv;
	}

	public String getWeiJiRenShu() {
		return weiJiRenShu;
	}

	public void setWeiJiRenShu(String weiJiRenShu) {
		this.weiJiRenShu = weiJiRenShu;
	}

	public int getKeShi() {
		return keShi;
	}

	public void setKeShi(int keShi) {
		this.keShi = keShi;
	}

	public String getQingKuang() {
		return qingKuang;
	}

	public void setQingKuang(String qingKuang) {
		this.qingKuang = qingKuang;
	}

	public String getClass_id() {
		return class_id;
	}

	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "KeShi_BanJI [class_teacher=" + class_teacher + ", teacher="
				+ teacher + ", jiaoxuejindu=" + jiaoxuejindu
				+ ", weijiqingkuang=" + weijiqingkuang + ", lsqk=" + lsqk
				+ ", wjrm=" + wjrm + ", shiDao=" + shiDao + ", yingDao="
				+ yingDao + ", chuQinLv=" + chuQinLv + ", weiJiLv=" + weiJiLv
				+ ", weiJiRenShu=" + weiJiRenShu + ", qingKuang=" + qingKuang
				+ ", class_id=" + class_id + ", time=" + time + ", queqin="
				+ queqin + ", tingke=" + tingke + ", keShi=" + keShi
				+ ", weijiLists=" + weijiLists + "]";
	}
	
	
	
}
