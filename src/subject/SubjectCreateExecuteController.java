package subject;

import java.util.HashMap;
import java.util.Map;

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

	        // フォームから取得した科目コードと名前を取得
	        String cd = req.getParameter("cd");
	        String name = req.getParameter("name");
	     // ログイン中の先生（sessionの"user"）を取得
	        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");
	     // エラー情報をまとめる
			Map<String, String> errors = new HashMap<>();




	        // Subject インスタンスを作成し、情報をセット
	        Subject subject = new Subject();
	        subject.setSchool(teacher.getSchool()); // 先生の所属学校をセット
	        subject.setCd(cd);
	        subject.setName(name);



	        // DAO を使って科目をDBに登録
	        SubjectDAO dao = new SubjectDAO();
	        dao.addSubject(subject);

	     // ------------ エラー情報のチェック ------------

			// 科目番号の重複チェック
			if (cd == null || cd.isEmpty()) {
				errors.put("no", "科目コードを入力してください");
			} else if (dao.getSubjectByCd(cd) != null) {
		        errors.put("no", "科目コードがが重複しています");
		    }


			 // ------------ エラー情報のチェック ------------



	    } catch (Exception e) {
	        e.printStackTrace();
	        // エラー画面や元の画面に戻すなど
//	        req.setAttribute("error", "科目の登録に失敗しました。");
//	        req.getRequestDispatcher("/main/subject/SBJM002.jsp").forward(req, resp);
	    }
		resp.sendRedirect(req.getContextPath() + "/main/subject/SBJM003.jsp");
	}

}