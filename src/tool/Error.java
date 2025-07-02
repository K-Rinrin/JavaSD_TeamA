package tool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/error" })
public class Error extends CommonServlet {


    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // error.jsp にフォワードしてエラー画面を表示
        req.getRequestDispatcher("error.jsp").forward(req, resp);
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        get(req, resp);
    }
}
