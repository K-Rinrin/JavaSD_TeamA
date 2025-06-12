package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {
   private Connection connection;
   public TestListSubjectDAO(Connection connection) {
       this.connection = connection;
   }
   // クラス番号を指定して一覧を取得
   public List<TestListSubject> getTestListByClass(String classNum) throws SQLException {
       String sql = "SELECT s.ent_year, s.no AS student_no, s.name AS student_name, sub.name AS subject_name,  t.point FROM test t JOIN student s ON t.student_no = s.no  JOIN  subject sub ON t.subject_cd = sub.cd  WHERE  s.class_num = ? ORDER BY s.no, sub.cd ";
       List<TestListSubject> result = new ArrayList<>();
       Map<String, TestListSubject> studentMap = new LinkedHashMap<>();
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, classNum);
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               String studentNo = rs.getString("student_no");
               TestListSubject tls = studentMap.get(studentNo);
               if (tls == null) {
                   tls = new TestListSubject();
                   tls.setEntYear(rs.getInt("ent_year"));
                   tls.setStudentNo(studentNo);
                   tls.setStudentName(rs.getString("student_name"));
                   tls.setPoints(new LinkedHashMap<>());
                   studentMap.put(studentNo, tls);
               }
               // Map に科目名と点数を追加
               tls.getPoints().put(rs.getString("subject_name"), rs.getInt("point"));
           }
       }
       result.addAll(studentMap.values());
       return result;
   }
}