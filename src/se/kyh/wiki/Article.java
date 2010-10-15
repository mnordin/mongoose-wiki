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
		ArrayList<String> article = this.getArticle(id);
		this.setTitle(article.get(0));
		this.setBody(article.get(1));
	}
	
	private ArrayList<String> getArticle(int id){
		DbConnection connection = new DbConnection();
		ArrayList<String> article = new ArrayList<String>();
		try {
			Statement query = connection.connect().createStatement();
			ResultSet result = query.executeQuery("SELECT * FROM article WHERE id = "+ id);
			
			article.add(result.getNString("title"));
			article.add(result.getNString("body"));
			
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
