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


public class ClassNumDAO extends DAO {
   private Connection connection;
   public ClassNumDAO(Connection connection) {
       this.connection = connection;
   }
   // クラスの追加
   public void addClassNum(ClassNum classNum) throws SQLException {
       try (Connection con = getConnection()){
    	   String sql = "INSERT INTO class_num (class_num, school_cd) VALUES (?, ?)";
    	   PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, classNum.getClass_num());
           stmt.setString(2, classNum.getSchool().getCd());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
   // クラス番号で取得
   public ClassNum getClassNumById(String classNumStr) throws SQLException {
       try (Connection con = getConnection()) {
    	   String sql = "SELECT * FROM class_num WHERE class_num = ?";
    	   PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, classNumStr);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               ClassNum classNum = new ClassNum();
               classNum.setClass_num(rs.getString("class_num"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               classNum.setSchool(school);
               return classNum;
           }
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return null;
   }
		// 全クラスの取得
		public List<ClassNum> getAllClassNums() throws SQLException {
			List<ClassNum> list = new ArrayList<>();
			try (Connection con = getConnection();
			     Statement stmt = con.createStatement()) {

				String sql = "SELECT * FROM class_num";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					ClassNum classNum = new ClassNum();
					classNum.setClass_num(rs.getString("class_num"));

					School school = new School();
					school.setCd(rs.getString("school_cd"));
					classNum.setSchool(school);

					list.add(classNum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

   // クラスの更新
   public void updateClassNum(ClassNum classNum) throws SQLException {
       try (Connection con = getConnection()) {
           String sql = "UPDATE class_num SET school_cd = ? WHERE class_num = ?";
    	   PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, classNum.getSchool().getCd());
           stmt.setString(2, classNum.getClass_num());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
   // クラスの削除
   public void deleteClassNum(String classNumStr) throws SQLException {
       try (Connection con = getConnection()) {
           String sql = "DELETE FROM class_num WHERE class_num = ?";
    	   PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, classNumStr);
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
}