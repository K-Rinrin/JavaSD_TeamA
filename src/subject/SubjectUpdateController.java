package subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher; // Teacherクラスのインポート
import dao.SubjectDAO;
import tool.CommonServlet;

/**
 * 科目情報の更新画面表示および更新処理を行うコントローラー
 * URL: /main/subject/SBJM004
 */
@WebServlet(urlPatterns = { "/main/subject/SBJM004" })
public class SubjectUpdateController extends CommonServlet {

    /**
     * GETリクエスト：科目更新画面を表示します。
     *
     * 1. リクエストパラメータから科目コード(cd)を取得します。
     * 2. SubjectDAOを使い、データベースから該当する科目情報を取得します。
     * 3. 取得した科目情報をリクエストスコープにセットします。
     * 4. 科目変更画面 (JSP) にフォワードします。
     */
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // リクエストパラメータから科目コードを取得
        String cd = req.getParameter("cd");

        // DAOをインスタンス化
        SubjectDAO dao = new SubjectDAO(null);

        // 科目コードを元に科目情報を取得
        Subject subject = dao.getSubjectByCd(cd);

        // 取得した科目情報をリクエスト属性にセット
        req.setAttribute("subject", subject);

        // 科目変更ページにフォワードします。
        // プロジェクトのフォルダ構成に合わせてJSPのパスを記述してください。
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main/subject/SBJM004.jsp"); // 仮のパス
        dispatcher.forward(req, resp);
    }

    /**
     * POSTリクエスト：科目情報を更新します。
     *
     * 1. フォームから送信された科目コード(cd)と科目名(name)を取得します。
     * 2. セッションからログインユーザー(Teacher)の情報を取得し、学校コードを取得します。
     * 3. 取得した情報から更新用のSubjectオブジェクトを生成します。
     * 4. SubjectDAOを使い、データベースの科目情報を更新します。
     * 5. 科目変更完了画面 (JSP) にフォワードします。
     */
    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // セッションを取得
        HttpSession session = req.getSession();
        // セッションからログインユーザー情報を取得（Teacherクラスを想定）
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ログインしていない、またはセッションが切れた場合のガード処理
        if (teacher == null) {
            // ログインページにリダイレクト（パスは要確認）
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // フォームからの入力値を取得（文字化け防止）
        req.setCharacterEncoding("UTF-8");
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 更新に必要なSchoolオブジェクトを作成し、ログインユーザーの学校コードを設定
        School school = new School();
        school.setCd(teacher.getSchool().getCd());

        // 更新対象のSubjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school); // Schoolオブジェクトをセット

        // DAOをインスタンス化して更新処理を実行
        SubjectDAO dao = new SubjectDAO(null);
        dao.updateSubject(subject); // 戻り値がないため、例外処理で成否を判断

        // 更新完了ページにフォワード
        // プロジェクトのフォルダ構成に合わせてJSPのパスを記述してください。
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main/subject/SBJM005.jsp"); // 仮のパス
        dispatcher.forward(req, resp);
    }
}