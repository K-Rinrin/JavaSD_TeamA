package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;


public class TestDAO extends DAO {
   private Connection connection;
   public TestDAO(Connection connection) {
       this.connection = connection;
   }
   // テスト登録
   public void addTest(Test test) throws SQLException {
       String sql = "INSERT INTO test (student_no, class_num, school_cd, no, point) VALUES (?, ?, ?, ?, ?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, test.getStudent().getNo());
           stmt.setString(2, test.getClassNum());
           stmt.setString(3, test.getcShool().getCd());
           stmt.setInt(4, test.getNo());
           stmt.setInt(5, test.getPoint());
           stmt.executeUpdate();
       }
   }
   // 特定テスト取得（主キーが no のみ or 複合キー？仮に no だけで）
   public Test getTestByNo(int no) throws SQLException {
       String sql = "SELECT * FROM test WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setInt(1, no);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               Test test = new Test();
               Student student = new Student();
               student.setNo(rs.getString("student_no"));
               test.setStudent(student);
               test.setClassNum(rs.getString("class_num"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               test.setcShool(school);
               test.setNo(rs.getInt("no"));
               test.setPoint(rs.getInt("point"));
               return test;
           }
       }
       return null;
   }
   // 全テスト取得
   public List<Test> getAllTests() throws SQLException {
       List<Test> list = new ArrayList<>();
       String sql = "SELECT * FROM test";
       try (Statement stmt = connection.createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               Test test = new Test();
               Student student = new Student();
               student.setNo(rs.getString("student_no"));
               test.setStudent(student);
               test.setClassNum(rs.getString("class_num"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               test.setcShool(school);
               test.setNo(rs.getInt("no"));
               test.setPoint(rs.getInt("point"));
               list.add(test);
           }
       }
       return list;
   }
   // テスト更新
   public void updateTest(Test test) throws SQLException {
       String sql = "UPDATE test SET student_no = ?, class_num = ?, school_cd = ?, point = ? WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, test.getStudent().getNo());
           stmt.setString(2, test.getClassNum());
           stmt.setString(3, test.getcShool().getCd());
           stmt.setInt(4, test.getPoint());
           stmt.setInt(5, test.getNo());
           stmt.executeUpdate();
       }
   }
   // テスト削除
   public void deleteTest(int no) throws SQLException {
       String sql = "DELETE FROM test WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setInt(1, no);
           stmt.executeUpdate();
       }
   }
}