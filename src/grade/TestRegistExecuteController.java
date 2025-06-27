package grade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = {"/main/grade/GRMU001Execute"})
public class TestRegistExecuteController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.sendRedirect(req.getContextPath() + "/main/grade/GRMU001");
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {


    	// --- 1. 準備 ---
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目コード
        String f4 = req.getParameter("f4"); // 回数
        String action = req.getParameter("action");
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

        Map<String, String> inputErrors = new HashMap<>();
        String errorMsg = null;
        boolean allSuccess = true;



        // --- 2. バリデーション (入力値チェック) ---
        try {
            // (1) 検索条件の必須チェック
            if (f1 == null || f1.isEmpty() || f2 == null || f2.isEmpty()
                    || f3 == null || f3.isEmpty() || f4 == null || f4.isEmpty()) {
                allSuccess = false;
                errorMsg = "入学年度、クラス、科目、回数をすべて選択してください。";
            } else {
                // (2) 点数の入力値チェック (検索条件が揃っている場合のみ)
                for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
                    String paramName = entry.getKey();
                    if (paramName.startsWith("point_")) {
                        String studentNo = paramName.substring("point_".length());
                        String pointStr = entry.getValue()[0];

                        if (pointStr == null || pointStr.trim().isEmpty()) {
                            continue; // 入力がない場合はチェックしない
                        }

                        try {
                            int point = Integer.parseInt(pointStr);
                            if (point < 0 || point > 100) {
                                allSuccess = false;
                                inputErrors.put(studentNo, "0～100の範囲で入力してください。");
                            }
                        } catch (NumberFormatException e) {
                            allSuccess = false;
                            inputErrors.put(studentNo, "数値を入力してください。");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            allSuccess = false;
            errorMsg = "処理中に予期せぬエラーが発生しました。";
        }


        // --- 3. DB操作 (バリデーションがすべて成功した場合のみ) ---
        if (allSuccess) {
            TestDAO testDao = new TestDAO();
            String schoolCd = teacher.getSchool().getCd();
            int testNo = Integer.parseInt(f4);
            String subjectCd = f3;
            String classNum = f2;

            try {
                for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
                    String paramName = entry.getKey();
                    if (paramName.startsWith("point_")) {
                        String studentNo = paramName.substring("point_".length());
                        String pointStr = entry.getValue()[0];
                        boolean deleteChecked = req.getParameter("check_" + studentNo) != null;

                        if (deleteChecked) {
                            testDao.deleteTest(studentNo, subjectCd, schoolCd, testNo);
                            continue;
                        }

                        if (pointStr == null || pointStr.trim().isEmpty()) {
                            continue;
                        }

                        Student student = new Student();
                        student.setNo(studentNo);
                        student.setClassNum(classNum);
                        Subject subject = new Subject();
                        subject.setCd(subjectCd);
                        School school = teacher.getSchool();
                        Test test = new Test();
                        test.setStudent(student);
                        test.setSchool(school);
                        test.setNo(testNo);
                        test.setPoint(Integer.parseInt(pointStr));
                        test.setClassNum(classNum);

                        if (testDao.testExists(studentNo, subjectCd, schoolCd, testNo)) {
                            testDao.updateTest(test, subject);
                        } else {
                            testDao.insertTest(test, subject, student);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                allSuccess = false;
                errorMsg = "データベースの更新中にエラーが発生しました。";
            }
        }

        // --- 4. 画面遷移 ---
        // 遷移前に必要な情報をリクエストにセット
        req.setAttribute("inputErrors", inputErrors);
        req.setAttribute("errorMsg", errorMsg);
        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);
        req.setAttribute("postedValues", req.getParameterMap()); // 入力値復元用


        // エラー時、または「再度入力」が押された場合は、入力画面を再表示
        if ("again".equals(action) || !allSuccess) {
            // DAOを準備
            StudentDAO stuDao = new StudentDAO();
            SubjectDAO subDao = new SubjectDAO();
            TestDAO testDao = new TestDAO();

            // セレクトボックス用のリストをセット
            req.setAttribute("student", stuDao.getAllEntYear(teacher));
            req.setAttribute("classNums", stuDao.getAllClassNum(teacher));
            req.setAttribute("subjects", subDao.getAllSubjects());

            // 成績一覧を再取得してセット（検索条件が揃っている場合）
            if (f1 != null && !f1.isEmpty() && f2 != null && !f2.isEmpty()
                && f3 != null && !f3.isEmpty() && f4 != null && !f4.isEmpty()) {

                List<Test> scorelist = testDao.searchTests(Integer.parseInt(f1), f2, f3, Integer.parseInt(f4));
                req.setAttribute("scorelist", scorelist);

                Subject subject = subDao.getSubjectByCd(f3);
                if (subject != null) {
                    req.setAttribute("subjectName", subject.getName());
                }
            }
            req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);
        } else {
            // 成功時は完了画面へ
            req.getRequestDispatcher("/main/grade/GRMU002.jsp").forward(req, resp);
        }
    }
}