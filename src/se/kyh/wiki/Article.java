package se.kyh.wiki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import se.kyh.wiki.db.ArticleDAO;
import se.kyh.wiki.db.DbConnection;

public class Article {
	
	private int id;
	private String title;
	private String body;
			
	public Article(int id, String title, String body) {
		this.setId(id);
		this.setTitle(title);
		this.setBody(body);
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
