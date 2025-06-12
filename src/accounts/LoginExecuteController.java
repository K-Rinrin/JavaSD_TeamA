package accounts;

//ログイン処理
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.CommonServlet; 

// このアノテーションにより、/accounts/loginExecute へのリクエストがこのサーブレットにマッピングされます。
@WebServlet(urlPatterns = { "/accounts/loginExecute" })
public class LoginExecuteController extends CommonServlet {

	/**
	 * HTTP GET リクエストを処理します。
	 * このサーブレットではGETリクエストは想定されていませんが、必要に応じて実装できます。
	 * 現在は空の実装です。
	 *
	 * @param req  HttpServletRequest オブジェクト
	 * @param resp HttpServletResponse オブジェクト
	 * @throws Exception 処理中にエラーが発生した場合
	 */
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// GETリクエスト時の処理は現在ありません。
		// TODO: 必要に応じてGETリクエストの処理を実装する
	}

	/**
	 * HTTP POST リクエストを処理します。
	 * ログインフォームから送信されたIDとパスワードを使用して認証を行います。
	 * 認証成功時にはセッションにユーザー情報を保存し、メイン画面へリダイレクトします。
	 * 認証失敗時にはエラーメッセージを設定し、ログイン画面にフォワードします。
	 *
	 * @param req  HttpServletRequest オブジェクト
	 * @param resp HttpServletResponse オブジェクト
	 * @throws Exception 処理中にエラーが発生した場合
	 */
	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 現在のセッションを取得します。セッションが存在しない場合は新しく作成されます。
		HttpSession session = req.getSession();

		// リクエストパラメータからログインIDとパスワードを取得します。
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		// TeacherDaoのインスタンスを生成し、データベースから教員情報を取得します。
		TeacherDao dao = new TeacherDao();
		Teacher teacher = dao.login(id, password); // ログインIDとパスワードで教員情報を検索します。

		// 取得した教員情報がnullでない場合（認証成功）
		if (teacher != null) {
			// 認証成功時：取得した教員情報をセッションに保存します。
			// "session_user" という名前でセッションアトリビュートとして設定します。
			session.setAttribute("session_user", teacher);

			// ログイン成功後、アプリケーションのコンテキストパスに "/menu" を付加したURLへリダイレクトします。
			// これにより、ブラウザは指定されたURLに再度アクセスします。
			resp.sendRedirect(req.getContextPath() + "/menu");

		} else {
			// 認証失敗時：入力されたログインIDをリクエスト属性にセットし、ログイン画面で再表示できるようにします。
			req.setAttribute("id", id);

			// エラーメッセージをリクエスト属性に追加する場合のコメントアウトされたコードです。
			// req.setAttribute("errorMessage", "ログイン名またはパスワードが違います");

			// ログイン画面 (LOGI001.jsp) にフォワードし、ユーザーに入力ミスを修正して再試行を促します。
			req.getRequestDispatcher("LOGI001.jsp").forward(req, resp);
		}
	}
}