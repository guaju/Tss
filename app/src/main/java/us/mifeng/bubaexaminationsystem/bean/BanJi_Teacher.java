package us.mifeng.bubaexaminationsystem.bean;

public class BanJi_Teacher {
	private String name,ziwu,zhuangtai,bianHao,zWBianHao,notes;

	public String getzWBianHao() {
		return zWBianHao;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setzWBianHao(String zWBianHao) {
		this.zWBianHao = zWBianHao;
	}

	public String getBianHao() {
		return bianHao;
	}

	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}

	public String getName() {
		return name;
	}

	public void setName(String name1) {
		this.name = name1;
	}

	public String getZiwu() {
		return ziwu;
	}

	public void setZiwu(String ziwu1) {
		this.ziwu = ziwu1;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai1) {
		this.zhuangtai = zhuangtai1;
	}

}
