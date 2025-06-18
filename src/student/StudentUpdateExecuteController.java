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

		//学生情報をもらう
		String name= req.getParameter("name");
		String classNum = req.getParameter("classNum");
		Boolean isAttend = Boolean.parseBoolean(req.getParameter("isAttend"));
		String no = req.getParameter("no");

		try{
			//情報をDAOに渡す
			Student student = new Student();
			student.setName(name);
			student.setClassNum(classNum);
			student.setAttend(isAttend);
			student.setNo(no);

			StudentDAO dao = new StudentDAO();
			dao.updateStudent(student);

			//フォワード
			req.getRequestDispatcher("/main/student/STDM005.jsp").forward(req, resp);

			} catch (Exception e) {
				//登録に失敗した場合
				req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
			}
	}
}
