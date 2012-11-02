package coreservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipUtilities {
	
	// Browser Gzip destekliyor mu? 
	public static boolean isGzipSupported(HttpServletRequest request) {
		String encodings = request.getHeader("Accept-Encoding");
		
		return ((encodings != null) && (encodings.contains("gzip")));
	}
	
	// Gzip hizmet dışı mı?
	public static boolean isGzipDisabled(HttpServletRequest request) {
		String gzipState = request.getParameter("disableGzip");
		
		return ((gzipState != null) && (!gzipState.equalsIgnoreCase("false")));
	}
	
	public static PrintWriter getGzipWriter(HttpServletResponse response) throws IOException {
		
		return new PrintWriter(new GZIPOutputStream(response.getOutputStream())); 
	}

}
