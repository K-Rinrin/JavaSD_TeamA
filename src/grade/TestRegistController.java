package grade;

import java.util.Comparator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import bean.Test;
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
            Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");
            if (teacher == null) {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }

            // ==================== 1. セレクトボックス用のデータを常に取得 ====================
            StudentDAO stu_dao = new StudentDAO();
            req.setAttribute("student", stu_dao.getAllEntYear(teacher));
            req.setAttribute("classNums", stu_dao.getAllClassNum(teacher));

            SubjectDAO sub_dao = new SubjectDAO();
            List<Subject> sub_list = sub_dao.getAllSubjectsBySchool(teacher.getSchool().getCd());
            sub_list.sort(Comparator.comparing(Subject::getCd));
            req.setAttribute("subjects", sub_list);


            // ==================== 2. 検索処理 ====================
            String f1 = req.getParameter("f1");
            String f2 = req.getParameter("f2");
            String f3 = req.getParameter("f3");
            String f4 = req.getParameter("f4");
            String searchBtn = req.getParameter("search"); // 検索ボタンが押されたか



            // 検索ボタンが押された場合のみ、バリデーションと検索処理を行う
            if (searchBtn != null && searchBtn.equals("true")) {

                // (1) 検索条件の必須チェック
                if (f1 == null || f1.isEmpty() || f2 == null || f2.isEmpty()
                        || f3 == null || f3.isEmpty() || f4 == null || f4.isEmpty()) {
                    req.setAttribute("errorMsg", "入学年度、クラス、科目、回数をすべて選択してください。");
                } else {
                    // (2) 検索実行
                    try {
                        int entYear = Integer.parseInt(f1);
                        String classNum = f2;
                        String subjectCd = f3;
                        int testNo = Integer.parseInt(f4);
                        String schoolCd = teacher.getSchool().getCd();

                        TestDAO testDao = new TestDAO();
                        List<Test> scorelist = testDao.searchTests(entYear, classNum, subjectCd, testNo,schoolCd);
                        req.setAttribute("scorelist", scorelist);

                        // 検索結果が0件の場合のメッセージ
                        if (scorelist.isEmpty()) {
                            req.setAttribute("errorMsg", "指定された条件に一致する学生情報が見つかりませんでした。");
                        }

                        Subject subject = sub_dao.getSubjectByCd(subjectCd,teacher.getSchool().getCd());
                        if (subject != null) {
                            req.setAttribute("subjectName", subject.getName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        req.setAttribute("errorMsg", "データの検索中にエラーが発生しました。");
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "画面の表示中に予期せぬエラーが発生しました。");
        }



        // ==================== 3. 画面遷移 ====================
        // 検索条件をリクエストに保持してJSPに渡す
        req.setAttribute("f1", req.getParameter("f1"));
        req.setAttribute("f2", req.getParameter("f2"));
        req.setAttribute("f3", req.getParameter("f3"));
        req.setAttribute("f4", req.getParameter("f4"));


        req.getRequestDispatcher("/main/grade/GRMU001.jsp").forward(req, resp);
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }
}