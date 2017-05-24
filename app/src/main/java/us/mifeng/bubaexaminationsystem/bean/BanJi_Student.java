package us.mifeng.bubaexaminationsystem.bean;

public class BanJi_Student {
	private String name,sex,zhuangtai,bianHao,notes;
	private int disciplinarycategory;

	public String getName() {
		return name;
	}

	public String getBianHao() {
		return bianHao;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}

	public void setName(String name1) {
		this.name = name1;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex1) {
		this.sex = sex1;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zt) {
		this.zhuangtai = zt;
	}

	public int getDisciplinarycategory() {
		return disciplinarycategory;
	}

	public void setDisciplinarycategory(int disciplinarycategory) {
		this.disciplinarycategory = disciplinarycategory;
	}

}
