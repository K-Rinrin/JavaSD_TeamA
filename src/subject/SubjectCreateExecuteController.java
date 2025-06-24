package subject;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.CommonServlet;


@WebServlet(urlPatterns = { "/main/subject/SBJM002Execute" })
public class SubjectCreateExecuteController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }




    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // JSPからのリクエストはPOSTで受け取るため、文字コードを設定
        req.setCharacterEncoding("UTF-8");


        try {
            // フォームから取得した科目コードと名前を取得
            String cd = req.getParameter("cd");
            String name = req.getParameter("name");
            Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");



            // エラー情報まとめ
            Map<String, String> errors = new HashMap<>();


            // DAO をインスタンス化
            SubjectDAO dao = new SubjectDAO();




            // ------------ エラー情報のチェック ------------


            // 科目番号の必須入力チェック
            if (cd == null || cd.trim().isEmpty()) { // trim()で空白のみもエラーに
                errors.put("cd", "科目コードを入力してください");
            } else {
                // 科目番号の重複チェック
                Subject subjectExists = dao.getSubjectByCd(cd);


                if (subjectExists != null && subjectExists.getCd() != null &&
                    subjectExists.getSchool().getCd().equals(teacher.getSchool().getCd())) {
                    errors.put("cd", "科目コードが重複しています");
                }
            }


            // ------------ エラー情報のチェック ------------




            // エラーがある場合の処理
            if (!errors.isEmpty()) {

                req.setAttribute("errors", errors);
                req.setAttribute("cd", cd);
                req.setAttribute("name", name);

                // 登録画面にフォワード
                req.getRequestDispatcher("/main/subject/SBJM002.jsp").forward(req, resp);
                return;
            }





            // エラーがなかった場合の処理
            Subject subject = new Subject();
            subject.setSchool(teacher.getSchool());
            subject.setCd(cd);
            subject.setName(name);


            // DAO を使って科目をDBに登録
            dao.addSubject(subject);

            // 登録完了画面へ遷移する
            resp.sendRedirect(req.getContextPath() + "/main/subject/SBJM003.jsp");




        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "科目の登録に失敗しました。");
            req.setAttribute("cd", req.getParameter("cd"));
            req.setAttribute("name", req.getParameter("name"));
            req.getRequestDispatcher("/main/subject/SBJM002.jsp").forward(req, resp);
        }
    }
}