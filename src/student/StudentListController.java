package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentlist"})//
public class StudentListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//絞り込み条件（未入力ならNULL）
		String entYearParam = req.getParameter("entYear");
		Integer entYear = (entYearParam != null && !entYearParam.isEmpty()) ? Integer.parseInt(entYearParam) : null;

		String classNum = req.getParameter("classNum");
		if (classNum == null) classNum = "";

		String isAttendParam = req.getParameter("isAttend");
		Boolean isAttend = (isAttendParam != null) ? true : null;

		Teacher school_cd = (Teacher) req.getSession().getAttribute("session_user");


		//絞り込み条件無しなら全部の情報
		//ありならそれに応じた情報をDBから取得
		StudentDAO dao = new StudentDAO();
		List<Student> student = dao.findStudents(entYear, classNum, isAttend, school_cd);
		List<Student> allclass = dao.getAllClassNum();
		List<Student> allent = dao.getAllEntYear();

		req.setAttribute("allent", allent);
		req.setAttribute("allclass", allclass);
		req.setAttribute("student", student);
		req.setAttribute("entYear", entYear);
		req.setAttribute("classNum", classNum);
		req.setAttribute("isAttend", isAttend);
		req.setAttribute("件数", student.size());


		//学生一覧と条件をJSPに渡す
		req.getRequestDispatcher("/main/student/STDM001.jsp").forward(req, resp);



	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・ス

	}

}
