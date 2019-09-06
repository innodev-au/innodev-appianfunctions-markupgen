package au.com.innodev.appianplugin.markupgen.internal.gen.escaping;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeUtilsTest {

	@Test
	public void escapeHtml() {
		/* HTML doesn't escape apostrophe */
		String unescaped = "double quotes \" apostrophe ' angle brackets < >";
		String expected = "double quotes &quot; apostrophe ' angle brackets &lt; &gt;";
		
		assertEquals(expected, EscapeUtils.escapeHtml(unescaped));
	}

	@Test
	public void escapeXml() {
		/* XML escapes apostrophe */
		String unescaped = "double quotes \" apostrophe ' angle brackets < >";
		String expected = "double quotes &quot; apostrophe &apos; angle brackets &lt; &gt;";
		
		assertEquals(expected, EscapeUtils.escapeXml(unescaped));
	}
}
