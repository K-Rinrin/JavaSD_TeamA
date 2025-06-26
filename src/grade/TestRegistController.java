package grade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.CommonServlet;

// 成績登録表示コントローラ
@WebServlet(urlPatterns = { "/main/grade/GRMU001" })
public class TestRegistController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        try {


            // 入学年度一覧（学生情報から取得）
            StudentDAO stu_dao = new StudentDAO();
            List<Student> stu_list = stu_dao.getAllStudents();

            // 重複しない入学年度のみ抽出
            Map<Integer, Student> entYearMap = new LinkedHashMap<>();
            for (Student s : stu_list) {
                entYearMap.putIfAbsent(s.getEntYear(), s);
            }
//            エラー情報をまとめる
            List<String> errorMsgs = new ArrayList<>();


            List<Student> filteredEntYears = new ArrayList<>(entYearMap.values());
            filteredEntYears.sort(Comparator.comparing(Student::getEntYear));
            req.setAttribute("student", filteredEntYears);



//-------------------------------------------------------------------------------------------------------------------

            // クラス一覧の取得
            ClassNumDAO cn_dao = new ClassNumDAO(null); // 学校コードなしでも可
            List<ClassNum> cn_list = cn_dao.getAllClassNums();

            Map<String, ClassNum> classMap = new LinkedHashMap<>();
            for (ClassNum c : cn_list) {
                classMap.putIfAbsent(c.getClass_num(), c);
            }

            List<ClassNum> filteredClassList = new ArrayList<>(classMap.values());
            filteredClassList.sort(Comparator.comparing(ClassNum::getClass_num));
            req.setAttribute("classNums", filteredClassList);


//-------------------------------------------------------------------------------------------------------------------



            //  科目一覧の取得
            SubjectDAO sub_dao = new SubjectDAO();
            List<Subject> sub_list = sub_dao.getAllSubjects();

            List<Subject> filteredSubjectList = new ArrayList<>(sub_list); // 科目コードはユニークなので重複除去不要
            filteredSubjectList.sort(Comparator.comparing(Subject::getCd));
            req.setAttribute("subjects", filteredSubjectList);

//-------------------------------------------------------------------------------------------------------------------

            //  検索条件の取得
            String f1 = req.getParameter("f1"); // 入学年度
            String f2 = req.getParameter("f2"); // クラス
            String f3 = req.getParameter("f3"); // 科目コード
            String f4 = req.getParameter("f4"); // 回数

            List<Test> scorelist = new ArrayList<>(); // 検索結果格納用
            String errorMsg = null;
            String subjectName = ""; // 科目名表示用

            //  検索条件がすべて揃っている場合、検索処理実行
            if (f1 != null && !f1.isEmpty()
             && f2 != null && !f2.isEmpty()
             && f3 != null && !f3.isEmpty()
             && f4 != null && !f4.isEmpty()) {

                try {
                    int entYear = Integer.parseInt(f1);
                    String classNum = f2;
                    String subjectCd = f3;
                    int testNo = Integer.parseInt(f4);

                    // 成績情報を取得
                    TestDAO testDao = new TestDAO();
                    scorelist = testDao.searchTests(entYear, classNum, subjectCd, testNo);

                    // 科目名取得（コードに対応する名称を探す）
                    for (Subject s : sub_list) {
                        if (s.getCd().equals(subjectCd)) {
                            subjectName = s.getName();
                            break;
                        }
                    }


                    // 検索条件をJSPに渡す
                    req.setAttribute("f1", f1);
                    req.setAttribute("f2", f2);
                    req.setAttribute("f3", f3);
                    req.setAttribute("f4", f4);

                } catch (Exception e) {
                }
            // いずれか1つでも未入力ならエラーを追加
            } else if
            	(f1 == null || f1.isEmpty() ||
                f2 == null || f2.isEmpty() ||
                f3 == null || f3.isEmpty() ||
                f4 == null || f4.isEmpty()) {

                errorMsgs.add("入学年度、クラス、科目、回数すべてを選択してください。");
            }

            // 最終的な結果のセットと画面遷移
            req.setAttribute("scorelist", scorelist);
            req.setAttribute("errorMsg", errorMsgs);
            req.setAttribute("subjectName", subjectName);

        } catch (Exception e) {

        }

        // 成績登録画面にフォワード
        req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // POST処理（未実装）
    }
}
