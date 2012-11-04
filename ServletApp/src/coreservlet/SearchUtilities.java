package coreservlet;

public class SearchUtilities {
	
	private static SearchSpec[] commonSpecs = {
			new SearchSpec("Google", "http://www.google.com/search?q="),
			new SearchSpec("Yahoo", "http://search.yahoo.com/bin/search?p=")
	};
	
	public static SearchSpec[] getCommonSpecs() {
		return commonSpecs;
	}
}
