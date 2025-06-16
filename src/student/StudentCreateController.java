package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/create"})//
public class StudentCreateController extends CommonServlet {

	//学生登録
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//学生登録

		//登録に必要な情報をもらう
		StudentDAO dao = new StudentDAO(null);
		List<Student> stu = dao.getAllStudents();

		//登録に必要な情報を渡す
		req.setAttribute("stu", stu);

		req.getRequestDispatcher("main/student/STDM002.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
