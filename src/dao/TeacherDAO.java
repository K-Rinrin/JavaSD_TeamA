package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {
	public TeacherDAO(Connection connection) {
	}

	// IDで教員を取得
	public Teacher getTeacherById(String id) throws SQLException {
		try (Connection con = getConnection()){
			String sql = "SELECT * FROM teacher WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getString("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setName(rs.getString("name"));
				School school = new School();
				school.setCd(rs.getString("school_cd"));
				teacher.setSchool(school);
				return teacher;
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

	public Teacher login(String id, String password) throws Exception {
		// ユーザーIDに対応するユーザー情報を取得
		Teacher teacher = getTeacherById(id);
		if (teacher == null) {
			return null; // ユーザーが存在しない場合
		}

		if (password.equals(teacher.getPassword())) {
			return teacher;
		} else {
			return null;
		}
	}
}