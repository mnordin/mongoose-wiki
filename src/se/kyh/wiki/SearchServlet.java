package se.kyh.wiki;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchQuery = null;
		
		searchQuery = request.getParameter("q");
		if (searchQuery == null) {
			// ingen s�kning, formul�ret i search.jsp visas
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jsp");
			
			dispatcher.forward(request, response);
			
		} else {
			// s�kning �r k�rd, hitta alla artiklar som tr�ffas
			
			Search search = new Search(searchQuery);

			// s�kresultat
			request.setAttribute("searchQuery", searchQuery);
			request.setAttribute("searchResult", search.getResult());
			
			
			//request.setAttribute("articles", articles);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchResult.jsp");
			dispatcher.forward(request, response);
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
