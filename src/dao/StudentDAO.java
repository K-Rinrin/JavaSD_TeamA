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
public class StudentDAO {
   private Connection connection;
   public StudentDAO(Connection connection) {
       this.connection = connection;

public class StudentDAO implements DAO {
    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
   }
   // 学生の追加
   public void addStudent(Student student) throws SQLException {
       String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_no) VALUES (?, ?, ?, ?, ?, ?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, student.getNo());
           stmt.setString(2, student.getName());
           stmt.setInt(3, student.getEntYear());
           stmt.setString(4, student.getClassNum());
           stmt.setBoolean(5, student.isAttend());
           stmt.setString(6, student.getSchool().getCd());  // Schoolから学校番号を取得
           stmt.executeUpdate();
       }
   }
   // 学籍番号で学生取得
   public Student getStudentByNo(String no) throws SQLException {
       String sql = "SELECT * FROM student WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, no);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               Student student = new Student();
               student.setNo(rs.getString("no"));
               student.setName(rs.getString("name"));
               student.setEntYear(rs.getInt("ent_year"));
               student.setClassNum(rs.getString("class_num"));
               student.setAttend(rs.getBoolean("is_attend"));
               // Schoolオブジェクトは別DAOなどで取得するのが一般的
               School school = new School();
               school.setCd(rs.getString("school_no"));
               student.setSchool(school);
               return student;
           }
       }
       return null;
   }
   // 全学生取得
   public List<Student> getAllStudents() throws SQLException {
       List<Student> list = new ArrayList<>();
       String sql = "SELECT * FROM student";
       try (Statement stmt = connection.createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               Student student = new Student();
               student.setNo(rs.getString("no"));
               student.setName(rs.getString("name"));
               student.setEntYear(rs.getInt("ent_year"));
               student.setClassNum(rs.getString("class_num"));
               student.setAttend(rs.getBoolean("is_attend"));
               School school = new School();
               school.setCd(rs.getString("school_no"));
               student.setSchool(school);
               list.add(student);
           }
       }
       return list;
   }
   // 学生情報の更新
   public void updateStudent(Student student) throws SQLException {
       String sql = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_no = ? WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, student.getName());
           stmt.setInt(2, student.getEntYear());
           stmt.setString(3, student.getClassNum());
           stmt.setBoolean(4, student.isAttend());
           stmt.setString(5, student.getSchool().getCd());
           stmt.setString(6, student.getNo());
           stmt.executeUpdate();
       }
   }
   // 学生削除
   public void deleteStudent(String no) throws SQLException {
       String sql = "DELETE FROM student WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, no);
           stmt.executeUpdate();
       }
   }
}