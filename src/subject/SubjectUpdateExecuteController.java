package subject;

import javax.servlet.annotation.WebServlet;

//科目更新処理

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM004Execute"})

public class SubjectUpdateExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			// DAO で更新
			SubjectDAO dao = new SubjectDAO();
		try {
			// パラメータ取得
			String cd = req.getParameter("cd");
			String name = req.getParameter("name");

			// セッションから先生情報を取得（所属学校用）
//			bean.Teacher teacher = (bean.Teacher) req.getSession().getAttribute("session_user");

			// Subject を作成して値をセット
			Subject subject = new Subject();
			subject.setCd(cd);
			subject.setName(name);
//			subject.setSchool(teacher.getSchool());


			dao.updateSubject(subject);

		} catch (Exception e) {
			e.printStackTrace();
			// エラーページに転送も可能
			// req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("/main/subject/SBJM005.jsp").forward(req, resp);

	}
}
