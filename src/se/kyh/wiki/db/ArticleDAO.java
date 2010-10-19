package se.kyh.wiki.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	private Article getArticleById(int id){
		DbConnection connection = new DbConnection();
		Article article = null;
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT title, body FROM article WHERE id = "+ id);
			
			while (result.next()) {
				String title = (result.getString("title"));
				String body = (result.getString("body"));
			}
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return article;
		
	}
	
}
