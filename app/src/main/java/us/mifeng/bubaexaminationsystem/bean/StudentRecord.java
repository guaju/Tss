package us.mifeng.bubaexaminationsystem.bean;

import java.util.ArrayList;

public class StudentRecord {
	String student_name;
	ArrayList<Integer> disciplinarycategory_id;
	int student_id;
	int overallrecord_id;
	String notes;
	int  student_class;
	int student_status;
	public ArrayList<Integer> getDisciplinarycategory_id() {
		return disciplinarycategory_id;
	}
	public void setDisciplinarycategory_id(
			ArrayList<Integer> disciplinarycategory_id) {
		this.disciplinarycategory_id = disciplinarycategory_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public int getOverallrecord_id() {
		return overallrecord_id;
	}
	public void setOverallrecord_id(int overallrecord_id) {
		this.overallrecord_id = overallrecord_id;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getStudent_class() {
		return student_class;
	}
	public void setStudent_class(int student_class) {
		this.student_class = student_class;
	}
	public int getStudent_status() {
		return student_status;
	}
	public void setStudent_status(int student_status) {
		this.student_status = student_status;
	}
	@Override
	public String toString() {
		return "StudentRecord [student_name=" + student_name
				+ ", disciplinarycategory_id=" + disciplinarycategory_id
				+ ", student_id=" + student_id + ", overallrecord_id="
				+ overallrecord_id + ", notes=" + notes + ", student_class="
				+ student_class + ", student_status=" + student_status + "]";
	}

}
