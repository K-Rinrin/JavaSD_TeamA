package subject;

//科目削除表示

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM006" })

public class SubjectDeleteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cd = req.getParameter("cd");
//	    String name = req.getParameter("name");
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

		// TODO 自動生成されたメソッド・スタブ
		SubjectDAO dao = new SubjectDAO();
		Subject subject = dao.getSubjectByCd(cd, teacher.getSchool().getCd());

		req.setAttribute("subject", subject);
		// JSPに処理を渡す
		req.getRequestDispatcher("/main/subject/SBJM006.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
