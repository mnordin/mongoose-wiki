package se.kyh.wiki;

import java.util.ArrayList;
import java.util.regex.Pattern;



public class Wikitranslator {
	
	ArrayList<Translation> translations;
	
	public Wikitranslator() {

		this.translations = new ArrayList<Translation>();
		translations.add(new Translation(Pattern.compile("===(.+)==="), "<h2>", "</h2>"));
		translations.add(new Translation(Pattern.compile("==(.+)=="), "<h3>", "</h3>"));
		translations.add(new Translation(Pattern.compile("\\[section\\](.+)\\[/section\\]"), "<p>", "</p>"));
		translations.add(new Translation(Pattern.compile("\\[b\\](.+)\\[/b\\]"), "<strong>", "</strong>"));
		translations.add(new Translation(Pattern.compile("\\[i\\](.+)\\[/i\\]"), "<em>", "</em>"));
		translations.add(new Translation(Pattern.compile("\n\\*\\p{Blank}+(.+)"), "\n<li>", "</li>"));
		translations.add(new Translation(Pattern.compile("\\[(\\S+)\\p{Blank}+(.*)\\]"), "<a href=\"$1\">$2</a>"));
		
	}
	
	public String translate(String articleBody) {
		
		for (int i = 0; i < translations.size(); i++ ) {
			articleBody = translations.get(i).translate(articleBody);
		}
		
		return articleBody;
		
	}

}
