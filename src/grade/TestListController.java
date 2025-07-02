package grade;

import java.util.List;

//成績参照検索処理

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.StudentDAO;
import dao.SubjectDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = { "/main/grade/GRMR001" })
public class TestListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		// セッションからログインユーザー情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

		StudentDAO studao = new StudentDAO();
		SubjectDAO subdao = new SubjectDAO();

		List<Integer> entYears = studao.getAllEntYear(teacher);
		List<String> classNums = studao.getAllClassNum(teacher);
		List<Subject> subjects = subdao.getAllSubjectsBySchool(teacher.getSchool().getCd());

		//データを渡す
		req.setAttribute("entYears", entYears);
		req.setAttribute("classNums", classNums);
		req.setAttribute("subjects", subjects);

		req.getRequestDispatcher("/main/grade/GRMR001.jsp").forward(req, resp);

		/*
		 *
		 *
		 *
		 */
	}


	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
