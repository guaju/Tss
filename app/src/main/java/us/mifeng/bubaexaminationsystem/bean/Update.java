package us.mifeng.bubaexaminationsystem.bean;

public class Update {
	String ver;
	String url;
	String notes;
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Update(String ver, String url, String notes) {
		super();
		this.ver = ver;
		this.url = url;
		this.notes = notes;
	}
	public Update() {
		super();
	}
	@Override
	public String toString() {
		return "Update [ver=" + ver + ", url=" + url + ", notes=" + notes + "]";
	}
	
}
