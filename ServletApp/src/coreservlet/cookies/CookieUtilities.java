package coreservlet.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtilities {

	public static String getCookieValue(HttpServletRequest request,
			String cookieName, String defaultValue) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName))
					return cookie.getValue();
			}
		}

		return defaultValue;
	}

	public static Cookie getCookie(HttpServletRequest request, String cookieName) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName))
					return cookie;
			}
		}

		return null;
	}

}
