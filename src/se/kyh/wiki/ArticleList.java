package se.kyh.wiki;

import java.util.ArrayList;

public class ArticleList extends ArrayList<Article> {

	private static final long serialVersionUID = -6942215659539061389L;

	public boolean contains(Article comparisonArticle) {
		
		for (Article article : this) {
			if (article.equals(comparisonArticle)) {
				return true;
			}
		}
		
		return false;
	}
	
}
