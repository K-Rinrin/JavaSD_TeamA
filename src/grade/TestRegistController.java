package grade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

//成績登録表示

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO2;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/grade/GRMU001" })

public class TestRegistController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		try {
			// 全クラスを取得するDAO
			// 入学年度を取得する
			StudentDAO2 stu_dao = new StudentDAO2();
			List<Student> stu_list = stu_dao.getAllStudents();

			// 入学年度で重複しないStudentリストを作成（最初に見つかった学生を保持）
			Map<Integer, Student> map = new LinkedHashMap<>();
			for (Student s : stu_list) {
			    map.putIfAbsent(s.getEntYear(), s);
			}

			// 昇順にソート（入学年度の昇順）
			List<Student> filteredList = new ArrayList<>(map.values());
			filteredList.sort(Comparator.comparing(Student::getEntYear));

			// JSPに渡す
			req.setAttribute("student", filteredList);


/*---------------------------------------------------------------------------------------------*/


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



		} catch (Exception e) {
			// TODO: handle exception
		}


		req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		try {
			// 検索条件を取得（戻るときのために保持）
			int entYear = Integer.parseInt(req.getParameter("f1"));
            String classNum = req.getParameter("f2");
            String subjectCd = req.getParameter("f3");
            String testNoStr = req.getParameter("f4");
            int testNo = Integer.parseInt(testNoStr);

            // 全パラメータを取得
			Test tests = new Test();
            Student student = new Student();
            Subject subject = new Subject();
			student .setEntYear(entYear);
			student.setClassNum(classNum);
			subject.setCd(subjectCd);
			tests.setNo(testNo);

            TestDAO dao = new TestDAO();
            dao.searchTests(entYear, classNum, subjectCd, testNo);



            // 成功後、検索画面にリダイレクト（検索条件付きで戻す）
            resp.sendRedirect(req.getContextPath() + "/main/grade/GRMU001"
                + "?f1=" + entYear
                + "&f2=" + classNum
                + "&f3=" + subjectCd
                + "&f4=" + testNoStr
            );

        } catch (Exception e) {
            e.printStackTrace();
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
		req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);


	}

}
