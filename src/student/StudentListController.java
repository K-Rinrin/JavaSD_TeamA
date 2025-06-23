package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = {"/main/student/STDM001"})
public class StudentListController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // セッションからログインユーザー情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");



        // ログインチェック
        if (teacher == null || teacher.getSchool() == null) {
            resp.sendRedirect(req.getContextPath() + "/main/accounts/login");
            return;
        }



        StudentDAO dao = new StudentDAO();
        Map<String, String> errors = new HashMap<>();



        // パラメータの受け取り
        String entYearParam = req.getParameter("entYear");
        Integer entYear =
        		(entYearParam != null && !entYearParam.isEmpty()) ? Integer.parseInt(entYearParam) : null;

        String classNum = req.getParameter("classNum");
        if (classNum != null && classNum.isEmpty()) {
            classNum = null;
        }


        String filter = req.getParameter("filter");
        Boolean isAttend = null;

        List<Student> studentList = new ArrayList<>();




        try {
            if (filter != null) {
                // "絞込み"ボタンが押された時の処理
                req.setAttribute("filtered", true);


                // バリデーションチェック
                if (classNum != null && entYear == null) {
                    errors.put("entYear", "クラスを指定する場合は入学年度も選択してください");
                    req.setAttribute("errors", errors);
                    // エラーがある場合はstudentListは空のまま
                } else {
                    String isAttendParam = req.getParameter("isAttend");



                    // チェックボックスがチェックされている場合のみ isAttend に true をセットする
                    // チェックされていない場合は、isAttend は初期値の null のまま
                    if ("true".equals(isAttendParam)) {
                        isAttend = true;
                    }


                    studentList = dao.findStudents(entYear, classNum, isAttend, teacher);
                }
            } else {
                // 初回アクセス時や絞り込みボタンが押されていない場合
                // 全学生を表示
                studentList = dao.findStudents(null, null, null, teacher);
            }




            // プルダウン用のリストを取得
            List<String> classNumList = dao.getAllClassNum(teacher);
            List<Integer> entYearList = dao.getAllEntYear(teacher);


            // JSPに渡すデータをリクエストにセット
            req.setAttribute("allent", entYearList);
            req.setAttribute("allclass", classNumList);
            req.setAttribute("student", studentList);
            req.setAttribute("件数", studentList.size());


            // 検索条件の保持（画面の入力値を復元するため）
            req.setAttribute("entYear", entYear);
            req.setAttribute("classNum", classNum);


            // isAttend が true の場合のみリクエストにセット
            if (isAttend != null && isAttend) {
                req.setAttribute("isAttend", true);
            }



        } catch (Exception e) {

            e.printStackTrace();
            req.setAttribute("errorMessage", "データベースへのアクセス中にエラーが発生しました。");

        }



        // JSPにフォワード
        req.getRequestDispatcher("/main/student/STDM001.jsp").forward(req, resp);
    }






    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // POSTリクエストが来た場合もGETメソッドと同じ処理を行う

    }
}