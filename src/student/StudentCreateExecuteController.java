package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentcreateexecute"})
public class StudentCreateExecuteController extends CommonServlet {

	// getメソッドは直接使われない想定
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 不正なアクセスの場合は登録画面にリダイレクト
		resp.sendRedirect(req.getContextPath() + "/main/student/STDM002");
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//学生登録
		try{
			StudentDAO dao = new StudentDAO();

			//登録に必要な情報をもらう
			String entYearStr = req.getParameter("ent_year");
			String no = req.getParameter("no");
			String name = req.getParameter("name");
			String classNum = req.getParameter("class_num");
			Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");
			Boolean isAttend = true;



            // ログイン情報が不正な場合はエラーとして処理を中断
            if (teacher == null || teacher.getSchool() == null) {
                req.setAttribute("error", "ログイン情報が不正です。再度ログインしてください。");
                // プルダウン用のデータを念のため取得
                req.setAttribute("entYearList", dao.getEntYearRange());
                req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);
                return;
            }

            // ent_yearが選択されているかチェック
            if (entYearStr == null || entYearStr.isEmpty()) {
                req.setAttribute("error", "入学年度を選択してください。");
                // 入力データを復元
                req.setAttribute("no", no);
                req.setAttribute("name", name);
                req.setAttribute("class_num", classNum);
                // プルダウン用のデータを再取得
                req.setAttribute("classnum", dao.getAllClassNum(teacher));
                req.setAttribute("entYearList", dao.getEntYearRange());
                req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);
                return;
            }
            int entYear = Integer.parseInt(entYearStr);


			//重複チェック
			if (dao.getStudentByNo(no) != null) {
		        req.setAttribute("error", "この学生番号はすでに登録されています。");

		        // 入力済みデータと一覧データを再セット
		        req.setAttribute("ent_year", entYear);
		        req.setAttribute("no", no);
		        req.setAttribute("name", name);
		        req.setAttribute("class_num", classNum);

		        req.setAttribute("classnum", dao.getAllClassNum(teacher));
		        req.setAttribute("entYearList", dao.getEntYearRange());

		        req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);
                return;
		    }

			//DAOに渡す
			Student stu = new Student();
			stu.setNo(no);
			stu.setName(name);
			stu.setEntYear(entYear);
			stu.setClassNum(classNum);
			stu.setAttend(isAttend);
			stu.setSchool(teacher.getSchool()); // 先生の所属学校

			dao.addStudent(stu);

			req.getRequestDispatcher("/main/student/STDM003.jsp").forward(req, resp);

		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}
	}
}