package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = {"/main/student/STDM001"})
public class StudentListController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // セッションからログインユーザー情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

        // ログインチェック
        if (teacher == null || teacher.getSchool() == null) {
            resp.sendRedirect(req.getContextPath() + "/main/accounts/login"); // ログインページのURLは環境に合わせてください
            return;
        }


        // パラメータ処理
        String entYearParam = req.getParameter("entYear");
        Integer entYear = (entYearParam != null && !entYearParam.isEmpty()) ? Integer.parseInt(entYearParam) : null;

        String classNum = req.getParameter("classNum");
        if (classNum != null && classNum.isEmpty()) {
            classNum = null;
        }

        // 「絞込み」ボタンが押されたかどうかを判定
        String filter = req.getParameter("filter");
        Boolean isAttend = null; // デフォルトはnull（絞り込まない）

        if (filter != null) {
            // 絞り込みボタンが押された場合
            String isAttendParam = req.getParameter("isAttend");
            // チェックボックスがチェックされていればtrue, されていなければfalseを設定
            isAttend = (isAttendParam != null);
        }

        // DAOをインスタンス化
        StudentDAO dao = new StudentDAO();
        // 検索条件を元に、ログイン中の学校の学生を取得
        List<Student> student;

        if (filter != null) {
            // 絞り込みボタンが押された場合は、条件で検索
             student = dao.findStudents(entYear, classNum, isAttend, teacher);
        } else {
            // 初回アクセス時は、ログイン中の学校の全学生を表示
            student = dao.findStudents(null, null, null, teacher);
        }




        List<String> classNumList = dao.getAllClassNum(teacher);
        List<Integer> entYearList = dao.getAllEntYear(teacher);

        req.setAttribute("allent", entYearList);
        req.setAttribute("allclass", classNumList);
        req.setAttribute("student", student);
        req.setAttribute("件数", student.size());

        // 検索条件の保持（任意）
        req.setAttribute("entYear", entYear);
        req.setAttribute("classNum", classNum);
        // isAttendがnullでない場合のみセット（チェックボックスの状態復元用）
        if (isAttend != null) {
            req.setAttribute("isAttend", isAttend);
        }


        //学生一覧と条件をJSPに渡す
        req.getRequestDispatcher("/main/student/STDM001.jsp").forward(req, resp);
    }

    @Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// postリクエストが来た場合もgetメソッドと同じ処理を行う
		get(req, resp);
	}
}