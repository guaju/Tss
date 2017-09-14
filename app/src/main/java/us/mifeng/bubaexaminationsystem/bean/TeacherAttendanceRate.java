package us.mifeng.bubaexaminationsystem.bean;

public class TeacherAttendanceRate {
	int teacherId;
	String teacherName;
	int departId;
	String attendance_rate;
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public String getAttendance_rate() {
		return attendance_rate;
	}
	public void setAttendance_rate(String attendance_rate) {
		this.attendance_rate = attendance_rate;
	}
}
