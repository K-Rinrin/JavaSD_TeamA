package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
public class TeacherDAO {
   private Connection connection;
   public TeacherDAO(Connection connection) {
       this.connection = connection;
   }
   // 教員の追加
   public void addTeacher(Teacher teacher) throws SQLException {
       String sql = "INSERT INTO teacher (id, password, name, school_cd) VALUES (?, ?, ?, ?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, teacher.getId());
           stmt.setString(2, teacher.getPassword());
           stmt.setString(3, teacher.getName());
           stmt.setString(4, teacher.getSchool().getCd());
           stmt.executeUpdate();
       }
   }
   // IDで教員を取得
   public Teacher getTeacherById(String id) throws SQLException {
       String sql = "SELECT * FROM teacher WHERE id = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, id);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               Teacher teacher = new Teacher();
               teacher.setId(rs.getString("id"));
               teacher.setPassword(rs.getString("password"));
               teacher.setName(rs.getString("name"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               teacher.setSchool(school);
               return teacher;
           }
       }
       return null;
   }
   // 全教員を取得
   public List<Teacher> getAllTeachers() throws SQLException {
       List<Teacher> list = new ArrayList<>();
       String sql = "SELECT * FROM teacher";
       try (Statement stmt = connection.createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               Teacher teacher = new Teacher();
               teacher.setId(rs.getString("id"));
               teacher.setPassword(rs.getString("password"));
               teacher.setName(rs.getString("name"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               teacher.setSchool(school);
               list.add(teacher);
           }
       }
       return list;
   }
   // 教員情報の更新
   public void updateTeacher(Teacher teacher) throws SQLException {
       String sql = "UPDATE teacher SET password = ?, name = ?, school_cd = ? WHERE id = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, teacher.getPassword());
           stmt.setString(2, teacher.getName());
           stmt.setString(3, teacher.getSchool().getCd());
           stmt.setString(4, teacher.getId());
           stmt.executeUpdate();
       }
   }
   // 教員の削除
   public void deleteTeacher(String id) throws SQLException {
       String sql = "DELETE FROM teacher WHERE id = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, id);
           stmt.executeUpdate();
       }
   }
}