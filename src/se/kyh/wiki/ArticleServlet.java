package se.kyh.wiki;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.kyh.wiki.db.ArticleDAO;

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
		
		String pathInfo;
		String[] tempUrlFragments;
		ArrayList<String> urlFragments = new ArrayList<String>();
		
		MainNavigation mainNavigation = new MainNavigation();

		request.setAttribute("mainNavigation", mainNavigation);
		
		if ((pathInfo = request.getPathInfo()) != null) {
			
			pathInfo = pathInfo.substring(1);
			
			tempUrlFragments = pathInfo.split("[/]");
			
			for (String urlFragment : tempUrlFragments) {
				urlFragments.add(urlFragment);
			}
			
			
		} else {
			urlFragments.add("view");
			urlFragments.add("start");
		}
		
		if (urlFragments.get(0).equals("view")) {

			
			Article article = ArticleDAO.INSTANCE.getArticleByTitle(urlFragments.get(1).toString());
			
			String jsp = "/view.jsp";
			
			if (article == null) {
				jsp = "/404.jsp";
			}
			
			request.setAttribute("article", article);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
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
