package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;


public class StudentDAO extends DAO {

	 // ★ 入学年度、クラス番号、在学状態で絞り込み
	public List<Student> findStudents(Integer entYear, String classNum, Boolean isAttend, Teacher teacher) throws SQLException {
		List<Student> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE 1=1");
		List<Object> params = new ArrayList<>();

		// ログインしている教員の学校で絞る
		if (teacher != null && teacher.getSchool() != null) {
			sql.append(" AND school_cd = ?");
			params.add(teacher.getSchool().getCd());
		} else {

			return list;
		}

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

		sql.append(" ORDER BY no");

		try (Connection con = getConnection()) {
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			System.out.println("Executing SQL: " + stmt); // デバッグ用に実行SQLを表示

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
			e.printStackTrace();
			throw new SQLException("学生情報の検索に失敗しました。", e);
		}
		return list;
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

		            School school = new School();
		            school.setCd(rs.getString("school_cd"));
		            student.setSchool(school);
				}
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new SQLException("学生情報の取得に失敗しました。学生番号: " + no, e);
			}
			return student;
		}

		// 全学生取得
		   public List<Student> getAllStudents() throws SQLException {
		      List<Student> list = new ArrayList<>();
		      try(Connection con = getConnection()) {
		    	  String sql = "SELECT * FROM student";
		    	  PreparedStatement stmt = con.prepareStatement(sql);
		    	  ResultSet rs = stmt.executeQuery();
		    	  while (rs.next()) {
		    		  Student student = new Student();
		              student.setNo(rs.getString("no"));
		              student.setName(rs.getString("name"));
		              student.setEntYear(rs.getInt("ent_year"));
		              student.setClassNum(rs.getString("class_num"));
		              student.setAttend(rs.getBoolean("is_attend"));
		             list.add(student);
		          }
		      } catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			  }
			  return list;
		   }






		public List<String> getAllClassNum(Teacher teacher) throws SQLException{
			List<String> list = new ArrayList<>();
	        // teacherがnullの場合は空のリストを返す（エラー防止）
	        if (teacher == null || teacher.getSchool() == null) {
	            return list;
	        }
			try(Connection con = getConnection()){
				String sql = "select class_num from student where school_cd = ? group by class_num order by class_num";
				PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, teacher.getSchool().getCd());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {

					list.add(rs.getString("class_num"));
		          	}
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException("クラス番号の取得に失敗しました。", e);
			}
			return list;
		}






		public List<Integer> getAllEntYear(Teacher teacher) throws SQLException{
			List<Integer> list = new ArrayList<>();
	        // teacherがnullの場合は空のリストを返す
	        if (teacher == null || teacher.getSchool() == null) {
	            return list;
	        }
			try(Connection con = getConnection()){
				String sql = "select ent_year from student where school_cd = ? group by ent_year order by ent_year desc";
				PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, teacher.getSchool().getCd());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					list.add(rs.getInt("ent_year"));
		          	}
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException("入学年度の取得に失敗しました。", e);
			}
			return list;
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



	}

