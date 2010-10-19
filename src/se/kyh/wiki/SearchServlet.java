package se.kyh.wiki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.kyh.wiki.db.ArticleDAO;

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

			// ingen sškning, formulŠret i search.jsp visas
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jsp");
			
			dispatcher.forward(request, response);
			
		} else {
			// sï¿½kning ï¿½r kï¿½rd, hitta alla artiklar som trï¿½ffas

			List<Article> articles = ArticleDAO.INSTANCE.getArticlesBySearchQuery(searchQuery);

			// sï¿½kresultat
			request.setAttribute("searchQuery", searchQuery);
			
			request.setAttribute("searchResult", articles);
			
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
