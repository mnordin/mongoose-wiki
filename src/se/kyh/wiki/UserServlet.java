package se.kyh.wiki;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.kyh.wiki.db.SessionBean;
import se.kyh.wiki.db.UserDAO;

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
		
		request.setAttribute("message", "E-mail och l�senordsf�lten �r obligatoriska");
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (!(request.getParameter("register") == null)) {
			// The user wishes to register a new user!
			
			String firstName = (String)request.getParameter("first_name");
			String lastName = (String)request.getParameter("last_name");
			String email = (String)request.getParameter("email");
			String password = (String)request.getParameter("password");
			
			if (email != null && password != null) {
				if (UserDAO.INSTANCE.register(firstName, lastName, email, password)) {
					request.setAttribute("message", firstName + " " + lastName + " �r registrerad");
				} else {
					request.setAttribute("message", "Du kunde inte registera dig");
				}
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
			
			dispatcher.forward(request, response);
		
		} else if (!(request.getParameter("login") == null)) {
			// The user wishes to log in
			
			String email = (String)request.getParameter("email");
			String password = (String)request.getParameter("password");
			
			boolean login = UserDAO.INSTANCE.logIn(email, password);
			
			if (login) {
				SessionBean.INSTANCE.setLoggedIn(true);
				request.getSession().setAttribute("loggedIn", SessionBean.INSTANCE.isLoggedIn());
				
				response.sendRedirect("/mongoose-wiki/");
				
			} else {
				request.setAttribute("message", "Du fyllde i fel uppgifter, f�rs�k igen!");
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
				
				dispatcher.forward(request, response);
			}
			
		}
		
		
		
	}

}
