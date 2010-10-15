package se.kyh.wiki;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticleServlet
 */
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo().trim();
		pathInfo = pathInfo.substring(1);
		
		String[] tempUrlFragments = pathInfo.split("[/]");
		ArrayList<String> urlFragments = new ArrayList<String>();
		
		for (String urlFragment : tempUrlFragments) {
			urlFragments.add(urlFragment);
		}
		
		if (urlFragments.get(0).equals("view")) {
			
			Article article = new Article(urlFragments.get(1));
			
			request.setAttribute("article", article);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view.jsp");
			
			dispatcher.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}