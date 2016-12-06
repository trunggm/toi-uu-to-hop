package com.toiuutohop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TrunggmGrapsServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setAttribute("author", "Trunggm");
		req.setAttribute("title", "Greedy randomized adaptive search procedure");
		req.getRequestDispatcher("/form.jsp").forward(req, resp);
	}
}
