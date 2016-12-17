package com.toiuutohop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.search.query.ExpressionParser.name_return;

public class ExerciseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String request = req.getRequestURL().toString();
		String[] requestArray = request.split("/");
		String name = requestArray[requestArray.length - 1];
		
		System.out.println(name);
		StudentInfo st = new StudentInfo();
		if(name.equals(new String("trunggm"))){
			st = new StudentInfo("Trunggm", "GRASP", "trunggm");
		}
		else if(name.equals(new String("hieubq"))){
			st = new StudentInfo("Hieubq", "Quy hoach dong", "hieubq");
		}
		else if(name.equals(new String("anhht"))){
			st = new StudentInfo("Anhht", "Cutting Stock", "anhht");
		}
		else if(name.equals(new String("phuongq"))){
			st = new StudentInfo("Phuongq", "Hungarian", "phuongq");
		}
		else if(name.equals(new String("thenn"))){
			st = new StudentInfo("Thenn", "Balo3", "thenn");
		}
		else if(name.equals(new String("huylq"))){
			st = new StudentInfo("Huylq", "Tabu Search", "huylq");
		}
		else if(name.equals(new String("kiennv"))){
			st = new StudentInfo("Kiennv", "Bee for tsp", "kiennv");
		}
		else if(name.equals(new String("taip"))){
			st = new StudentInfo("Taip", "ACO", "taip");
		}
		else if(name.equals(new String("anhtv"))){
			st = new StudentInfo("Anhtv", "GA", "anhtv");
		}
		else if(name.equals(new String("phucnncb"))){
			st = new StudentInfo("Phucnn", "ChessBoard", "phucnncb");
		}
		else if(name.equals(new String("dunglx"))){
			st = new StudentInfo("dunglx", "Luyện Kim", "dunglx");
		}
		else if(name.equals(new String("phucnnlcs"))){
			st = new StudentInfo("Phucnn", "LCS", "phucnnlcs");
		}
		else if(name.equals(new String("phucnned"))){
			st = new StudentInfo("Phucnn", "Edit Distance", "phucnned");
		}
		else if(name.equals(new String("phucnnmm"))){
			st = new StudentInfo("Phucnn", "Matrix Chain Multiplication", "phucnnmm");
		}
		else if(name.equals(new String("namnv"))){
			st = new StudentInfo("Namnv", "Nhánh cận", "namnv");
		}
		else if(name.equals(new String("duongkm"))){
			st = new StudentInfo("Duongkm", "ILP", "duongkm");
		}
		else {
			st = new StudentInfo("Default", "Heuristic", "default");
		}
		
		setJsp(req, resp, st);
	}
	
	public void setJsp(HttpServletRequest req, HttpServletResponse resp, StudentInfo st) throws ServletException, IOException {
		req.setAttribute("author", st.name);
		req.setAttribute("title", st.excersice);
		req.setAttribute("jsfile", st.jsFile);
		req.getRequestDispatcher("/form.jsp").forward(req, resp);
		
	}
}
