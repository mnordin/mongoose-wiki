package se.kyh.wiki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class Search {
	
	private ArticleList articles = new ArticleList();

	
	public Search(String searchQuery) {
		
		ArrayList<Article> articlesByTitle = new ArrayList<Article>();
		ArrayList<Article> articlesByBody = new ArrayList<Article>();
		
		DbConnection connection = new DbConnection();
		
		try {

			// Title sšk
			Statement titleQuery = connection.connect().createStatement();
			ResultSet titleResult = titleQuery.executeQuery("SELECT id, title, body " +
					"FROM article " +
					"WHERE title LIKE '%"+ searchQuery +"%'");
			
			while (titleResult.next()) {
				int id = Integer.parseInt(titleResult.getString("id"));
				String title = titleResult.getString("title");
				String body = titleResult.getString("body");
				articlesByTitle.add(new Article(id, title, body));
			}
			
			// Body sšk
			Statement bodyQuery = connection.connect().createStatement();
			ResultSet bodyResult = bodyQuery.executeQuery("SELECT id, title, body " +
					"FROM article " +
					"WHERE body LIKE '%"+ searchQuery +"%'");
			
			while (bodyResult.next()) {
				int id = Integer.parseInt(bodyResult.getString("id"));
				String title = bodyResult.getString("title");
				String body = bodyResult.getString("body");
				articlesByBody.add(new Article(id, title, body));
			}
			
			
			connection.disconnect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.articles.addAll(articlesByTitle);
		
		//remove duplicates
		for (Article articleByBody : articlesByBody) {
			if (!articles.contains(articleByBody)) {
				this.articles.add(articleByBody);
			}
		}
		
	}
	
	public ArrayList<Article> getResult() {
		return this.articles;
	}

}
