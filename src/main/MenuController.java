package main;

//メインメニュー画面

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;
@WebServlet(urlPatterns = { "/main/MMNU001" })

public class MenuController extends CommonServlet {

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
        req.getRequestDispatcher("MMNU001.jsp").forward(req, resp);
	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
