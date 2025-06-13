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


public class StudentDAO extends DAO {
   private Connection connection;
   public StudentDAO(Connection connection) {
       this.connection = connection;
   }
   // 学生追加
   public void addStudent(Student student) throws SQLException {
       String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, student.getNo());
           stmt.setString(2, student.getName());
           stmt.setInt(3, student.getEntYear());
           stmt.setString(4, student.getClassNum());
           stmt.setBoolean(5, student.isAttend());
           stmt.setString(6, student.getSchool().getCd());
           stmt.executeUpdate();
       }
   }
   // 学生番号で取得
   public Student getStudentByNo(String no) throws SQLException {
       String sql = "SELECT * FROM student WHERE no = ?";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, no);
           ResultSet rs = stmt.executeQuery();
           if (rs.next()) {
               return mapResultSetToStudent(rs);
           }
       }
       return null;
   }
   // 全学生取得
   public List<Student> getAllStudents() throws SQLException {
       List<Student> list = new ArrayList<>();
       String sql = "SELECT * FROM student";
       try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           while (rs.next()) {
               list.add(mapResultSetToStudent(rs));
           }
       }
       return list;
   }
   // 学生更新
   public void updateStudent(Student student) throws SQLException {
       String sql = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_cd = ? WHERE no = ?";
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
   // ★ 入学年度、クラス番号、在学状態で絞り込み
   public List<Student> findStudents(Integer entYear, String classNum, Boolean isAttend) throws SQLException {
       List<Student> list = new ArrayList<>();
       StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1");
       List<Object> params = new ArrayList<>();
       if (entYear != null) {
           sql.append(" AND ent_year = ?");
           params.add(entYear);
       }
       if (classNum != null && !classNum.isEmpty()) {
           sql.append(" AND class_num = ?");
           params.add(classNum);
       }
       if (isAttend != null) {
           sql.append(" AND is_attend = ?");
           params.add(isAttend);
       }
       try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
           for (int i = 0; i < params.size(); i++) {
               stmt.setObject(i + 1, params.get(i));
           }
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               list.add(mapResultSetToStudent(rs));
           }
       }
       return list;
   }
   // 結果セットから Student を作るヘルパー
   private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
       Student student = new Student();
       student.setNo(rs.getString("no"));
       student.setName(rs.getString("name"));
       student.setEntYear(rs.getInt("ent_year"));
       student.setClassNum(rs.getString("class_num"));
       student.setAttend(rs.getBoolean("is_attend"));
       School school = new School();
       school.setCd(rs.getString("school_cd"));
       student.setSchool(school);
       return student;
   }
}