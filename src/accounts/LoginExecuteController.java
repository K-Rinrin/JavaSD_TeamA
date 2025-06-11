package accounts;

import javax.servlet.annotation.WebServlet;

//ログイン機能

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.CommonServlet;

@WebServlet(urlPatterns = { "/accounts/loginexecute" })
public class LoginExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		// 現在のセッションを取得（存在しない場合は新規作成）
        HttpSession session = req.getSession();

		String id = req.getParameter("id");
        String password = req.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher != null) {
            // 認証成功時：ユーザー情報をセッションに保存
            session.setAttribute("session_user", teacher);

            // ログイン成功後、メイン画面（/main）にリダイレクト（ブラウザにURLを再要求させる）
            resp.sendRedirect(req.getContextPath() + "/menu");

        } else {
            // 認証失敗時：入力されたログイン名を再表示用にセット
            req.setAttribute("id", id);

            // エラーメッセージをリクエストに追加（JSP側で表示用）
//            req.setAttribute("errorMessage", "ログイン名またはパスワードが違います");

            // ログイン画面に戻る（入力ミスを修正して再試行させる）
            req.getRequestDispatcher("LOGI001.jsp").forward(req, resp);
        }
	}

}
