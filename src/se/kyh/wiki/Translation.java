package se.kyh.wiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translation {
	
	private Pattern needle;
	private String replacement;

	public Translation(Pattern pNeedle, String pReplacement) {
		this.needle = pNeedle;
		this.replacement = pReplacement;
	}

	public String translate(String pArticleBody) {
		
		Matcher m = this.needle.matcher(pArticleBody);
		
		String newArticleBody = m.replaceAll(this.replacement);
		
		return newArticleBody;
		
	}
	
}