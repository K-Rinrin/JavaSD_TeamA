package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/sutudent/list"})//
public class SutudentListController extends CommonServlet {

	//学生一覧
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//絞り込み条件（未入力ならNULL）
		int EntYear = Integer.parseInt(req.getParameter("EntYear"));
		String ClassNum = req.getParameter("ClassNum");
		boolean Attend =

		//学生情報の取得（全件か絞り込み付か）
		StudentDAO dao = new StudentDAO();
		List<Student> list = dao.findAll();

		req.setAttribute("list", list);


		//学生一覧と条件をJSPに渡す
		req.getRequestDispatcher("main/student/STDM001.jsp").forward(req, resp);

		//
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		get(req, resp); // POST → GETと同じ処理に委譲
	}

}
