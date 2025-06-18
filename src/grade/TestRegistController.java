package grade;

import java.util.List;

import javax.servlet.annotation.WebServlet;

//成績登録表示

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMU001" })

public class TestRegistController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
//		参照前のプルダウンに入れるためのやつ

		req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
//		検索処理選択されたプルダウンに格納されているデータを取り出し学生を検索結果として返す
//		入学年度、クラス：STUDEDNT
//		科目NAME：SUBJECT　回数：TEST
//		点数は既に登録されている点数を変更、書き換えができるような形

		SubjectDAO sub_dao = new SubjectDAO();
		StudentDAO stu_dao = new StudentDAO();
		TestDAO test_dao = new TestDAO(null);
		ClassNumDAO cn_dao = new ClassNumDAO(null);

		try {
//			// パラメータ取得
//			Subject subject = new Subject();
//			Student student = new Student();
//			Test test = new Test();
//			ClassNum classNum = new ClassNum();
//
//			student.setEntYear(Integer.parseInt(req.getParameter("entYear")));
//			classNum.setClass_num(req.getParameter("classNum"));
//			subject.setName(req.getParameter("name"));
//			test.setNo(Integer.parseInt(req.getParameter("no")));
//
//			student.setAttend(Boolean.parseBoolean(req.getParameter("isAttend")));
			 // パラメータ取得
	        String entYearParam = req.getParameter("entYear");
	        String classNumParam = req.getParameter("classNum");
	        String isAttendParam = req.getParameter("isAttend");


	        Integer entYear = (entYearParam != null && !entYearParam.isEmpty()) ? Integer.parseInt(entYearParam) : null;
	        String classNum = (classNumParam != null && !classNumParam.isEmpty()) ? classNumParam : null;
	        Boolean isAttend = (isAttendParam != null && !isAttendParam.isEmpty()) ? Boolean.parseBoolean(isAttendParam) : null;

			List<Student> students = stu_dao.findStudents(entYear, classNum, isAttend);
			req.setAttribute("studentList", students);






		} catch (Exception e) {
			// TODO: handle exception
		}
        req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);

	}

}
