package subject;

//科目更新処理

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher; // Teacherをインポート
import dao.SubjectDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = { "/main/subject/SBJM004Execute"})
public class SubjectUpdateExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// GETリクエストでは何もしない
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
			Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");


			// Subject を作成して値をセット
			Subject subject = new Subject();
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(teacher.getSchool());


			dao.updateSubject(subject);

		} catch (Exception e) {
			e.printStackTrace();
			// エラーページに転送も可能
			req.setAttribute("errorMsg", "科目の更新中にエラーが発生しました。");
			req.getRequestDispatcher("/error.jsp").forward(req, resp); // エラーページに転送
			return; // 処理を終了
		}
		// 更新成功後、完了ページへ転送
		req.getRequestDispatcher("/main/subject/SBJM005.jsp").forward(req, resp);
	}
}