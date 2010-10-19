package se.kyh.wiki;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("message", "E-mail och lösenordsfälten är obligatoriska");
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = (String)request.getParameter("first_name");
		String lastName = (String)request.getParameter("last_name");
		String email = (String)request.getParameter("email");
		String password = (String)request.getParameter("password");
		
		if (email != null && password != null) {
			User newUser = new User(firstName, lastName, email, password);
			if (newUser.register()) {
				request.setAttribute("message", firstName + " " + lastName + " är registrerad");
			} else {
				request.setAttribute("message", "Du kunde inte registera dig");
			}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
