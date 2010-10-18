package se.kyh.wiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translation {
	
	private Pattern needle;
	private String startHtml;
	private String endHtml;
	private Boolean simpleReplace = false;
	private String replacement;

	public Translation(Pattern pNeedle, String pStartHtml, String pEndHtml) {
		this.needle = pNeedle;
		this.startHtml = pStartHtml;
		this.endHtml = pEndHtml;
	}

	public Translation(Pattern pNeedle, String pReplacement) {
		this.needle = pNeedle;
		this.replacement = pReplacement;
		this.simpleReplace = true;
	}

	public String translate(String pArticleBody) {
		
		Matcher m = this.needle.matcher(pArticleBody);
		
		String newArticleBody;
		
		if (this.simpleReplace) {
			newArticleBody = m.replaceAll(this.replacement);
		} else {
			newArticleBody = m.replaceAll(this.startHtml + "$1" + this.endHtml);
		}
		
		return newArticleBody;
		
	}
	
}