package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentcreate"})//
public class StudentCreateController extends CommonServlet {

	//学生登録
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//学生登録
		int entyear = Integer.parseInt(req.getParameter("ent_year"));
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classnum= req.getParameter("class_num");

		//登録に必要な情報をもらう
		StudentDAO dao = new StudentDAO();


		req.getRequestDispatcher("main/student/STDM002.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
