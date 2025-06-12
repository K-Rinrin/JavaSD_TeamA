package accounts;

//ログイン画面表示

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

// このアノテーションにより、/accounts/login へのリクエストがこのサーブレットにマッピングされます。
@WebServlet(urlPatterns = { "/accounts/login" })
public class LoginController extends CommonServlet {

	/**
	 * HTTP GET リクエストを処理します。
	 * ログイン画面 (LOGI001.jsp) を表示します。
	 *
	 * @param req  HttpServletRequest オブジェクト
	 * @param resp HttpServletResponse オブジェクト
	 * @throws Exception 処理中にエラーが発生した場合
	 */
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// LOGI001.jsp にフォワードしてログイン画面を表示
		req.getRequestDispatcher("LOGI001.jsp").forward(req, resp);
	}

	/**
	 * HTTP POST リクエストを処理します。
	 * 通常、ログインフォームの送信時に呼び出され、ログイン実行処理にリダイレクトします。
	 *
	 * @param req  HttpServletRequest オブジェクト
	 * @param resp HttpServletResponse オブジェクト
	 * @throws Exception 処理中にエラーが発生した場合
	 */
	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// ログイン実行処理を行う /accounts/loginexecute へリダイレクト
		resp.sendRedirect(req.getContextPath() + "/accounts/loginExecute");
	}
}