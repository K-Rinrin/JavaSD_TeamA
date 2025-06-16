package subject;

import java.util.List;

import javax.servlet.annotation.WebServlet;

//科目一覧画面表示

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM001" })

public class SubjectListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {

			// SubjectDAOのインスタンスを生成し、データベースから教員情報を取得します。
			SubjectDAO dao = new SubjectDAO(null);
			List<Subject> list = dao. getAllSubjects();
			// JSPで使えるようにリクエストスコープにデータを格納
			req.setAttribute("subjectList", list);
			// JSPに処理を渡す
			req.getRequestDispatcher("/main/subject/SBJM001.jsp").forward(req, resp);


		} catch (Exception e) {
			// TODO: handle exception
			// エラー画面 (ERRO001.jsp) にフォワード
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		get(req, resp);

	}

}
