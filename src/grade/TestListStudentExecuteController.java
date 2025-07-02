package grade;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent; // TestListStudent をインポート
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO; // TestListStudentDAO をインポート
import tool.CommonServlet;

@WebServlet(urlPatterns = { "/main/grade/GRMR003" })
public class TestListStudentExecuteController extends CommonServlet {

    // 成績参照(学生)
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // セッションからログインユーザー情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

        // ログインチェック
        if (teacher == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        // 教師オブジェクトに学校情報が設定されているかチェック
        if (teacher.getSchool() == null) {
            req.setAttribute("errorMsg", "教師情報に学校が設定されていません。システム管理者に連絡してください。");
            req.getRequestDispatcher("/error.jsp").forward(req, resp); // エラーページへ転送
            return;
        }

        StudentDAO studao = new StudentDAO();
        SubjectDAO subdao = new SubjectDAO();

        // 検索フォームの初期表示に必要なデータを取得
        List<Integer> entYears = studao.getAllEntYear(teacher);
        List<String> classNums = studao.getAllClassNum(teacher);
        List<Subject> subjects = subdao.getAllSubjectsBySchool(teacher.getSchool().getCd());

        // データをJSPに渡す
        req.setAttribute("entYears", entYears);
        req.setAttribute("classNums", classNums);
        req.setAttribute("subjects", subjects);


        // 学生番号で検索した場合の処理
        String stu_num = req.getParameter("f4"); // 画面からの学生番号パラメータ

        // 入力された学生番号をJSPのフォームに保持させるため、常にセット
        if (stu_num != null) {
            stu_num = stu_num.trim();
        } else {
            stu_num = ""; // nullの場合も空文字列に初期化
        }
        req.setAttribute("f4", stu_num);


        // 学生番号が入力されている場合のみ、成績検索処理を実行
        if (!stu_num.isEmpty()) {
            try {
                // ★修正点1: 学生番号と教師の学校コードで学生を検索
                //    これにより、自分の学校にいない学生はここで null になる
                Student student = studao.getStudentByNo(stu_num, teacher.getSchool().getCd());

                // 学生情報が見つかった場合のみ、JSPにセット
                // 見つからなければ student は null のまま、JSP側で名前は表示されない
                req.setAttribute("student", student);

                // 成績リストを格納する変数
                List<TestListStudent> results = null;

                // 自分の学校に学生が存在する場合のみ、成績データを検索
                if (student != null) {
                    TestListStudentDAO stu_dao = new TestListStudentDAO();
                    // ★修正点2: TestListStudentDAO にも学校コードを渡す
                    results = stu_dao.getTestListByStudentNo(stu_num, teacher.getSchool().getCd());
                }

                // results は成績がある場合はリスト、ない場合は null または空リストになる
                // JSPのC:if test="${not empty results}" で制御
                req.setAttribute("results", results);

                req.getRequestDispatcher("/main/grade/GRMR003.jsp").forward(req, resp);

            } catch (Exception e) {
                // エラーログ出力
                e.printStackTrace();
                // ユーザーフレンドリーなエラーメッセージを設定し、エラーページへ転送
                req.setAttribute("errorMsg", "データの取得中にエラーが発生しました。システム管理者に連絡してください。");
                req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
            }
        } else {
            // 学生番号が入力されていない場合 (ページ初期表示など)
            // student, results 属性はセットされないか、デフォルト値 (null) のまま
            req.getRequestDispatcher("/main/grade/GRMR003.jsp").forward(req, resp);
        }
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // POSTリクエストもGETと同じ処理を呼び出すことで、フォームからの検索に対応
        get(req, resp);
    }
}