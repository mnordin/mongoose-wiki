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
import se.kyh.wiki.db.UserDAO;

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
			
		} else if (urlFragments.get(0).equals("edit")){
			
			if (request.getSession().getAttribute("loggedIn") != null){
			
			
				Article article = ArticleDAO.INSTANCE.getArticleByTitle(urlFragments.get(1).toString());
				String jsp = "/edit.jsp";
				
				if (article == null) {
					jsp = "/404.jsp";
				}
				request.setAttribute("message", "Här kan du uppdatera artikeln");
				request.setAttribute("article", article);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("/mongoose-wiki/user");
			}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((request.getParameter("update_article") != null)) {
			// The user wishes to update an article!
			
			int id = Integer.parseInt(request.getParameter("id"));
			String title = (String)request.getParameter("title");
			String body = (String)request.getParameter("body");
			
			if (id > 0) {
				if (ArticleDAO.INSTANCE.updateArticle(id,title,body)) {
					request.setAttribute("message", "Artikel: "+title + " �r uppdaterad");
					String url = "/mongoose-wiki/article/view/"+title;
					response.sendRedirect(url);
				} else {
					request.setAttribute("message", "Det har blivit något fel. Artikel: "+title + " �r INTE uppdaterad");
				}
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view.jsp");
				
				dispatcher.forward(request, response);
			}
			
			
		
		}
	}

}
