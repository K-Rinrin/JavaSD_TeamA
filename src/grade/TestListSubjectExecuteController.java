package grade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//科目別成績一覧

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMR002" })

public class TestListSubjectExecuteController extends CommonServlet {

	//成績参照(科目)
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // セッションからログインユーザー情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

        StudentDAO studao = new StudentDAO();
        SubjectDAO subdao = new SubjectDAO();

        List<Integer> entYears = studao.getAllEntYear(teacher);
        List<String> classNums = studao.getAllClassNum(teacher);
        List<Subject> subjects = subdao.getAllSubjects();

        // JSP に渡すデータをセット
        req.setAttribute("entYears", entYears);
        req.setAttribute("classNums", classNums);
        req.setAttribute("subjects", subjects);

        // パラメータを取得
        String f1 = req.getParameter("f1");
        String class_num = req.getParameter("f2");
        String subject_cd = req.getParameter("f3");

        // 未入力チェック
        if (f1 == null || f1.isEmpty() || class_num == null || class_num.isEmpty() || subject_cd == null || subject_cd.isEmpty()) {
            // エラーをセットして JSP に戻る
            Map<String, String> errors = new HashMap<>();
            errors.put("subject_search_error", "入学年度とクラスと科目を選択してください");
            req.setAttribute("errors", errors);

            // 検索条件の保持
            req.setAttribute("f1", f1);
            req.setAttribute("f2", class_num);
            req.setAttribute("f3", subject_cd);

            req.getRequestDispatcher("/main/grade/GRMR002.jsp").forward(req, resp);
            return;
        }

        try {
            int ent_year = Integer.parseInt(f1);

            TestListSubjectDAO sub_dao = new TestListSubjectDAO();
            List<TestListSubject> results = sub_dao.getTestListByClass(ent_year, class_num, subject_cd);

            req.setAttribute("results", results);

            // 検索条件の保持
            req.setAttribute("f1", f1);
            req.setAttribute("f2", class_num);
            req.setAttribute("f3", subject_cd);

            req.getRequestDispatcher("/main/grade/GRMR002.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("subject_search_error", "入学年度が不正です");
            req.setAttribute("errors", errors);

            // 検索条件の保持
            req.setAttribute("f1", f1);
            req.setAttribute("f2", class_num);
            req.setAttribute("f3", subject_cd);

            req.getRequestDispatcher("/main/grade/GRMR002.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(); // ログにエラーを出す（開発中のみ）
            req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
        }
    }
	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
	}

}
