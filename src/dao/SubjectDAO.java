package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;


public class SubjectDAO extends DAO {

   // 科目の追加
   public void addSubject(Subject subject) throws SQLException {
       try (Connection con = getConnection()) {
    	   String sql = "INSERT INTO subject (school_cd,cd,name) VALUES (?, ?, ?)";
    	   PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, subject.getSchool().getCd());
           stmt.setString(2, subject.getCd());
           stmt.setString(3, subject.getName());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
   // 科目コードで取得
   public Subject getSubjectByCd(String cd) throws SQLException {
       String sql = "SELECT * FROM subject WHERE cd = ?";
       try (Connection con = getConnection()) {
    	   PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, cd);
           ResultSet rs = stmt.executeQuery();
           Subject subject = new Subject();
           School school = new School();
           if (rs.next()) {
               school.setCd(rs.getString("school_cd"));
               subject.setCd(rs.getString("cd"));
               subject.setName(rs.getString("name"));
               subject.setSchool(school);
           }
           return subject;
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return null;
   }
   // 全科目の取得
   public List<Subject> getAllSubjects() throws SQLException {
       List<Subject> list = new ArrayList<>();
       try (Connection con = getConnection()) {
    	   String sql = "SELECT * FROM subject ORDER BY cd";
    	   PreparedStatement stmt = con.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Subject subject = new Subject();
               School school = new School();
               subject.setCd(rs.getString("cd"));
               subject.setName(rs.getString("name"));
               school.setCd(rs.getString("school_cd"));
               subject.setSchool(school);
               list.add(subject);
           }
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return list;
   }
   // 科目の更新
   public void updateSubject(Subject subject) throws SQLException {
	    try (Connection con = getConnection()) {
	    	String sql = "UPDATE subject SET name = ? WHERE cd = ?";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setString(1, subject.getName());       // 正しくnameをセット
	        stmt.setString(2, subject.getCd());         // WHERE条件にcdを使う
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

   // 科目の削除
   public void deleteSubject(String cd) throws SQLException {
       String sql = "DELETE FROM subject WHERE cd = ?";
       try (Connection con = getConnection()) {
    	   PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, cd);
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
}