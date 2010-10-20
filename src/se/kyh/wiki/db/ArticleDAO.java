package se.kyh.wiki.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.kyh.wiki.Article;

public class ArticleDAO {
	public static final ArticleDAO INSTANCE = new ArticleDAO();
	
	private ArticleDAO() {
		
	}
	
	public Article getArticleByTitle(String title) {
		DbConnection connection = new DbConnection();
		Article article = null;
		try {
			
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT id,body FROM article WHERE title = '" + title + "'");
			
			while (result.next()) {
				int id = result.getInt("id");
				String body = result.getString("body");
				article = new Article(id, title, body);
			}
			
			connection.disconnect();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return article;
	}
	
	public Article getArticleById(int id){
		DbConnection connection = new DbConnection();
		Article article = null;
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT * FROM article WHERE id = "+ id);
			
			while (result.next()) {
				article = new Article(result);
			}
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return article;
		
	}
	
	public List<Article> getArticlesBySearchQuery(String searchQuery) {
		List<Article> articlesByTitle = new ArrayList<Article>();
		List<Article> articlesByBody = new ArrayList<Article>();
		List<Article> articles = new ArrayList<Article>();
		
		DbConnection connection = new DbConnection();
		
		try {

			// Title sök
			Statement titleQuery = connection.connect().createStatement();
			ResultSet titleResult = titleQuery.executeQuery("SELECT id, title, body " +
					"FROM article " +
					"WHERE title LIKE '%"+ searchQuery +"%'");
			
			while (titleResult.next()) {
				articlesByTitle.add(new Article(titleResult));
			}
			
			// Body sök
			Statement bodyQuery = connection.connect().createStatement();
			ResultSet bodyResult = bodyQuery.executeQuery("SELECT id, title, body " +
					"FROM article " +
					"WHERE body LIKE '%"+ searchQuery +"%'");
			
			while (bodyResult.next()) {
				articlesByBody.add(new Article(bodyResult));
			}
			
			
			connection.disconnect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		articles.addAll(articlesByTitle);
		
		//remove duplicates
		// TODO bugg i if-satsen, den hajar inte dubletter utan lägger till allting igen
		for (Article articleByBody : articlesByBody) {
			if (!articlesByTitle.contains(articleByBody)) {
				articles.add(articleByBody);
			}
		}
		
		return articles;
	}
	
}
