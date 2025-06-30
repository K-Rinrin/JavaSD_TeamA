package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {

   // クラス番号を指定して一覧を取得
	public List<TestListSubject> getTestListByClass(int ent_year, String classNum, String subject) throws SQLException {
	    List<TestListSubject> list = new ArrayList<>();

	    try (Connection con = getConnection()) {
	        String sql =
	            "SELECT " +
	            "s.ent_year, " +
	            "s.class_num, " +
	            "s.no AS student_no, " +
	            "s.name AS student_name, " +
	            "MAX(CASE WHEN t.no = 1 THEN t.point END) AS point_1, " +
	            "MAX(CASE WHEN t.no = 2 THEN t.point END) AS point_2 " +
	            "FROM test t " +
	            "JOIN student s ON t.student_no = s.no " +
	            "JOIN subject sub ON t.subject_cd = sub.cd " +
	            "WHERE s.ent_year = ? " +
	            "AND s.class_num = ? " +
	            "AND sub.cd = ? " +
	            "GROUP BY s.ent_year, s.class_num, s.no, s.name " +
	            "ORDER BY s.no";
	        	PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setInt(1, ent_year);
	            stmt.setString(2, classNum);
	            stmt.setString(3, subject);
	            ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                    TestListSubject tls = new TestListSubject();
	                    tls.setEntYear(rs.getInt("ent_year"));
	                    tls.setStudentNo(rs.getString("student_no"));
	                    tls.setStudentName(rs.getString("student_name"));

	                    LinkedHashMap<Integer, Integer> points = new LinkedHashMap<>();
	                    points.put(1, rs.getInt("point_1"));
	                    points.put(2, rs.getInt("point_2"));
	                    tls.setPoints(points);

	                    list.add(tls);
	                }

	    } catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	    return list;
	}
}