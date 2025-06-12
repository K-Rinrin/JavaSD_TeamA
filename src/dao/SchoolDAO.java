package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.School;
public class SchoolDAO extends DAO {
   private Connection connection;
   public SchoolDAO(Connection connection) {
       this.connection = connection;
   }
   // 学校を追加
   public void addSchool(School school) throws SQLException {
       String sql = "INSERT INTO school (cd, name) VALUES (?, ?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, school.getCd());
           stmt.setString(2, school.getName());
           stmt.executeUpdate();
       }
   }
   // 学校コードで取得
   public School getSchoolByCd(String cd) throws SQLException {
       String sql = "SELECT * FROM school WHERE cd = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, cd);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               School school = new School();
               school.setCd(rs.getString("cd"));
               school.setName(rs.getString("name"));
               return school;
           }
       }
       return null;
   }
   // 全学校取得
   public List<School> getAllSchools() throws SQLException {
       List<School> list = new ArrayList<>();
       String sql = "SELECT * FROM school";
       try (Statement stmt = connection.createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               School school = new School();
               school.setCd(rs.getString("cd"));
               school.setName(rs.getString("name"));
               list.add(school);
           }
       }
       return list;
   }
   // 学校情報更新
   public void updateSchool(School school) throws SQLException {
       String sql = "UPDATE school SET name = ? WHERE cd = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, school.getName());
           stmt.setString(2, school.getCd());
           stmt.executeUpdate();
       }
   }
   // 学校削除
   public void deleteSchool(String cd) throws SQLException {
       String sql = "DELETE FROM school WHERE cd = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, cd);
           stmt.executeUpdate();
       }
   }
}