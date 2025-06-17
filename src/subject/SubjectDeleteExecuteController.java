package subject;

import javax.servlet.annotation.WebServlet;

//科目削除処理

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM006Execute" })

public class SubjectDeleteExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		try {

			String cd = req.getParameter("cd");

			// DAO を使って科目を削除
			SubjectDAO dao = new SubjectDAO();
			dao.deleteSubject(cd);



	    } catch (Exception e) {
	        e.printStackTrace();
	        // エラー画面や元の画面に戻すなど
//	        req.setAttribute("error", "科目の登録に失敗しました。");
//	        req.getRequestDispatcher("/main/subject/SBJM002.jsp").forward(req, resp);
	    }
		resp.sendRedirect(req.getContextPath() + "/main/subject/SBJM007.jsp");

	}

}
