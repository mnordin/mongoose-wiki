package se.kyh.wiki.db;

public class SessionBean {
	
	public static final SessionBean INSTANCE = new SessionBean();
	
	private boolean loggedIn = false;
	
	private SessionBean() {
		
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	
	
	
}
