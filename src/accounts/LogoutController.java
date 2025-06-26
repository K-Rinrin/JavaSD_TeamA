package accounts;

//ログアウト画面表示+処理

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/accounts/LOGO001" })

public class LogoutController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		// セッションを取得（存在しない場合は新規作成される）
        HttpSession session = req.getSession();

        // セッションからユーザー情報と管理者フラグを削除
        session.removeAttribute("session_user"); // ログインユーザー情報の削除
        req.getRequestDispatcher("LOGO001.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
