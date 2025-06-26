package grade;

import java.util.List;

//学生別成績一覧

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMR003" })

public class TestListStudentExecuteController extends CommonServlet {

	//成績参照(学生)
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		// セッションからログインユーザー情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

		StudentDAO studao = new StudentDAO();
		SubjectDAO subdao = new SubjectDAO();

		List<Integer> entYears = studao.getAllEntYear(teacher);
		List<String> classNums = studao.getAllClassNum(teacher);
		List<Subject> subjects = subdao.getAllSubjects();

		//データを渡す
		req.setAttribute("entYears", entYears);
		req.setAttribute("classNums", classNums);
		req.setAttribute("subjects", subjects);



		//学生番号で検索した場合

		String stu_num = req.getParameter("f4");
		req.setAttribute("f4", stu_num);
		try{
			TestListStudentDAO stu_dao = new TestListStudentDAO();
			List<TestListStudent> results = stu_dao.getTestListByStudentNo(stu_num);

			req.setAttribute("results", results);
			req.getRequestDispatcher("/main/grade/GRMR003.jsp").forward(req, resp);
		}catch (Exception e) {
			// TODO: handle exception
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
