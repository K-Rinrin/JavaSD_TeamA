package subject;

import javax.servlet.annotation.WebServlet;

//科目更新処理

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM004Execute"})

public class SubjectUpdateExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			// パラメータ取得
			String cd = req.getParameter("cd");
			String name = req.getParameter("name");

			// セッションから先生情報を取得（所属学校用）
			bean.Teacher teacher = (bean.Teacher) req.getSession().getAttribute("session_user");

			// Subject を作成して値をセット
			bean.Subject subject = new bean.Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(teacher.getSchool());

			// DAO で更新
			dao.SubjectDAO dao = new dao.SubjectDAO();
			dao.updateSubject(subject);

			// 更新後、一覧ページにリダイレクト

		} catch (Exception e) {
			e.printStackTrace();
			// エラーページに転送も可能
			// req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		resp.sendRedirect(req.getContextPath() + "/main/subject/SBJM005.jsp");

	}
}
