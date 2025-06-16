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
		int entyear = Integer.parseInt(req.getParameter("ent_year"));
		String classnum = req.getParameter("class_num");
		boolean isattend = Boolean.parseBoolean(req.getParameter("is_attend"));

		//絞り込み条件無しなら全部の情報
		//ありならそれに応じた情報をDBから取得
		StudentDAO dao = new StudentDAO(null);
		List<Student> stu = dao.findStudents(entyear, classnum, isattend);

		req.setAttribute("stu", stu);
		req.setAttribute("entyear", entyear);
		req.setAttribute("classnum", classnum);
		req.setAttribute("isattend", isattend);


		//学生一覧と条件をJSPに渡す
		req.getRequestDispatcher("main/student/STDM001.jsp").forward(req, resp);



	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		get(req, resp); // POST → GETと同じ処理に委譲

	}

}
