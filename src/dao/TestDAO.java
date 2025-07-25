package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDAO extends DAO {

    // 全件取得
	//指定した学校の成績だけを取得
	public List<Test> getTestsBySchool(String schoolCd) throws SQLException {
	    List<Test> list = new ArrayList<>();
	    try (Connection con = getConnection()) {
	        String sql = "SELECT * FROM TEST WHERE SCHOOL_CD = ?";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, schoolCd);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            Test test = new Test();
	            Student stu = new Student();
	            School sch = new School();

	            stu.setNo(rs.getString("STUDENT_NO"));
	            test.setStudent(stu);

	            test.setClassNum(rs.getString("CLASS_NUM"));
	            test.setNo(rs.getInt("NO"));
	            test.setPoint(rs.getInt("POINT"));
	            sch.setCd(rs.getString("SCHOOL_CD"));
	            test.setSchool(sch);

	            list.add(test);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}


    public List<Test> searchTests(int entYear, String classNum, String subjectCd, int testNo,String schoolCd) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection con = null; // ローカル変数として宣言
        PreparedStatement st = null; // ローカル変数として宣言
        ResultSet rs = null; // ローカル変数として宣言

        try {
            con = getConnection(); // CommonDAOからConnectionを取得
            String sql = "SELECT "
                    + "S.NO AS STUDENT_NO, "
                    + "S.NAME AS STUDENT_NAME, "
                    + "S.ENT_YEAR, "
                    + "S.CLASS_NUM, "
                    + "T.POINT, "
                    + "T.SUBJECT_CD, "
                    + "T.NO AS TEST_NO, "
                    + "S.SCHOOL_CD AS SCHOOL_CD, "
                    + "SUB.NAME AS SUBJECT_NAME "
                    + "FROM STUDENT S "
                    + "LEFT JOIN TEST T ON S.NO = T.STUDENT_NO "
                    + "AND T.SUBJECT_CD = ? "
                    + "AND T.NO = ? "
                    + "AND T.SCHOOL_CD = S.SCHOOL_CD " // 学生の学校とテストの学校が一致
                    + "LEFT JOIN SUBJECT SUB ON T.SUBJECT_CD = SUB.CD AND SUB.SCHOOL_CD = S.SCHOOL_CD " // 科目の学校も学生の学校と一致
                    + "WHERE S.ENT_YEAR = ? "
                    + "AND S.CLASS_NUM = ? "
                    + "AND S.SCHOOL_CD = ? "
                    + "ORDER BY S.NO";

            st = con.prepareStatement(sql);
            st.setString(1, subjectCd);
            st.setInt(2, testNo);
            st.setInt(3, entYear);
            st.setString(4, classNum);
            st.setString(5, schoolCd);

            rs = st.executeQuery();

            while (rs.next()) {
                Test test = new Test();
                Student stu = new Student();
                Subject sub = new Subject();
                School school = new School(); // Schoolオブジェクトを追加

                stu.setNo(rs.getString("STUDENT_NO"));
                stu.setName(rs.getString("STUDENT_NAME"));
                stu.setEntYear(rs.getInt("ENT_YEAR"));
                stu.setClassNum(rs.getString("CLASS_NUM"));
                school.setCd(rs.getString("SCHOOL_CD"));

                sub.setCd(rs.getString("SUBJECT_CD")); // TestのSubjectCDをセット
                sub.setName(rs.getString("SUBJECT_NAME")); // TestのSubjectNameをセット

                school.setCd(rs.getString("SCHOOL_CD")); // Schoolオブジェクトに学校コードをセット

                test.setStudent(stu);
//                test.setSubject(sub);
                test.setSchool(school); // TestオブジェクトにSchoolオブジェクトをセット
                test.setNo(rs.getInt("TEST_NO")); // テストの回数をセット
                test.setPoint(rs.getInt("POINT"));

                list.add(test);
            }

        } catch (SQLException e) { // ExceptionではなくSQLExceptionをキャッチ
            e.printStackTrace();
            throw e; // 例外を再スローして呼び出し元で処理させる
        } finally {
            // リソースのクローズ処理を確実に行う
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


//変更・更新
    public boolean updateTest(Test test, Subject sub) throws SQLException {
        try (Connection con = getConnection()) {
            String sql = "UPDATE TEST SET POINT=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND NO=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, test.getPoint());
            st.setString(2, test.getStudent().getNo());
            st.setString(3, sub.getCd());
            st.setInt(4, test.getNo());


            int count = st.executeUpdate(); // 更新された行数を取得
            return count > 0; // 1行以上更新されれば成功

        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return false;
    }

//削除
    public boolean deleteTest(String studentNo, String subjectCd, String schoolCd, int testNo) throws SQLException {
        try (Connection con = getConnection()) {
            // TESTテーブルにSCHOOL_CDカラムがある場合、WHERE句に追加
            String sql = "DELETE FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND NO=? AND SCHOOL_CD=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setInt(3, testNo);
            st.setString(4, schoolCd);


            int count = st.executeUpdate(); // 削除された行数を取得
            return count > 0; // 1行以上削除されれば成功
        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return false;
    }

//追加
    public boolean insertTest(Test test, Subject sub, Student student) throws SQLException {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, test.getStudent().getNo());
            st.setString(2, sub.getCd());
            // 学校コードは必須項目なので、Testオブジェクトに含まれている前提
            st.setString(3, test.getSchool().getCd());
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, student.getClassNum());



            int count = st.executeUpdate();
            return count > 0;
        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return false;
    }


//テストが存在するか否か
    public boolean testExists(String studentNo, String subjectCd, String schoolCd, int testNo) throws SQLException {
        try (Connection con = getConnection()) {
            String sql = "SELECT COUNT(*) FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setString(3, schoolCd);
            st.setInt(4, testNo);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return false;
    }
}
