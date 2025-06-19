package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;


public class StudentDAO extends DAO {


   // 学生追加
   public void addStudent(Student student) throws SQLException {
       try (Connection con = getConnection()) {
           String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
           PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, student.getNo());
           stmt.setString(2, student.getName());
           stmt.setInt(3, student.getEntYear());
           stmt.setString(4, student.getClassNum());
           stmt.setBoolean(5, student.isAttend());
           stmt.setString(6, student.getSchool().getCd());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }


   // 学生番号で取得
   public Student getStudentByNo(String no) throws SQLException {

	   Student student = null;

       try (Connection con = getConnection()) {
    	   String sql = "SELECT * FROM student WHERE no = ?";
    	   PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, no);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
        	   student = new Student();
               student.setNo(rs.getString("no"));
               student.setName(rs.getString("name"));
               student.setEntYear(rs.getInt("ent_year"));
               student.setClassNum(rs.getString("class_num"));
               student.setAttend(rs.getBoolean("is_attend"));
           }
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return student;
   }

   // 全てのクラス番号を取得
   public List<Student> getAllClassNum() throws SQLException{
	   List<Student> list = new ArrayList<>();
	   try(Connection con = getConnection()){
		   String sql = "select class_num from student group by class_num";
		   PreparedStatement stmt = con.prepareStatement(sql);
		   ResultSet rs = stmt.executeQuery();
		   while (rs.next()) {
			   Student student = new Student();
			   student.setClassNum(rs.getString("class_num"));
	           list.add(student);
	          }
	   } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	} return list;
   }


   //今年を基準に+-10年文の西暦
	public List<Integer> getEntYearRange() {
	    List<Integer> list = new ArrayList<>();
	    int currentYear = java.time.Year.now().getValue();
	    for (int i = currentYear - 10; i <= currentYear + 10; i++) {
	        list.add(i);
	    }
	    return list;
	}


   // 学生更新
   public void updateStudent(Student student) throws SQLException {

       try (Connection con = getConnection()) {
    	   String sql = "UPDATE student SET name = ?, class_num = ?, is_attend = ? WHERE no = ?";
    	   PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, student.getName());
           stmt.setString(2, student.getClassNum());
           stmt.setBoolean(3, student.isAttend());
           stmt.setString(4, student.getNo());
           stmt.executeUpdate();
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
   }
   
   //重複チェック




   // ★ 入学年度、クラス番号、在学状態で絞り込み
   public List<Student> findStudents(Integer entYear, String classNum, Boolean isAttend) throws SQLException {
       List<Student> list = new ArrayList<>();
       try (Connection con = getConnection()) {
    	   String sql = "SELECT * FROM student WHERE 1=1";
           List<Object> params = new ArrayList<>();
           if (entYear != null) {
               sql += " AND ent_year = ?";
               params.add(entYear);
           }
           if (classNum != null && !classNum.isEmpty()) {
               sql += " AND class_num = ?";
               params.add(classNum);
           }
           if (isAttend != null) {
               sql+= " AND is_attend = ?";
               params.add(isAttend);
           }
           sql += "order by no";
    	   PreparedStatement stmt = con.prepareStatement(sql);
           System.out.println("connection：" + stmt);
           for (int i = 0; i < params.size(); i++) {
               stmt.setObject(i + 1, params.get(i));
           }
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
        	   Student student = new Student();
               student.setNo(rs.getString("no"));
               student.setName(rs.getString("name"));
               student.setEntYear(rs.getInt("ent_year"));
               student.setClassNum(rs.getString("class_num"));
               student.setAttend(rs.getBoolean("is_attend"));
               School school = new School();
               school.setCd(rs.getString("school_cd"));
               student.setSchool(school);
               list.add(student);
           }
       } catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
       return list;
   }
}


