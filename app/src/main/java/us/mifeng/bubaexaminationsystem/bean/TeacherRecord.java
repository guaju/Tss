package us.mifeng.bubaexaminationsystem.bean;


public class TeacherRecord {
	int teacher_role;
	int overallrecord_id;
	int id;
	int teacher_id;
	String notes;
	String teacher_name;
	int teacher_status;
	public int getTeacher_role() {
		return teacher_role;
	}
	public void setTeacher_role(int teacher_role) {
		this.teacher_role = teacher_role;
	}
	public int getOverallrecord_id() {
		return overallrecord_id;
	}
	public void setOverallrecord_id(int overallrecord_id) {
		this.overallrecord_id = overallrecord_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public int getTeacher_status() {
		return teacher_status;
	}
	public void setTeacher_status(int teacher_status) {
		this.teacher_status = teacher_status;
	}

}
