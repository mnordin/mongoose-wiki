package se.kyh.wiki;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Wikitranslator {
	
	ArrayList<Translation> translations = new ArrayList<Translation>();
	
	public Wikitranslator() {
		
		/**
		 * Assume that order matters among translations (run tests)
		 */
		
		translations.add(new Translation(Pattern.compile("\\[section\\]"), "<p>"));
		translations.add(new Translation(Pattern.compile("\\[/section\\]"), "</p>"));
		translations.add(new Translation(Pattern.compile("\\[b\\]"), "<strong>"));
		translations.add(new Translation(Pattern.compile("\\[/b\\]"), "</strong>"));
		translations.add(new Translation(Pattern.compile("\\[i\\]"), "<em>"));
		translations.add(new Translation(Pattern.compile("\\[/i\\]"), "</em>"));
		translations.add(new Translation(Pattern.compile("===(.+)==="), "<h2>$1</h2>"));
		translations.add(new Translation(Pattern.compile("==(.+)=="), "<h3>$1</h3>"));
		translations.add(new Translation(Pattern.compile("\n\\*\\p{Blank}([^\n]+)"), "\n<li>$1</li>"));
		translations.add(new Translation(Pattern.compile("\\[\\[(\\w+)\\]\\]"), "<a href=\"/mongoose-wiki/article/view/$1\">$1</a>"));
		translations.add(new LinkTranslation());
		
	}
	
	public String translate(String articleBody) {
		
		for (Translation translation : translations) {
			articleBody = translation.translate(articleBody);
		}
		
		return articleBody;
		
	}

}
