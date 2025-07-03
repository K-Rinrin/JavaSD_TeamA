package student;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentcreateexecute"})
public class StudentCreateExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	}



	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {


		StudentDAO dao = new StudentDAO();
		// エラー情報をまとめる
		Map<String, String> errors = new HashMap<>();



		//登録に必要な情報をもらう
		String entYearStr = req.getParameter("ent_year");
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");



		try{
            // ログイン情報が不正な場合はシステムエラー
            if (teacher == null || teacher.getSchool() == null) {
                req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
                return;
            }




            // ------------ エラー情報のチェック ------------

            // 1. 入学年度のチェック
            Integer entYear = null;
            if (entYearStr == null || entYearStr.isEmpty()) {
                errors.put("ent_year", "入学年度を選択してください");
            } else {
                entYear = Integer.parseInt(entYearStr);
            }

			// 2. 学生番号の重複チェック
			if (no == null || no.isEmpty()) {
				errors.put("no", "学生番号を入力してください");
			} else if (dao.getStudentByNo(no, teacher.getSchool().getCd()) != null) {
		        errors.put("no", "学生番号が重複しています");
		    }


			 // ------------ エラー情報のチェック ------------






            // エラーが1つでもあれば、登録画面に戻す
			if (!errors.isEmpty()) {
				// 入力された値をリクエストにセットして画面に復元
				req.setAttribute("ent_year", entYearStr);
				req.setAttribute("no", no);
				req.setAttribute("name", name);
				req.setAttribute("class_num", classNum);

				// エラーメッセージのMapをリクエストにセット
				req.setAttribute("errors", errors);

				// プルダウン用のリストを再取得してリクエストにセット
                req.setAttribute("classnum", dao.getAllClassNum(teacher));
                req.setAttribute("entYearList", dao.getEntYearRange());


				req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);

				return;
			}



			// エラーがなければ登録処理を実行
			Student stu = new Student();
			stu.setNo(no);
			stu.setName(name);
			stu.setEntYear(entYear);
			stu.setClassNum(classNum);
			stu.setAttend(true);
			stu.setSchool(teacher.getSchool());

			dao.addStudent(stu);



			req.getRequestDispatcher("/main/student/STDM003.jsp").forward(req, resp);


		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}
	}
}
