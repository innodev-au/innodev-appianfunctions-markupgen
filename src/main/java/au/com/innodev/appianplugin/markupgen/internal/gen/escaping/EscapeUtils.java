package au.com.innodev.appianplugin.markupgen.internal.gen.escaping;

import org.apache.commons.text.StringEscapeUtils;

/**
 * Wraps escaping methods for consistent use across this project.
 * 
 * @author juanal
 *
 */
public class EscapeUtils {

	public static final String escapeHtml(String unescapedText) {
		return StringEscapeUtils.escapeHtml4(unescapedText);
	}
	
	public static final String escapeXml(String unescapedText) {
		return StringEscapeUtils.escapeXml10(unescapedText);
	}
}
