package grade;

import java.util.List;

//科目別成績一覧

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMR002" })

public class TestListSubjectExecuteController extends CommonServlet {

	//成績参照(科目)
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



		//入学年度、科目、クラス、で検索した場合
		int ent_year = Integer.parseInt(req.getParameter("f1"));
		String class_num = req.getParameter("f2");
		String subject_list = req.getParameter("f3");
		try{
			TestListSubjectDAO sub_dao = new TestListSubjectDAO();

			List<TestListSubject> results = sub_dao.getTestListByClass(ent_year, class_num, subject_list);

			req.setAttribute("results", results);
			req.getRequestDispatcher("/main/grade/GRMR002.jsp").forward(req, resp);
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
