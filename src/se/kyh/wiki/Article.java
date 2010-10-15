package se.kyh.wiki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Article {
	
	private int id;
	private String title;
	private String body;
	
	public Article(int id) {
		this.setId(id);
		ArrayList<String> article = this.getArticleById(id);
		this.setTitle(article.get(0));
		this.setBody(article.get(1));
	}
	
	public Article(String title) {
		this.setTitle(title);
		ArrayList<String> article = this.getArticleByTitle(title);
		this.setId(Integer.valueOf((article.get(0))).intValue());
		this.setBody(article.get(1));
	}

	private ArrayList<String> getArticleById(int id){
		DbConnection connection = new DbConnection();
		ArrayList<String> article = new ArrayList<String>();
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT * FROM article WHERE id = "+ id);
			
			while (result.next()) {
				article.add(result.getString("title"));
				article.add(result.getString("body"));
			}
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return article;
		
	}
	
	private ArrayList<String> getArticleByTitle(String title){
		DbConnection connection = new DbConnection();
		ArrayList<String> article = new ArrayList<String>();
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT * FROM article WHERE title = "+ title);
			
			while (result.next()) {
				article.add(result.getString("id"));
				article.add(result.getString("body"));
			}
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return article;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	

}
