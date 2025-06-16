package subject;

import javax.servlet.annotation.WebServlet;

//科目登録処理

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM002Execute" })


public class SubjectCreateExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Subject subject = new Subject();
			subject.setCd(req.getParameter("cd"));
			subject.setName(req.getParameter("name"));

			SubjectDAO dao = new SubjectDAO(null);


		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
