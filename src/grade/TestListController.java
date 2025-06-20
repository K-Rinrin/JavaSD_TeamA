package grade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//成績参照検索処理

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Student;
import bean.Subject;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = { "/main/grade/GRMR001" })
public class TestListController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		int ent_year = Integer.parseInt(req.getParameter("f1"));
		String class_num = req.getParameter("f2");
		String subject_list = req.getParameter("f3");
		//String kaisuu = req.getParameter("f4");

		try {

			// 全クラスを取得するDAO
			// クラス番号を取得する
			ClassNumDAO cn_dao = new ClassNumDAO(null);
			List<ClassNum> cn_list = cn_dao.getAllClassNums();

			// 重複を排除（最初に出てきたクラス番号のデータだけを残す）
			Map<String, ClassNum> map2 = new LinkedHashMap<>();
			for (ClassNum c : cn_list) {
			    map2.putIfAbsent(c.getClass_num(), c);
			}

			// 昇順にソート（クラス番号の昇順）
			List<ClassNum> filteredClassList = new ArrayList<>(map2.values());
			filteredClassList.sort(Comparator.comparing(ClassNum::getClass_num));

			// JSPに渡す
			req.setAttribute("classNums", filteredClassList);

/*---------------------------------------------------------------------------------------------*/

			//全科目を取得するDAO
			//科目名を取得する
			SubjectDAO sub_dao = new SubjectDAO();
			List<Subject> sub_list = sub_dao.getAllSubjects();

			// 重複を排除（最初に出てきた科目名のデータだけを残す）
			Map<String, Subject> map3 = new LinkedHashMap<>();
			for (Subject c : sub_list) {
			    map3.putIfAbsent(c.getName(), c);
			}

			// 昇順にソート(科目名の昇順）
			List<Subject> filteredSubjectList = new ArrayList<>(map3.values());
			filteredSubjectList.sort(Comparator.comparing(Subject::getName));

			// リクエストにセット
			req.setAttribute("subjects", filteredSubjectList);




/*---------------------------------------------------------------------------------------------*/

			// 全学生を取得する
			StudentDAO stu_dao = new StudentDAO();
			List<Student> stu_list = stu_dao.getAllStudents();

			// 入学年度の重複を排除し、昇順にソート
			List<Student> entYears = stu_dao.getAllEntYear();

			// リクエストにセット
			req.setAttribute("entYears", entYears);


		req.getRequestDispatcher("/main/grade/GRMR001.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
