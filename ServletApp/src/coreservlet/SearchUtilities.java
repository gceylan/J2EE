package coreservlet;

public class SearchUtilities {
	
	private static SearchSpec[] commonSpecs = {
			new SearchSpec("Google", "http://www.google.com/search?q="),
			new SearchSpec("Yahoo", "http://search.yahoo.com/bin/search?p=")
	};
	
	public static SearchSpec[] getCommonSpecs() {
		return commonSpecs;
	}
	
	public static String makeURL(String searchEngineName, String searchString) {
		SearchSpec[] searchSpecs = getCommonSpecs();
		String searchURL = null;
		
		for (SearchSpec searchSpec : searchSpecs) {
			if (searchSpec.getName().equalsIgnoreCase(searchEngineName)) {
				searchURL = searchSpec.makeURL(searchString);
				break;
			}
		}
		
		return searchURL;
	}
}
