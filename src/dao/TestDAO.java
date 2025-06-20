package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
       try (Connection con = getConnection()) {
           String sql = "INSERT INTO test (student_no, class_num, school_cd, no, point) VALUES (?, ?, ?, ?, ?)";
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, test.getStudent().getNo());
           stmt.setString(2, test.getClassNum());
           stmt.setString(3, test.getcShool().getCd());
           stmt.setInt(4, test.getNo());
           stmt.setInt(5, test.getPoint());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
   // 特定テスト取得（主キーが no のみ or 複合キー？仮に no だけで）
   public Test getTestByNo(int no) throws SQLException {
       try (Connection con = getConnection()) {
           String sql = "SELECT * FROM test WHERE no = ?";
           PreparedStatement stmt = connection.prepareStatement(sql);
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
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return null;
   }
   // 全テスト取得
   public List<Test> getAllTests() throws SQLException {
       List<Test> list = new ArrayList<>();
       try (Connection con = getConnection()) {
           String sql = "SELECT * FROM test";
           PreparedStatement stmt = connection.prepareStatement(sql);
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
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return list;
   }
   // テスト更新
   public void updateTest(Test test) throws SQLException {
       try (Connection con = getConnection()) {
           String sql = "UPDATE test SET student_no = ?, class_num = ?, school_cd = ?, point = ? WHERE no = ?";
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, test.getStudent().getNo());
           stmt.setString(2, test.getClassNum());
           stmt.setString(3, test.getcShool().getCd());
           stmt.setInt(4, test.getPoint());
           stmt.setInt(5, test.getNo());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
   // テスト削除
   public void deleteTest(String studentNo, String subjectCd,int no) throws SQLException {
       try (Connection con = getConnection()) {
           String sql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND no = ?";
    	   PreparedStatement stmt = connection.prepareStatement(sql);
    	   stmt.setString(1, studentNo);
           stmt.setString(2, subjectCd);
           stmt.setInt(3, no);
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
}