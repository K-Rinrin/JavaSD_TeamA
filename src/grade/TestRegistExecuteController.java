package grade;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns = {"/main/grade/GRMU001Execute"})
public class TestRegistExecuteController extends CommonServlet {

    // GETリクエストが来た場合はGRMU001（一覧）画面にリダイレクト
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.sendRedirect(req.getContextPath() + "/main/grade/GRMU001");
    }

    // POSTリクエスト（成績登録/更新/削除）処理
    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // 検索条件（画面の hidden input で保持されている）
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目コード
        String f4 = req.getParameter("f4"); // 回数

        // セッションからログイン中の先生を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");

        // DAO生成
        TestDAO testDao = new TestDAO();

        // 各種フラグ・メッセージ
        String errorMsg = null;
        boolean allSuccess = true;
        int processedCount = 0; // 登録/更新/削除した件数

        // 学校コードの取得（先生の所属から）
        String schoolCd = null;
        if (teacher != null && teacher.getSchool() != null) {
            schoolCd = teacher.getSchool().getCd();
        } else {
            errorMsg = "学校情報が取得できません。ログインし直してください。";
            allSuccess = false;
        }

        try {
            // 入力チェック（検索条件がすべて揃っているか）
            if (f1 == null || f1.isEmpty() || f2 == null || f2.isEmpty()
                    || f3 == null || f3.isEmpty() || f4 == null || f4.isEmpty()) {
                allSuccess = false;
                errorMsg = "入学年度、クラス、科目、回数をすべて選択してください。";
            }

            if (allSuccess) {
                int entYear = Integer.parseInt(f1);
                String classNum = f2;
                String subjectCd = f3;
                int testNo = Integer.parseInt(f4);

                // 全パラメータを確認（point_ 学生番号の形式で繰り返し）
                for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
                    String paramName = entry.getKey();

                    // 成績入力欄
                    if (paramName.startsWith("point_")) {
                        String studentNo = paramName.substring("point_".length());
                        String pointStr = entry.getValue()[0];

	                 // 入力されていない場合はスキップ
	                 if (pointStr == null || pointStr.trim().isEmpty()) {
	                     continue; // 入力されてない学生は登録・更新・削除しない
	                 }

	                 int point;
	                 try {
	                     point = Integer.parseInt(pointStr);
	                     if (point < 0 || point > 100) {
	                         errorMsg = (errorMsg == null ? "" : errorMsg + "<br>")
	                                 + "学生番号 " + studentNo + " の得点（" + pointStr + "）は0～100で入力してください。";
	                         allSuccess = false;
	                         continue;
	                     }
	                 } catch (NumberFormatException e) {
	                     errorMsg = (errorMsg == null ? "" : errorMsg + "<br>")
	                             + "学生番号 " + studentNo + " の得点（" + pointStr + "）は数値で入力してください。";
	                     allSuccess = false;
	                     continue;
	                 }


                        // 削除チェックがオンかどうか
                        boolean deleteChecked = req.getParameter("check_" + studentNo) != null;

                        // Testオブジェクト生成
                        Student student = new Student();
                        student.setNo(studentNo);
                        student.setClassNum(classNum);

                        School school = new School();
                        school.setCd(schoolCd);

                        Subject subject = new Subject();
                        subject.setCd(subjectCd);

                        Test test = new Test();
                        test.setStudent(student);
                        test.setSchool(school); // Bean側に合わせて setSchool 使用
                        test.setNo(testNo);
                        test.setPoint(point);

                        // 削除処理
                        if (deleteChecked) {
                            if (testDao.deleteTest(studentNo, subjectCd, schoolCd, testNo)) {
                                processedCount++;
                            } else {
                                allSuccess = false;
                                errorMsg = (errorMsg == null ? "" : errorMsg + "<br>")
                                        + "学生番号 " + studentNo + " の削除に失敗しました。";
                            }

                        // 登録・更新処理
                        } else {
                            if (testDao.testExists(studentNo, subjectCd, schoolCd, testNo)) {
                                if (testDao.updateTest(test, subject)) {
                                    processedCount++;
                                } else {
                                    allSuccess = false;
                                    errorMsg = (errorMsg == null ? "" : errorMsg + "<br>")
                                            + "学生番号 " + studentNo + " の得点更新に失敗しました。";
                                }
                            } else {
                                if (testDao.insertTest(test, subject, student)) {
                                    processedCount++;
                                } else {
                                    allSuccess = false;
                                    errorMsg = (errorMsg == null ? "" : errorMsg + "<br>")
                                            + "学生番号 " + studentNo + " の成績登録に失敗しました。";
                                }
                            }
                        }
                    }
                }
            }

            // 最終メッセージ決定
            if (!allSuccess) {
                if (errorMsg == null) {
                    errorMsg = "成績の登録または削除に失敗しました。";
                }
            } else {
                if (processedCount > 0) {
                    errorMsg = "成績の登録が完了しました。";
                } else {
                    errorMsg = "変更された成績はありませんでした。";
                }
            }

        } catch (NumberFormatException e) {
            errorMsg = "検索条件の値が不正です。";
            e.printStackTrace();
        } catch (Exception e) {
            errorMsg = "成績の登録中にエラーが発生しました。";
            e.printStackTrace();
        }


        // メッセージと検索条件をリクエストに保持
        req.setAttribute("errorMsg", errorMsg);
        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);

        // 処理後、GETにフォワードして結果を画面に反映
        req.getRequestDispatcher("/main/grade/GRMU002.jsp").forward(req, resp);
    }
}
