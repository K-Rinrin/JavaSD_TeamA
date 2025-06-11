package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDao extends DAO {

	public Teacher findByTeacherid(String id) throws Exception {
		Teacher teacher = null;

		try (Connection con = getConnection()) {
			String sql = "SELECT * FROM TEACHER WHERE ID = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				teacher = new Teacher();
				teacher.setId(rs.getString("id"));
				teacher.setName(rs.getString("name"));
				teacher.setPassword(rs.getString("password"));
//				teacher.setSchool(rs.getSchool("school"));
			}
		}
		return teacher;
	}


	public Teacher login(String id, String password) throws Exception {
		// ユーザーIDに対応するユーザー情報を取得
		Teacher teacher = findByTeacherid(id);
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
