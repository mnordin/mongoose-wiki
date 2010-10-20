package se.kyh.wiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkTranslation extends Translation {

	public LinkTranslation() {
		this.needle = Pattern.compile("\\[([^\\s\\]]+)[ ](.[^\\[]+)\\]");
	}

	@Override
	public String translate(String pArticleBody) {
		
		Matcher m = this.needle.matcher(pArticleBody);
		
		if(!m.find()) {
			return pArticleBody;
		}
		
		//så att do whilen inte börjar 1 "find" in
		m.reset();
		
		StringBuffer sb = new StringBuffer();
		
		do {
			m.find();
			String href = m.group(1);
			if(!Pattern.matches("^http://.*", href)){ href = "http://" + href; }
			String title = m.group(2);
			m.appendReplacement(sb, "<a href=\"" + href + "\">" + title + "</a>");
			
		} while (!m.hitEnd());
		
		m.appendTail(sb);
		
		return sb.toString();
		
	}

}
