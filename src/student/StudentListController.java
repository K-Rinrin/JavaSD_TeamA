package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/studentlist"})//
public class StudentListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//絞り込み条件（未入力ならNULL）
		String entYearParam = req.getParameter("ent_year");
		int ent_year = (entYearParam != null && !entYearParam.isEmpty()) ? Integer.parseInt(entYearParam) : 0;

		String class_num = req.getParameter("class_num");
		if (class_num == null) class_num = "";

		String isAttendParam = req.getParameter("is_attend");
		boolean is_attend = (isAttendParam != null) ? Boolean.parseBoolean(isAttendParam) : false;


		//絞り込み条件無しなら全部の情報
		//ありならそれに応じた情報をDBから取得
		StudentDAO dao = new StudentDAO();
		List<Student> student = dao.findStudents(ent_year, class_num, is_attend);

		req.setAttribute("student", student);
		req.setAttribute("ent_year", ent_year);
		req.setAttribute("class_num", class_num);
		req.setAttribute("is_attend", is_attend);


		//学生一覧と条件をJSPに渡す
		req.getRequestDispatcher("main/student/STDM001.jsp").forward(req, resp);



	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・ス

	}

}
