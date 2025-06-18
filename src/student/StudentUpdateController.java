package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentupdate"})//
public class StudentUpdateController extends CommonServlet {


	//学生更新
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//更新する学生の学生番号をもらう
		String no = req.getParameter("no");
		StudentDAO dao = new StudentDAO();
		Student stu = dao.getStudentByNo(no);

		req.setAttribute("stu", stu);

		req.getRequestDispatcher("/main/student/STDM004.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
