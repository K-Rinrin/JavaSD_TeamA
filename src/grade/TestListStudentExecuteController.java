package grade;

import java.util.List;

//学生別成績一覧

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListStudent;
import dao.TestListStudentDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMR003" })

public class TestListStudentExecuteController extends CommonServlet {

	//成績参照(学生)
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//学生番号で検索した場合
		String stu_num = req.getParameter("no");
		try{
			TestListStudentDAO stu_dao = new TestListStudentDAO();
			List<TestListStudent> student = stu_dao.getTestListByStudentNo(stu_num);

			req.setAttribute(student, student);
			req.getRequestDispatcher("/main/grade/GRMR003.jsp").forward(req, resp);
		}catch (Exception e) {
			// TODO: handle exception
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
