package se.kyh.wiki.test;

import java.util.ArrayList;

import se.kyh.wiki.Wikitranslator;
import junit.framework.TestCase;

public class TestTranslation extends TestCase {
	
	Wikitranslator translator = new Wikitranslator();
	
	public void testTranslate() {
		
		ArrayList<String> originals = new ArrayList<String>(); 
		ArrayList<String> expecteds = new ArrayList<String>();
		
		originals.add("hey[b]hey[/b]\nhey");
		expecteds.add("hey<strong>hey</strong>\nhey");
		
		originals.add("hey[section]===hey===[/section]\n==hey==");
		expecteds.add("hey<p><h2>hey</h2></p>\n<h3>hey</h3>");
		
		originals.add("[i]hey[/i]");
		expecteds.add("<em>hey</em>");
		
		originals.add("asd[http://wikibooks.org/wiki/How_to_find_a_book How to find a book]");
		expecteds.add("asd<a href=\"http://wikibooks.org/wiki/How_to_find_a_book\">How to find a book</a>");

		originals.add("asd[[start]]");
		expecteds.add("asd<a href=\"/Article/view/start\">start</a>");
		
		originals.add("qwerty\n" +
				"* list item 1\n" +
				"* list item 2\n" +
				"* list item 3\n");
		expecteds.add("qwerty\n" +
				"<li>list item 1</li>\n" +
				"<li>list item 2</li>\n" +
				"<li>list item 3</li>\n");
		
		int i = 0;
		for (String original : originals) {
			String translation = this.translator.translate(originals.get(i));
			assertEquals(expecteds.get(i), translation);
			i++;
		}
		
	}
	
}