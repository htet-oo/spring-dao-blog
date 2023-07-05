package springblog.mail;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getSitUrl(HttpServletRequest request) {
		String siteURL = request.getRequestURI().toString();
		return siteURL.replace(request.getServletPath(),"");
	}
}
