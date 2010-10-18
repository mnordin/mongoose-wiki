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
		String body = this.getArticleBodyByTitle(title);
		this.setId(0);
		this.setBody(body);
	}
	
	public Article(int id, String title, String body) {
		this.setId(id);
		this.setTitle(title);
		this.setBody(body);
	}

	private ArrayList<String> getArticleById(int id){
		DbConnection connection = new DbConnection();
		ArrayList<String> article = new ArrayList<String>();
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT title, body FROM article WHERE id = "+ id);
			
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
	
	private String getArticleBodyByTitle(String title){
		DbConnection connection = new DbConnection();
		String body = null;
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT body FROM article WHERE title = '" + title + "'");
			
			while (result.next()) {
				body = result.getString("body");
			}
			
			connection.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return body;
		
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
		Wikitranslator wikitext = new Wikitranslator();
		return wikitext.translate(body);
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public boolean equals(Article otherArticle) {
		return this.getId() == otherArticle.getId();
	}

}
