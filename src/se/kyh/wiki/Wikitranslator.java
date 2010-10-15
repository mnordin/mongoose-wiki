package se.kyh.wiki;

import java.util.ArrayList;
import java.util.Collection;



public class Wikitranslator {
	
	public String translate(String body) {
		
		ArrayList<String> wikiTaggar = new ArrayList();
			
		wikiTaggar.add("[[b]]");
		wikiTaggar.add("[[/b]]");
		wikiTaggar.add("[[i]]");
		wikiTaggar.add("[[/i]]");
		
		ArrayList<String> htmlTaggar = new ArrayList();
		
		htmlTaggar.add("<strong>");
		htmlTaggar.add("</strong>");
		htmlTaggar.add("<em>");
		htmlTaggar.add("</i>");
		
		
		for (int i = 0; i < wikiTaggar.size(); i++ ) {
			body = body.replaceAll(wikiTaggar.get(i), htmlTaggar.get(i));
		}
		
		return body;
	}

}
