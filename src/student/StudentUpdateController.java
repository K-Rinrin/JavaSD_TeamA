package student;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentupdate"})//
public class StudentUpdateController extends CommonServlet {


	//学生更新
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//更新する学生の学生番号をもらう
		String no = req.getParameter("no");

		//学生情報をもらう
		StudentDAO dao = new StudentDAO();
		Student student = dao.getStudentByNo(no);
		//クラス番号の一覧をもらう
		List<Student> allclass = dao.getAllClassNum();

		//確認
		if (student != null) {
		    System.out.println("【確認】学生情報取得成功:");
		    System.out.println("学生番号: " + student.getNo());
		    System.out.println("氏名: " + student.getName());
		    System.out.println("入学年度: " + student.getEntYear());
		    System.out.println("クラス: " + student.getClassNum());
		    System.out.println("在学中: " + student.isAttend());
		} else {
		    System.out.println("【警告】学生情報が null です。学生番号: " + no);
		}

		//学生情報をわたす
		req.setAttribute("student", student);
		req.setAttribute("allclass", allclass);

		req.getRequestDispatcher("/main/student/STDM004.jsp").forward(req, resp);

	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
