package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/STDM002"})
public class StudentCreateController extends CommonServlet {

	//学生登録画面表示
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ


		// セッションからログインユーザー情報を取得
		Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

        // ログインチェック（念のため）
        if (teacher == null || teacher.getSchool() == null) {
            resp.sendRedirect(req.getContextPath() + "/main/accounts/login");
            return;
        }

		//クラス一覧と今年を基準に+-10年文を渡す
		StudentDAO dao = new StudentDAO();


		List<String> classNumList = dao.getAllClassNum(teacher);
		List<Integer> entYearList = dao.getEntYearRange();

		req.setAttribute("entYearList", entYearList);
		req.setAttribute("classnum", classNumList);


		req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// POSTリクエストが来た場合はGETに処理を委譲
		get(req, resp);
	}
}