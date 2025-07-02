package subject;

//科目一覧画面表示

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM001" })

public class SubjectListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

		SubjectDAO dao = new SubjectDAO();
		List<Subject> list = dao.getAllSubjectsBySchool(teacher.getSchool().getCd());
		req.setAttribute("subjects", list);
		// JSPに処理を渡す
			req.getRequestDispatcher("/main/subject/SBJM001.jsp").forward(req, resp);
	}
	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

//		get(req, resp);

	}

}
