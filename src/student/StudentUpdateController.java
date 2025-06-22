package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentupdate"})
public class StudentUpdateController extends CommonServlet {


	//学生更新
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		// セッションからログインユーザー情報を取得
				Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

		        // ログインチェック
		        if (teacher == null) {
		            resp.sendRedirect(req.getContextPath() + "/main/accounts/login");
		            return;
		        }

				//更新する学生の学生番号をもらう
				String no = req.getParameter("no");

				//学生情報をもらう
				StudentDAO dao = new StudentDAO();
				Student student = dao.getStudentByNo(no);

		        //クラス番号の一覧をもらう
				List<String> allclass = dao.getAllClassNum(teacher);

				//学生情報をわたす
				req.setAttribute("student", student);
				req.setAttribute("allclass", allclass);



				req.getRequestDispatcher("/main/student/STDM004.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
