package se.kyh.wiki;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Wikitranslator {
	
	ArrayList<Translation> translations = new ArrayList<Translation>();
	
	public Wikitranslator() {

		translations.add(new Translation(Pattern.compile("===(.+)==="), "<h2>$1</h2>"));
		translations.add(new Translation(Pattern.compile("==(.+)=="), "<h3>$1</h3>"));
		translations.add(new Translation(Pattern.compile("\\[section\\](.+)\\[/section\\]"), "<p>$1</p>"));
		translations.add(new Translation(Pattern.compile("\\[b\\](.+)\\[/b\\]"), "<strong>$1</strong>"));
		translations.add(new Translation(Pattern.compile("\\[i\\](.+)\\[/i\\]"), "<em>$1</em>"));
		translations.add(new Translation(Pattern.compile("\n\\*\\p{Blank}+(.+)"), "\n<li>$1</li>"));
		translations.add(new Translation(Pattern.compile("\\[(\\S+)\\p{Blank}+(.*)\\]"), "<a href=\"$1\">$2</a>"));
		translations.add(new Translation(Pattern.compile("\\[\\[(.+)\\]\\]"), "<a href=\"/mongoose-wiki/article/view/$1\">$1</a>"));
		
	}
	
	public String translate(String articleBody) {
		
		for (Translation translation : translations) {
			articleBody = translation.translate(articleBody);
		}
		
		return articleBody;
		
	}

}
