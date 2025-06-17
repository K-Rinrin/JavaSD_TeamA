package subject;

import javax.servlet.annotation.WebServlet;

//科目更新画面表示

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import bean.School;
import bean.Subject;
import bean.Teacher; // Teacherクラスのインポート
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM004" })

public class SubjectUpdateController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		String cd = req.getParameter("cd");

		// TODO 自動生成されたメソッド・スタブ
		SubjectDAO dao = new SubjectDAO();
		Subject subject = dao.getSubjectByCd(cd);

		req.setAttribute("subject", subject);
		// JSPに処理を渡す
		req.getRequestDispatcher("SBJM004.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
