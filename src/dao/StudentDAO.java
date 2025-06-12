import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO implements DAO {
    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public void insert(Student student) {
        String sql = "INSERT INTO student (student_no, name, ent_year, class_num, is_attend, school_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.isAttend());
            ps.setInt(6, student.getSchoolId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student findById(String studentNo) {
        String sql = "SELECT * FROM student WHERE student_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("student_no"));
                s.setName(rs.getString("name"));
                s.setEntYear(rs.getInt("ent_year"));
                s.setClassNum(rs.getString("class_num"));
                s.setAttend(rs.getBoolean("is_attend"));

                // 学校情報を取得してセット
                int schoolId = rs.getInt("school_id");
                School school = findSchoolById(schoolId);
                s.setSchool(school);

                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("student_no"));
                s.setName(rs.getString("name"));
                s.setEntYear(rs.getInt("ent_year"));
                s.setClassNum(rs.getString("class_num"));
                s.setAttend(rs.getBoolean("is_attend"));

                int schoolId = rs.getInt("school_id");
                School school = findSchoolById(schoolId);
                s.setSchool(school);

                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Student student) {
        String sql = "UPDATE student SET name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_id = ? WHERE student_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getEntYear());
            ps.setString(3, student.getClassNum());
            ps.setBoolean(4, student.isAttend());
            ps.setInt(5, student.getSchoolId());
            ps.setString(6, student.getNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String studentNo) {
        String sql = "DELETE FROM student WHERE student_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentNo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 補助メソッド：学校IDでSchoolを取得（SchoolDAOでも可）
    private School findSchoolById(int schoolId) {
        School school = null;
        String sql = "SELECT * FROM school WHERE school_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, schoolId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                school = new School();
                school.setSchoolId(rs.getInt("school_id"));
                school.setSchoolName(rs.getString("school_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return school;
    }
}
