package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentupdateexecute"})//
public class StudentUpdateExecuteController extends CommonServlet {


	//学生更新
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		StudentDAO dao = new StudentDAO();

		//学生情報の更新
		try{
			Student stu = new Student();
			stu.setName(req.getParameter("name"));
			stu.setClassNum(req.getParameter("classNum"));
			stu.setAttend(Boolean.parseBoolean(req.getParameter("isAttend")));

			dao.updateStudent(stu);

			req.getRequestDispatcher("/main/student/STDM005.jsp").forward(req, resp);
		}catch (Exception e) {
			// TODO: handle exception
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}



	}

}
