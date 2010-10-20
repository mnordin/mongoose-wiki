package se.kyh.wiki.test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import se.kyh.wiki.Wikitranslator;
import junit.framework.TestCase;

public class TestTranslation extends TestCase {
	
	Wikitranslator translator = new Wikitranslator();
	
	public void testSimpleTranslations() {
		
		ArrayList<String> originals = new ArrayList<String>(); 
		ArrayList<String> expecteds = new ArrayList<String>();
		
		originals.add("hey[b]hey[/b]\nhey");
		expecteds.add("hey<strong>hey</strong>\nhey");
		
		originals.add("hey[section]===hey===[/section]\n==hey==");
		expecteds.add("hey<p><h2>hey</h2></p>\n<h3>hey</h3>");
		
		originals.add("[i]hey[/i]");
		expecteds.add("<em>hey</em>");

		originals.add("qwerty\n" +
				"* list item 1\n" +
				"* list item 2\n" +
				"* list item 3\n");
		expecteds.add("qwerty\n" +
				"<li>list item 1</li>\n" +
				"<li>list item 2</li>\n" +
				"<li>list item 3</li>\n");
		
		int i = 0;
		for (@SuppressWarnings("unused") String throwaway : originals) {
			String translation = this.translator.translate(originals.get(i));
			assertEquals(expecteds.get(i), translation);
			i++;
		}
		
		
	}
	
	public void testTranslateSection() {
		
		ArrayList<String> originals = new ArrayList<String>(); 
		ArrayList<String> expecteds = new ArrayList<String>();
		
		
		originals.add("hey[section]sdfghj[/section]\n");
		expecteds.add("hey<p>sdfghj</p>\n");
		
		int i = 0;
		for (@SuppressWarnings("unused") String unused : originals) {
			String translation = this.translator.translate(originals.get(i));
			assertEquals(expecteds.get(i), translation);
			i++;
		}
		
		
	}
	
	public void testTranslateInternalLinks() {
		
		ArrayList<String> originals = new ArrayList<String>(); 
		ArrayList<String> expecteds = new ArrayList<String>();
		
		originals.add("asd[[start]]");
		expecteds.add("asd<a href=\"/mongoose-wiki/article/view/start\">start</a>");
		
		int i = 0;
		for (@SuppressWarnings("unused") String throwaway : originals) {
			String translation = this.translator.translate(originals.get(i));
			assertEquals(expecteds.get(i), translation);
			i++;
		}
		
	}
	
	public void testTranslateExternalLinks() {
		
		String original = "asd[http://wikibooks.org/wiki/How_to_find_a_book How to find a book]";
		String actual = this.translator.translate(original);
		String expected = "asd<a href=\"http://wikibooks.org/wiki/How_to_find_a_book\">How to find a book</a>";
		
		assertEquals(expected, actual);
		
		original = "asd[http://wikibooks.org/wiki/How_to_find_a_book How to find a book]sdf sdfghf[][][][lifehacker.com lifehacker]";
		actual = this.translator.translate(original);
		expected = "asd<a href=\"http://wikibooks.org/wiki/How_to_find_a_book\">How to find a book</a>sdf sdfghf[][][]<a href=\"http://lifehacker.com\">lifehacker</a>";
		
		assertEquals(expected, actual);
		
	}
	
	public void testTranslateKitchenSink() {
		
		String original = 	"[section]Kolla på artikeln [[hejsan]] och \n" +
							" eller [http://lifehacker.com lifehacker]\n" +
							"[www.lifehacker.com lifehacker]\n" +
							"[b]mycket viktigt[/b], gå tillbaka till [[start]][/section]\n" +
							"* Item 1 [i]rooligt[/i]\n" +
							" eller [http://lifehacker.com lifehacker]\n" +
							"* Item 2\n" +
							"* Item 3\n" +
							"[section]sdfghjgfdsa[/section]\n";
		
		String expected = 	"<p>Kolla på artikeln <a href=\"/mongoose-wiki/article/view/hejsan\">hejsan</a> och \n" +
							" eller <a href=\"http://lifehacker.com\">lifehacker</a>\n" +
							"<a href=\"http://www.lifehacker.com\">lifehacker</a>\n" +
							"<strong>mycket viktigt</strong>, gå tillbaka till <a href=\"/mongoose-wiki/article/view/start\">start</a></p>\n" +
							"<li>Item 1 <em>rooligt</em></li>\n" +
							" eller <a href=\"http://lifehacker.com\">lifehacker</a>\n" +
							"<li>Item 2</li>\n" +
							"<li>Item 3</li>\n" +
							"<p>sdfghjgfdsa</p>\n";
		
		String actual = this.translator.translate(original);
		
		assertEquals(expected, actual);
		
	}
	
}