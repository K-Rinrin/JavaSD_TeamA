package student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/student/studentcreateexecute"})
public class StudentCreateExecuteController extends CommonServlet {

	//学生登録
	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//学生登録
		try{
			StudentDAO dao = new StudentDAO();

			//登録に必要な情報をもらう
			int entYear = Integer.parseInt(req.getParameter("ent_year"));
			String no = req.getParameter("no");
			String name = req.getParameter("name");
			String classNum = req.getParameter("class_num");
			Teacher teacher = (Teacher) req.getSession().getAttribute("session_user");
			Boolean isAttend = true;

			//重複チェック
			 if (dao.getStudentByNo(no) != null) {
		            req.setAttribute("error", "この学生番号はすでに登録されています。");

		            // 入力済みデータと一覧データを再セット
		            req.setAttribute("ent_year", entYear);
		            req.setAttribute("no", no);
		            req.setAttribute("name", name);
		            req.setAttribute("class_num", classNum);
		            req.setAttribute("classnum", dao.getAllClassNum());
		            req.setAttribute("entYearList", dao.getEntYearRange());

		            req.getRequestDispatcher("/main/student/STDM002.jsp").forward(req, resp);

		        }
			//DAOに渡す
			Student stu = new Student();
			stu.setNo(no);
			stu.setName(name);
			stu.setEntYear(entYear);
			stu.setClassNum(classNum);
			stu.setAttend(isAttend);
			stu.setSchool(teacher.getSchool()); // 先生の所属学校


			dao.addStudent(stu);

			req.getRequestDispatcher("main/student/STDM003.jsp").forward(req, resp);

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.getRequestDispatcher("/main/ERRO001.jsp").forward(req, resp);
		}


	}

}
