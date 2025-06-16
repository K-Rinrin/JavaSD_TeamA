package subject;

import javax.servlet.annotation.WebServlet;

//科目登録処理

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/subject/SBJM002Execute" })


public class SubjectCreateExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		try {
	        // ログイン中の先生（sessionの"user"）を取得
	        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

//	        if (teacher == null) {
//	            // ログインしていない場合の処理
//	            resp.sendRedirect(req.getContextPath() +"/main/accounts/LOGI001");
//	            return;
//	        }


	        // フォームから取得した科目コードと名前を取得
	        String cd = req.getParameter("cd");
	        String name = req.getParameter("name");



	        // Subject インスタンスを作成し、情報をセット
	        Subject subject = new Subject();
	        subject.setCd(cd);
	        subject.setName(name);
	        subject.setSchool(teacher.getSchool()); // 先生の所属学校をセット
	        // DAO を使って科目をDBに登録
	        SubjectDAO dao = new SubjectDAO(null);
	        dao.addSubject(subject);



			resp.sendRedirect(req.getContextPath() + "/main/subject/SBJM003");


	    } catch (Exception e) {
	        e.printStackTrace();
	        // エラー画面や元の画面に戻すなど
//	        req.setAttribute("error", "科目の登録に失敗しました。");
//	        req.getRequestDispatcher("/main/subject/SBJM002.jsp").forward(req, resp);
	    }

	}

}
