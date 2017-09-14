package us.mifeng.bubaexaminationsystem.bean;

public class ZhouBanji {
	public String name,headheadteacher,teacher,base,attendence,disciplinerate,id,depart_name,violation;
	
	
	public String getViolation() {
		return violation;
	}

	public void setViolation(String violation) {
		this.violation = violation;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public ZhouBanji() {
		super();
	}

	public ZhouBanji(String name, String headheadteacher, String teacher,
			String base, String attendence, String disciplinerate,String id) {
		super();
		this.name = name;
		this.headheadteacher = headheadteacher;
		this.teacher = teacher;
		this.base = base;
		this.attendence = attendence;
		this.disciplinerate = disciplinerate;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}

	public String getHeadheadteacher() {
		return headheadteacher;
	}

	

	public void setHeadheadteacher(String headheadteacher) {
		this.headheadteacher = headheadteacher;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getAttendence() {
		return attendence;
	}

	public void setAttendence(String attendence) {
		this.attendence = attendence;
	}

	public String getDisciplinerate() {
		return disciplinerate;
	}

	public void setDisciplinerate(String disciplinerate) {
		this.disciplinerate = disciplinerate;
	}

	@Override
	public String toString() {
		return "ZhouBanji [name=" + name + ", headheadteacher="
				+ headheadteacher + ", teacher=" + teacher + ", base=" + base
				+ ", attendence=" + attendence + ", disciplinerate="
				+ disciplinerate + ", id=" + id + ", depart_name="
				+ depart_name + "]";
	}
	

}
