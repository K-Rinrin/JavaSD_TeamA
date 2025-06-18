package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/studentlist"})//
public class StudentListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//絞り込み条件（未入力ならNULL）
		String entYearParam = req.getParameter("ent_year");
		Integer entYear = (entYearParam != null && !entYearParam.isEmpty()) ? Integer.parseInt(entYearParam) : null;


		String classNum = req.getParameter("class_num");
		if (classNum == null) classNum = "";

		String isAttendParam = req.getParameter("is_attend");
		Boolean isAttend = (isAttendParam != null) ? true : null;


		//絞り込み条件無しなら全部の情報
		//ありならそれに応じた情報をDBから取得
		StudentDAO dao = new StudentDAO();
		List<Student> student = dao.findStudents(entYear, classNum, isAttend);

		req.setAttribute("student", student);
		req.setAttribute("entYear", entYear);
		req.setAttribute("classNum", classNum);
		req.setAttribute("isAttend", isAttend);
		req.setAttribute("件数", student.size());

		System.out.println("studentList size = " + student.size());
		for (Student s : student) {
		    System.out.println("Student name = " + s.getName());
		}


		//学生一覧と条件をJSPに渡す
		req.getRequestDispatcher("main/student/STDM001.jsp").forward(req, resp);



	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・ス

	}

}
