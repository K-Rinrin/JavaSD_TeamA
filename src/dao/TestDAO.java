package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Test;
import bean.TestListSubject;

public class TestDAO extends DAO {

    // 全件取得
    public List<Test> getAllTests() throws Exception {
        List<Test> list = new ArrayList<>();
        try(Connection con = getConnection()) {
        	String sql = "SELECT * FROM TEST";
            PreparedStatement st = con.prepareStatement(sql);
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
                test.setcShool(sch); // typoに合わせるなら setcShool

                list.add(test);
            }

            rs.close();
            st.close();
            con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
    }

    // 条件付き検索（得点入力用）
    public List<TestListSubject> searchTests(int entYear, String classNum, String subjectCd, int testNo) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        try(Connection con = getConnection()) {
        	String sql = "SELECT"
            		+ "S.NO AS STUDENT_NO,"
            		+ "S.NAME AS STUDENT_NAME,"
            		+ "S.ENT_YEAR,"
            		+ "S.CLASS_NUM,"
            		+ "T.POINT"
            		+ "FROM"
            		+ "STUDENT S"
            		+ "LEFT JOIN"
            		+ "TEST T ON S.NO = T.STUDENT_NO"
            		+ "AND T.SUBJECT_CD = ? AND T.NO = ?"
            		+ "WHERE"
            		+ "S.ENT_YEAR = ? AND S.CLASS_NUM = ?"
            		+ "ORDER BY"
            		+ "S.NO";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, subjectCd);
            st.setInt(2, testNo);
            st.setInt(3, entYear);
            st.setString(4, classNum);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TestListSubject test = new TestListSubject();
                test.setStudentNo(rs.getString("STUDENT_NO"));
                test.setStudentName(rs.getString("STUDENT_NAME"));
                test.setEntYear(rs.getInt("ENT_YEAR"));

                // 得点（null の場合 0）
                Map<String, Integer> points = new HashMap<>();
                points.put(subjectCd, rs.getInt("POINT"));  // 1教科だけなのでsubjectCdで管理
                test.setPoints(points);

                list.add(test);
            }

            rs.close();
            st.close();
            con.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

        return list;
    }

    // テスト追加
    public void addTest(Test test, String subjectCd) throws Exception {
    	try(Connection con = getConnection()) {
            String sql = "INSERT INTO TEST VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getClassNum());
            st.setString(3, test.getcShool().getCd());  // getcShool() を使うなら注意
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, subjectCd);

            st.executeUpdate();

            st.close();
            con.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    // テスト更新
    public void updateTest(Test test, String subjectCd) throws Exception {

        try(Connection con = getConnection()) {
        	String sql = "UPDATE TEST SET POINT=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND NO=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, test.getPoint());
            st.setString(2, test.getStudent().getNo());
            st.setString(3, subjectCd);
            st.setInt(4, test.getNo());

            st.executeUpdate();

            st.close();
            con.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    // テスト削除
    public void deleteTest(String studentNo, String subjectCd, int testNo) throws Exception {
        try(Connection con = getConnection()) {
            String sql = "DELETE FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND NO=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setInt(3, testNo);

            st.executeUpdate();

            st.close();
            con.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
