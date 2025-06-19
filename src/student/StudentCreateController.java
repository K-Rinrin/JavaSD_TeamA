package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentcreate"})//
public class StudentCreateController extends CommonServlet {

	//学生登録
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//クラス一覧と今年を基準に+-10年文を渡す
		StudentDAO dao = new StudentDAO();
		List<Student> classnum = dao.getAllClassNum();
		//List<Integer> entYearList = dao.getEntYearRange();

		//req.setAttribute("entYearList", entYearList);
		req.setAttribute("classnum", classnum);


		req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
