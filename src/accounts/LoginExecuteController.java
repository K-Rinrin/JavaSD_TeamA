package accounts;

//ログイン処理

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.CommonServlet;

//アノテーション
@WebServlet(urlPatterns = { "/main/accounts/loginExecute" })
public class LoginExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 現在のセッションを取得。セッションが存在しない場合は新しく生成される。
		HttpSession session = req.getSession();

		// ログインIDとパスワードを取得
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		// TeacherDAOのインスタンスを生成し、データベースから教員情報を取得
		TeacherDAO dao = new TeacherDAO(null);

		try {
			Teacher teacher = dao.login(id, password); // ログインIDとパスワードで教員情報を検索

			// 取得した教員情報がnullでない場合（認証成功）
			if (teacher != null) {

				// 認証成功時：取得した教員情報をセッションに保存します。
				// "session_user" という名前でセッションアトリビュートとして設定します。
				session.setAttribute("session_user", teacher);

				// ログイン成功後、アプリケーションのコンテキストパスに "/menu" を付加したURLへリダイレクトします。
				// これにより、ブラウザは指定されたURLに再度アクセスします。
				resp.sendRedirect(req.getContextPath() + "/main/MMNU001");

			}else{
		        req.setAttribute("error", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
		        req.setAttribute("id",id);
				req.getRequestDispatcher("/main/accounts/LOGI001.jsp").forward(req, resp);

			}


		} catch (Exception e) {
			// エラー画面 (ERRO001.jsp) にフォワード
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}
	}

}