package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;
public class ClassNumDAO {
   private Connection connection;
   public ClassNumDAO(Connection connection) {
       this.connection = connection;
   }
   // クラスの追加
   public void addClassNum(ClassNum classNum) throws SQLException {
       String sql = "INSERT INTO class_num (class_num, school_cd) VALUES (?, ?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, classNum.getClass_num());
           stmt.setString(2, classNum.getSchoool().getCd());
           stmt.executeUpdate();
       }
   }
   // クラス番号で取得
   public ClassNum getClassNumById(String classNumStr) throws SQLException {
       String sql = "SELECT * FROM class_num WHERE class_num = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, classNumStr);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               ClassNum classNum = new ClassNum();
               classNum.setClass_num(rs.getString("class_num"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               classNum.setSchoool(school);
               return classNum;
           }
       }
       return null;
   }
   // 全クラスの取得
   public List<ClassNum> getAllClassNums() throws SQLException {
       List<ClassNum> list = new ArrayList<>();
       String sql = "SELECT * FROM class_num";
       try (Statement stmt = connection.createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               ClassNum classNum = new ClassNum();
               classNum.setClass_num(rs.getString("class_num"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               classNum.setSchoool(school);
               list.add(classNum);
           }
       }
       return list;
   }
   // クラスの更新
   public void updateClassNum(ClassNum classNum) throws SQLException {
       String sql = "UPDATE class_num SET school_cd = ? WHERE class_num = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, classNum.getSchoool().getCd());
           stmt.setString(2, classNum.getClass_num());
           stmt.executeUpdate();
       }
   }
   // クラスの削除
   public void deleteClassNum(String classNumStr) throws SQLException {
       String sql = "DELETE FROM class_num WHERE class_num = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, classNumStr);
           stmt.executeUpdate();
       }
   }
}