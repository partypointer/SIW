package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** DEPRECATED **/
@WebServlet("/controller")
public class Controller extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	//overriding
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
						throws ServletException, IOException {
		String comando;
		comando = request.getParameter("submit");

		String nextPage;
		if(comando.equals("conferma")) {
			nextPage = "/index.jsp";
		}
		else nextPage = "/index.jsp";
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	
}
