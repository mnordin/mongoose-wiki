package se.kyh.wiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translation {
	
	protected Pattern needle;
	protected String replacement;

	public Translation(Pattern pNeedle, String pReplacement) {
		this.needle = pNeedle;
		this.replacement = pReplacement;
	}
	
	public Translation() {
		
	}

	public String translate(String pArticleBody) {
		
		Matcher m = this.needle.matcher(pArticleBody);
		
		String newArticleBody = m.replaceAll(this.replacement);
		
		return newArticleBody;
		
	}
	
}