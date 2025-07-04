package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    // ある学生のすべてのテスト結果（科目ごと）を取得
    public List<TestListStudent> getTestListByStudentNo(String studentNo, String schoolCd) throws SQLException {
        List<TestListStudent> list = new ArrayList<>();

        try (Connection con = getConnection()) {
            String sql = "SELECT "
                    + "S.NO AS STUDENT_NO, "
                    + "S.NAME AS STUDENT_NAME, "
                    + "S.SCHOOL_CD AS STUDENT_SCHOOL_CD, "
                    + "SUB.CD AS SUBJECT_CD, "
                    + "SUB.NAME AS SUBJECT_NAME, "
                    + "T.NO AS TEST_NO, "
                    + "T.POINT "
                    + "FROM STUDENT S "
                    + "INNER JOIN TEST T ON S.NO = T.STUDENT_NO AND S.SCHOOL_CD = T.SCHOOL_CD "
                    + "INNER JOIN SUBJECT SUB ON T.SUBJECT_CD = SUB.CD AND T.SCHOOL_CD = SUB.SCHOOL_CD "
                    + "WHERE S.NO = ? "
                    + "AND S.SCHOOL_CD = ? "
                    + "ORDER BY SUB.CD, T.NO";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, studentNo);
            stmt.setString(2, schoolCd);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TestListStudent test = new TestListStudent();
                test.setSubjectName(rs.getString("SUBJECT_NAME"));
                test.setSubjectCd(rs.getString("SUBJECT_CD"));
                test.setNum(rs.getInt("TEST_NO"));
                test.setPoint(rs.getInt("POINT"));

                // test.setStudentNo(rs.getString("STUDENT_NO"));
                // test.setStudentName(rs.getString("STUDENT_NAME"));a

                list.add(test);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
