package us.mifeng.bubaexaminationsystem.bean;

public class Weiji implements Comparable<Weiji>{
	
	String name,qingkuang,keshi;
	
	int tag;

	public String getKeshi() {
		return keshi;
	}

	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQingkuang() {
		return qingkuang;
	}

	public void setQingkuang(String qingkuang) {
		this.qingkuang = qingkuang;
	}

	@Override
	public int compareTo(Weiji another) {
		if ("违纪情况：旷课".equals(another.qingkuang)) {
			return -1;
		}else if ("缺勤情况：停课".equals(another.qingkuang)) {
			return 100;
		}else {
			return 1;
		}
	}

}
