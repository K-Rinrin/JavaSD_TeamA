package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/studentcreateexecute"})//
public class StudentCreateExecuteController extends CommonServlet {

	//学生登録
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		req.getRequestDispatcher("main/student/STDM003.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		/*
		 * なにこれ
		 */

		//学生登録
		try{
			Student stu = new Student();
			stu.setNo(req.getParameter("no"));
			stu.setName(req.getParameter("name"));
			stu.setEntYear(Integer.parseInt(req.getParameter("ent_year")));
			stu.setClassNum(req.getParameter("class_num"));
			stu.setAttend(Boolean.parseBoolean(req.getParameter("is_attend")));

			StudentDAO dao = new StudentDAO();
			dao.addStudent(stu);

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.getRequestDispatcher("main/student/STDM003.jsp").forward(req, resp);
		}

		req.getRequestDispatcher("main/ERRO001.jsp").forward(req, resp);
	}

}
