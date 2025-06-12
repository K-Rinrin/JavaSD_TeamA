package accounts;

//ログイン画面表示c

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

// このアノテーションにより、/accounts/login へのリクエストがこのサーブレットにマッピングされます。
@WebServlet(urlPatterns = { "/main/accounts/LOGI001" })
public class LoginController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// LOGI001.jsp にフォワードしてログイン画面を表示
		req.getRequestDispatcher("LOGI001.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// ログイン実行処理を行う /accounts/loginexecute へリダイレクト
//		resp.sendRedirect(req.getContextPath() + "/accounts/loginExecute");

	}
}