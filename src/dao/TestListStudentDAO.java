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
   public List<TestListStudent> getTestListByStudentNo(String studentNo) throws SQLException {
       List<TestListStudent> list = new ArrayList<>();

       try (Connection con = getConnection()) {
    	   String sql = "SELECT s.name AS subject_name, t.subject_cd, t.num, t.point " +
                    "FROM test t " +
                    "JOIN subject s ON t.subject_cd = s.cd " +
                    "WHERE t.student_no = ? " +
                    "ORDER BY t.subject_cd, t.num";
    	   	PreparedStatement stmt = con.prepareStatement(sql);
    	   	stmt.setString(1, studentNo);
			ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               TestListStudent test = new TestListStudent();
               test.setSubjectName(rs.getString("subject_name"));
               test.setSubjectCd(rs.getString("subject_cd"));
               test.setNum(rs.getInt("num"));
               test.setPoint(rs.getInt("point"));
               list.add(test);
           }
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return list;
   }
}

