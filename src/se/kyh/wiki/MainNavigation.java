package se.kyh.wiki;

import java.util.ArrayList;

public class MainNavigation {
	
	ArrayList<String> navItems = new ArrayList<String>();
	
	public MainNavigation() {
		addLink("/mongoose-wiki/", "Hem");
		addLink("/mongoose-wiki/user", "Registrera dig eller logga in");
	}
	
	public void addLink(String url, String label) {
		navItems.add("<a href=\"" + url + "\">"+label+"</a>");
		
	}
	
	public ArrayList<String> getItems() {
		return navItems;
	}
	
}
