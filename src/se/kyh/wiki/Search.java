package se.kyh.wiki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import se.kyh.wiki.db.DbConnection;

public class Search {
	
	private ArticleList result = new ArticleList();

	
	public Search(String searchQuery) {
		
	}
	
	public void addResult(Article article) {
		this.result.add(article);
	}
	
	public ArrayList<Article> getResult() {
		return this.result;
	}

}
