package bean;

//入学年度
//学生番号
//学生名
//テスト点数

import java.util.Map;

public class TestListSubject {
	private int entYear;
	private String studentNo;
	private String studentName;
	private Map points;
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Map getPoints() {
		return points;
	}
	public void setPoints(Map points) {
		this.points = points;
	}


}
