package grade;

import java.util.List;

//科目別成績一覧

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListSubject;
import dao.TestListSubjectDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMR002" })

public class TestListSubjectExecuteController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
//		全クラスを取得するDAO
//		クラス番号を取得する
		TestListSubjectDAO cn_dao = new TestListSubjectDAO(null);
		List<TestListSubject> testSub_list = cn_dao.getTestListByClass(null);
		req.setAttribute("results", testSub_list);
		req.getRequestDispatcher("/main/grade/GRMR001.jsp").forward(req, resp);


	}

}
